package com.tapestry.moic.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.Channel;
import com.tapestry.moic.entity.CustomerMaster;
import com.tapestry.moic.entity.FinalizedBuy;
import com.tapestry.moic.entity.JoorOrder;
import com.tapestry.moic.entity.JoorOrderDivisions;
import com.tapestry.moic.entity.JoorOrderItem;
import com.tapestry.moic.entity.MOQ;
import com.tapestry.moic.entity.MOQReport;
import com.tapestry.moic.entity.PreBuy;
import com.tapestry.moic.entity.ProductMaster;
import com.tapestry.moic.entity.ProductSKU;
import com.tapestry.moic.entity.ProductSizeScaleMoa;
import com.tapestry.moic.entity.SKUChangeReport;
import com.tapestry.moic.entity.Segment;
import com.tapestry.moic.entity.SkuChange;
import com.tapestry.moic.entity.key.FinalizedBuyKeys;
import com.tapestry.moic.repository.ChannelRepository;
import com.tapestry.moic.repository.CustomerMasterRepository;
import com.tapestry.moic.repository.FinalizedBuyRepository;
import com.tapestry.moic.repository.JoorOrderDivisionsRepository;
import com.tapestry.moic.repository.JoorOrderItemRepository;
import com.tapestry.moic.repository.JoorOrderRepository;
import com.tapestry.moic.repository.JoorOrderStyleItemRepository;
import com.tapestry.moic.repository.MOQReportRepository;
import com.tapestry.moic.repository.MOQRepository;
import com.tapestry.moic.repository.PreBuyRepository;
import com.tapestry.moic.repository.ProductMasterRepository;
import com.tapestry.moic.repository.ProductSKURepository;
import com.tapestry.moic.repository.ProductSizeScaleMoaRepository;
import com.tapestry.moic.repository.SKUChangeReportRepository;
import com.tapestry.moic.repository.SegmentRepository;
import com.tapestry.moic.repository.SkuChangeRepository;
import com.tapestry.moic.service.interfaces.IFinalizedBuyInternalService;
import com.tapestry.moic.utils.MoicUtil;

@Component
public class FinalizedBuyInternalService implements IFinalizedBuyInternalService {

	private Logger LOGGER = LoggerFactory.getLogger(FinalizedBuyInternalService.class);

	@Autowired
	private SkuChangeRepository skuChangeRepository;

	@Autowired
	private MOQRepository moqRepository;

	@Autowired
	private JoorOrderItemRepository joorOrderItemRepository;

	@Autowired
	private JoorOrderRepository joorOrderRepository;
	
	@Autowired
	private JoorOrderStyleItemRepository joorOrderStyleItemRepository;

	@Autowired
	private CustomerMasterRepository customerMasterRepository;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private ProductSKURepository productSKURepository;

	@Autowired
	private ProductMasterRepository productMasterRepository;

	@Autowired
	private PreBuyRepository preBuyRepository;

	@Autowired
	private MOQReportRepository moqReportRepository;
	
	@Autowired
	private SKUChangeReportRepository skuChangeReportRepository;

	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private FinalizedBuyRepository finalizedBuyRepository;
	
	@Autowired
	ProductSizeScaleMoaRepository productSizeScaleMoaRepository;
	
	@Autowired
	JoorOrderDivisionsRepository joorOrderDivisionsRepository; 

	@Override
	public void populateMOQReport() {
		LOGGER.debug("populateMOQReport Started ...");

		try {
			moqReportRepository.deleteAll();
			LOGGER.debug("Truncated MOQReport records");
			
			//setting this to false for all
			joorOrderItemRepository.setAllImpactedByMoqSkuChangeToFalse();
			
			List<MOQ> moqList = moqRepository.findByDelete(Boolean.FALSE);
			for (MOQ moq : moqList) {
				List<JoorOrderItem> joorOrderItems = joorOrderItemRepository.findByCombinedSku(moq.getSku());
				
				for (JoorOrderItem joorOrderItem : joorOrderItems) {
										
					Optional<JoorOrder> optionalJoorOrder = joorOrderRepository.findById(joorOrderItem.getOrderID());
					if (optionalJoorOrder.isPresent()) {
							
						List<String> seasons = joorOrderStyleItemRepository.getDistinctFashionSeasonByOrderId(joorOrderItem.getOrderID());
						String season = !seasons.isEmpty() ? seasons.get(0) : MoicConstant.BLANK;
						
						List<CustomerMaster> customerMaster = customerMasterRepository
								.findBySoldToNumberAndShipToNumberAndIsActiveOrderByCreateDateDesc(optionalJoorOrder.get().getCustomerCode(),
										optionalJoorOrder.get().getShippingCode(), Boolean.TRUE);
						if (customerMaster.size() > 0 &&
								moq.getSeasonCode().trim().equalsIgnoreCase(season)) {
							// MOQ details
							MOQReport moqReport = new MOQReport();
							moqReport.setSKU(moq.getSku());
							moqReport.setDelete(moq.getDelete());
							moqReport.setEffectiveDate(moq.getEffectiveDate());
							
							moqReport.setCreateDate(new Date());
														
							// JoorOrderItem details
							moqReport.setStyleColor(joorOrderItem.getItemColor());
							moqReport.setStyleNumber(joorOrderItem.getItemNumber());
							moqReport.setStyleName(joorOrderItem.getItemName());
							moqReport.setColorName(joorOrderItem.getItemColor());
							moqReport.setColorCode(joorOrderItem.getItemColorCode());
							moqReport.setTotalBuyQuantity(joorOrderItem.getItemQuantity());
							moqReport.setOrderTotalUSD(joorOrderItem.getItemTotalUSD());
							moqReport.setWidth(null == joorOrderItem.getWidth() ? "" : joorOrderItem.getWidth());
							moqReport.setSize(joorOrderItem.getItemSize());
							moqReport.setEuSize(joorOrderItem.getEuSize());
							moqReport.setUkSize(joorOrderItem.getUkSize());
							
							// JoorOrderStyleItem
							moqReport.setSeason(season);
							
							// JoorOrder details
							moqReport.setPoNumber(optionalJoorOrder.get().getOrderId().toString());
							moqReport.setStatus(optionalJoorOrder.get().getOrderType());
							moqReport.setOrderId(optionalJoorOrder.get().getOrderId());
							
							// "Logic:TRUE if in PreBuy table for the given Season"
							PreBuy preBuy = preBuyRepository.findBySeasonCodeAndSKU(season, joorOrderItem.getCombinedSku());
							if (preBuy != null) {
								moqReport.setCoo(preBuy.getCoo());

								// Use Primary when Country Of Origin is non-Asia
								// Use Secondary when Country Of Origin is Asia then Use Tertiary for everything
								// else Use Tertiary for everything else
								if (preBuy.getCoo() != null) {
									if (MoicConstant.NON_ASIA.equalsIgnoreCase(preBuy.getCoo().trim())) {
										moqReport.setSalesDocType(customerMaster.get(0).getSalesOrderTypePrimary());
									} else if (MoicConstant.ASIA.equalsIgnoreCase(preBuy.getCoo().trim())) {
										moqReport.setSalesDocType(customerMaster.get(0).getSalesOrderTypeSecondary());
									}
								}
							} else {
								moqReport.setSalesDocType(customerMaster.get(0).getSalesOrderTypeTertiary());
							}
							
							// CustomerMaster details
							moqReport.setSalesOrg(customerMaster.get(0).getSalesOrg());
							moqReport.setDistributionChannel(customerMaster.get(0).getDistributionChannel());
							moqReport.setDivision(customerMaster.get(0).getDivision());
							moqReport.setSoldToNumber(customerMaster.get(0).getSoldToNumber());
							moqReport.setShipToNumber(customerMaster.get(0).getShipToNumber());
							moqReport.setSoCancelDate(customerMaster.get(0).getSoCancelDate());
							moqReport.setSite(customerMaster.get(0).getSite());
							moqReport.setSoldToDescription(customerMaster.get(0).getSoldToDescription());
							moqReport.setCountry(customerMaster.get(0).getShippingCountry());
							moqReport.setCity(customerMaster.get(0).getShippingCity());
							
							//Channel details
							if(null != customerMaster.get(0).getChannelID()) {
								Optional<Channel> optChannel = channelRepository.findById(customerMaster.get(0).getChannelID());
								if(optChannel.isPresent()) {
									moqReport.setFileName(optChannel.get().getChannelName());
									moqReport.setChannel(optChannel.get().getChannelName());
								}
							}
							// Segment details
							if (null != customerMaster.get(0).getSegmentID()) {
								Optional<Segment> optSegment = segmentRepository
										.findById(customerMaster.get(0).getSegmentID());
								if (optSegment.isPresent())
									moqReport.setReqSegment(optSegment.get().getSegmentCode());
							}
							String productSKUName =  joorOrderItem.getItemColorCode().trim();
							if(joorOrderItem.getItemColorCode().trim() != null && joorOrderItem.getItemColorCode().trim().contains(MoicConstant.SPACE)) {
								productSKUName =  joorOrderItem.getItemColorCode().trim().substring(0, joorOrderItem.getItemColorCode().trim().indexOf(MoicConstant.SPACE));
							}
							
							List<ProductSKU> productSKUs = productSKURepository.findByStyleIdAndSKUName(joorOrderItem.getItemNumber(), productSKUName);
							
							if(productSKUs.size() > 0) {
								// ProductSKU details
								ProductSKU productSKU = productSKUs.get(0);
								moqReport.setSkuIntroDate(productSKU.getSKUIntroDate());
								moqReport.setScheduledDeliveryDate(productSKU.getDeliveryStartDate());
								moqReport.setSkuClass(productSKU.getSku_class());
								moqReport.setMaterialName(null == productSKU.getSKUTeam() ? "" : productSKU.getSKUTeam());
								
								Optional<ProductMaster> optProductMaster = productMasterRepository
										.findById(productSKU.getPlmUniqueId());
	
								// ProductMaster details
								if (optProductMaster.isPresent()) {
									moqReport.setDepartment(optProductMaster.get().getDepartment());
									moqReport.setTargetCost(optProductMaster.get().getTargetCost());
									moqReport.setBusinessUnit(optProductMaster.get().getDepartment());
									moqReport.setRetailPrice(null == optProductMaster.get().getRetailPrice() ? 0 : optProductMaster.get().getRetailPrice());
								}

							}
							joorOrderItem.setImpactedByMoqSkuChange(Boolean.TRUE);
							joorOrderItemRepository.save(joorOrderItem);
							
							moqReportRepository.save(moqReport);
							LOGGER.debug("MOQReport :: " + moqReport.toString());
						}					
					}
				}		
			}
		}catch(Exception e){
			LOGGER.error("Something went wrong in populateMOQReport \n");
			e.printStackTrace();
		}
		LOGGER.debug("populateMOQReport Ended ...");
	}

	@Override
	public void populateSKUChangeReport() {
		LOGGER.debug("populateSKUChangeReport Started ...");

		try {
			skuChangeReportRepository.deleteAll();
			LOGGER.debug("Truncated SKUChangeReport records");
			
			List<SkuChange> skuChangeList = skuChangeRepository.findByDelete(Boolean.FALSE);
			for (SkuChange skuChange : skuChangeList) {
	
				List<JoorOrderItem> joorOrderItems = joorOrderItemRepository.findByCombinedSku(skuChange.getOldSKU());
				
				for (JoorOrderItem joorOrderItem : joorOrderItems) {
					
					Optional<JoorOrder> optionalJoorOrder = joorOrderRepository.findById(joorOrderItem.getOrderID());
					if (optionalJoorOrder.isPresent()) {
											
						List<CustomerMaster> customerMaster = customerMasterRepository
								.findBySoldToNumberAndShipToNumberAndIsActiveOrderByCreateDateDesc(optionalJoorOrder.get().getCustomerCode(),
										optionalJoorOrder.get().getShippingCode(), Boolean.TRUE);
						String custMasterChannel = null;
						
						List<String> season = joorOrderStyleItemRepository.getDistinctFashionSeasonByOrderId(joorOrderItem.getOrderID());
						
						if(customerMaster.size()> 0 && null != customerMaster.get(0).getChannelID()) {
							Optional<Channel> optChannel = channelRepository.findById(customerMaster.get(0).getChannelID());
							if(optChannel.isPresent())
								custMasterChannel = optChannel.get().getChannelName();
						}
						
						if (customerMaster.size() > 0 &&
								skuChange.getSeason().trim().equalsIgnoreCase(season.get(0)) &&
								skuChange.getLevel().trim().equalsIgnoreCase(MoicConstant.CORPORATE) ||
								(skuChange.getLevel().trim().equalsIgnoreCase(MoicConstant.CHANNEL) && skuChange.getLevelId().trim().equalsIgnoreCase(custMasterChannel)) ||
								(skuChange.getLevel().trim().equalsIgnoreCase(MoicConstant.ACCOUNT) && skuChange.getLevelId().trim().equalsIgnoreCase(customerMaster.get(0).getSoldToNumber()))
							){
							SKUChangeReport skuChangeReport = new SKUChangeReport();
							// skuChange details
							skuChangeReport.setSKU(skuChange.getOldSKU());
							skuChangeReport.setDelete(skuChange.getDelete());
							skuChangeReport.setEffectiveDate(skuChange.getEffectiveDate());
							
							if(skuChange.getNewSKU().isEmpty() || null == skuChange.getNewSKU())
								skuChangeReport.setNewSKU(skuChange.getOldSKU());
							else
								skuChangeReport.setNewSKU(skuChange.getNewSKU());
							
							skuChangeReport.setDrop_1(skuChange.getDrop_1());
							skuChangeReport.setOldSKU(skuChange.getOldSKU());
							skuChangeReport.setLevel(skuChange.getLevel());
							skuChangeReport.setLevelID(skuChange.getLevelId());
																					
							skuChangeReport.setCreateDate(new Date());
														
							// JoorOrderItem details
							skuChangeReport.setStyleColor(joorOrderItem.getItemColor());
							skuChangeReport.setStyleNumber(joorOrderItem.getItemNumber());
							skuChangeReport.setStyleName(joorOrderItem.getItemName());
							skuChangeReport.setColorName(joorOrderItem.getItemColor());
							skuChangeReport.setColorCode(joorOrderItem.getItemColorCode());
							skuChangeReport.setTotalBuyQuantity(joorOrderItem.getItemQuantity());
							skuChangeReport.setOrderTotalUSD(joorOrderItem.getItemTotalUSD());
							skuChangeReport.setWidth(null == joorOrderItem.getWidth() ? "" : joorOrderItem.getWidth());
							skuChangeReport.setSize(joorOrderItem.getItemSize());
							skuChangeReport.setEuSize(joorOrderItem.getEuSize());
							skuChangeReport.setUkSize(joorOrderItem.getUkSize());
							
							// JoorOrderStyleItem		
							skuChangeReport.setSeason(!season.isEmpty() ? season.get(0) : MoicConstant.BLANK);
							
							// JoorOrder details
							skuChangeReport.setPoNumber(optionalJoorOrder.get().getOrderId().toString());
							skuChangeReport.setStatus(optionalJoorOrder.get().getOrderType());
							skuChangeReport.setOrderId(optionalJoorOrder.get().getOrderId());
							
							// "Logic:TRUE if in PreBuy table for the given Season"
							PreBuy preBuy = preBuyRepository.findBySeasonCodeAndSKU(season.isEmpty() ? MoicConstant.BLANK : season.get(0), joorOrderItem.getCombinedSku());
							if (preBuy != null) {
								skuChangeReport.setCoo(preBuy.getCoo());

								// Use Primary when Country Of Origin is non-Asia
								// Use Secondary when Country Of Origin is Asia then Use Tertiary for everything
								// else Use Tertiary for everything else
								if (preBuy.getCoo() != null) {
									if (MoicConstant.NON_ASIA.equalsIgnoreCase(preBuy.getCoo().trim())) {
										skuChangeReport.setSalesDocType(customerMaster.get(0).getSalesOrderTypePrimary());
									} else if (MoicConstant.ASIA.equalsIgnoreCase(preBuy.getCoo().trim())) {
										skuChangeReport.setSalesDocType(customerMaster.get(0).getSalesOrderTypeSecondary());
									}
								}
							} else {
								skuChangeReport.setSalesDocType(customerMaster.get(0).getSalesOrderTypeTertiary());
							}
							
							// CustomerMaster details
							skuChangeReport.setSalesOrg(customerMaster.get(0).getSalesOrg());
							skuChangeReport.setDistributionChannel(customerMaster.get(0).getDistributionChannel());
							skuChangeReport.setDivision(customerMaster.get(0).getDivision());
							skuChangeReport.setSoldToNumber(customerMaster.get(0).getSoldToNumber());
							skuChangeReport.setShipToNumber(customerMaster.get(0).getShipToNumber());
							skuChangeReport.setSoCancelDate(customerMaster.get(0).getSoCancelDate());
							skuChangeReport.setSite(customerMaster.get(0).getSite());
							skuChangeReport.setSoldToDescription(customerMaster.get(0).getSoldToDescription());
							skuChangeReport.setCountry(customerMaster.get(0).getShippingCountry());
							skuChangeReport.setCity(customerMaster.get(0).getShippingCity());
							skuChangeReport.setRetailCurrency(customerMaster.get(0).getSuggestedRetailPriceCurrency());
							skuChangeReport.setWholesaleCurrency(customerMaster.get(0).getWholeSalePriceCurrency());
							
							//Channel details
							if(null != customerMaster.get(0).getChannelID()) {
								Optional<Channel> optChannel = channelRepository.findById(customerMaster.get(0).getChannelID());
								if(optChannel.isPresent()) {
									skuChangeReport.setFileName(optChannel.get().getChannelName());
									skuChangeReport.setChannel(optChannel.get().getChannelName());
								}
							}
							
							// Segment details
							if (null != customerMaster.get(0).getSegmentID()) {
								Optional<Segment> optSegment = segmentRepository
										.findById(customerMaster.get(0).getSegmentID());
								if (optSegment.isPresent())
									skuChangeReport.setReqSegment(optSegment.get().getSegmentCode());
							}
							
							String productSKUName =  joorOrderItem.getItemColorCode().trim();
							if(joorOrderItem.getItemColorCode().trim() != null && joorOrderItem.getItemColorCode().trim().contains(MoicConstant.SPACE)) {
								productSKUName =  joorOrderItem.getItemColorCode().trim().substring(0, joorOrderItem.getItemColorCode().trim().indexOf(MoicConstant.SPACE));
							}
							List<ProductSKU> productSKUs = productSKURepository.findByStyleIdAndSKUName(joorOrderItem.getItemNumber(), productSKUName);
							
							if(productSKUs.size() > 0) {
								// ProductSKU details
								ProductSKU productSKU = productSKUs.get(0);
								skuChangeReport.setSkuIntroDate(productSKU.getSKUIntroDate());
								skuChangeReport.setScheduledDeliveryDate(productSKU.getDeliveryStartDate());
								skuChangeReport.setSkuClass(productSKU.getSku_class());
								skuChangeReport.setMaterialName(null == productSKU.getSKUTeam() ? "" : productSKU.getSKUTeam());
								
								Optional<ProductMaster> optProductMaster = productMasterRepository
										.findById(productSKU.getPlmUniqueId());
	
								// ProductMaster details
								if (optProductMaster.isPresent()) {
									skuChangeReport.setDepartment(optProductMaster.get().getDepartment());
									skuChangeReport.setTargetCost(optProductMaster.get().getTargetCost());
									skuChangeReport.setBusinessUnit(optProductMaster.get().getDepartment());
									skuChangeReport.setRetailPrice(null == optProductMaster.get().getRetailPrice() ? 0 : optProductMaster.get().getRetailPrice());
									skuChangeReport.setWholesalePrice(null == optProductMaster.get().getWholesalePrice() ? 0 : optProductMaster.get().getWholesalePrice());
									skuChangeReport.setStyleDescription(optProductMaster.get().getStyleDescription());
								}
							}
													
							skuChangeReportRepository.save(skuChangeReport);
							LOGGER.debug("skuChangeReport :: " + skuChangeReport.toString());
						}
					}
				}
			}
		}catch(Exception e){
			LOGGER.error("Something went wrong in populateSKUChangeReport \n");
			e.printStackTrace();
		}
		LOGGER.debug("populateSKUChangeReport Ended ...");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void filterAndPopulateFinalizedBuy() {
		LOGGER.debug("filterAndPopulateFinalizedBuy Started...");
		try {
			//Populate MOQ and SKUChange Reports
			populateMOQReport();
			populateSKUChangeReport();
			
			//Truncate FinalizedBuy
			finalizedBuyRepository.deleteAll();
			LOGGER.debug("Truncated FinalizedBuy records");
						
			List<JoorOrderItem> joorOrderItems = joorOrderItemRepository.findByImpactedByMoqSkuChange(Boolean.FALSE);
			
			for (JoorOrderItem joorOrderItem : joorOrderItems) {	
				
				List<String> fashionSeasons = joorOrderStyleItemRepository.getDistinctFashionSeasonByOrderId(joorOrderItem.getOrderID());
				String fashionSeason = !fashionSeasons.isEmpty() ? fashionSeasons.get(0) : MoicConstant.BLANK;
				
				List<SkuChange> skuChanges = skuChangeRepository.findByOldSKUAndSeasonAndDelete(joorOrderItem.getCombinedSku(), fashionSeason, Boolean.FALSE);
				List<MOQ> moqs = moqRepository.findByDeleteAndSku(Boolean.FALSE, joorOrderItem.getCombinedSku());
				
				Optional<JoorOrder> optionalJoorOrder = joorOrderRepository.findById(joorOrderItem.getOrderID());
				if(optionalJoorOrder.isPresent()) {
														
					List<CustomerMaster> customerMaster = customerMasterRepository
							.findBySoldToNumberAndShipToNumberAndIsActiveOrderByCreateDateDesc(optionalJoorOrder.get().getCustomerCode(),
									optionalJoorOrder.get().getShippingCode(), Boolean.TRUE);
					
					if (customerMaster.size() > 0 ){
						String custMasterChannel = null;
						if(null != customerMaster.get(0).getChannelID()) {
							Optional<Channel> optChannel = channelRepository.findById(customerMaster.get(0).getChannelID());
							if(optChannel.isPresent())
								custMasterChannel = optChannel.get().getChannelName();
						}
						
						FinalizedBuy finalizedBuy = new FinalizedBuy(); 
												
						//MOQ details
						if (moqs.size() > 0) {
							finalizedBuy.setDelete(moqs.get(0).getDelete());
							finalizedBuy.setEffectiveDate(moqs.get(0).getEffectiveDate());
						}
						
						// skuChange details
						//setting CombinedSku for old and new sku in case of skuChanges does not exist
						finalizedBuy.setOldSKU(joorOrderItem.getCombinedSku());
						finalizedBuy.setNewSKU(joorOrderItem.getCombinedSku());
						finalizedBuy.setDrop_1(Boolean.FALSE);
						
						if(skuChanges.size() > 0) {
							for(SkuChange skuChange :skuChanges) {
								if ( customerMaster.size() > 0 &&
										skuChange.getSeason().trim().equalsIgnoreCase(fashionSeason) &&
										skuChange.getLevel().trim().equalsIgnoreCase(MoicConstant.CORPORATE) || 
										(skuChange.getLevel().trim().equalsIgnoreCase(MoicConstant.CHANNEL) && skuChange.getLevelId().trim().equalsIgnoreCase(custMasterChannel)) ||
										(skuChange.getLevel().trim().equalsIgnoreCase(MoicConstant.ACCOUNT) && skuChange.getLevelId().trim().equalsIgnoreCase(customerMaster.get(0).getSoldToNumber()))
									) {
									finalizedBuy.setOldSKU(skuChange.getOldSKU());
									
									if(skuChange.getNewSKU().isEmpty() || null == skuChange.getNewSKU())
										finalizedBuy.setNewSKU(skuChange.getOldSKU());
									else
										finalizedBuy.setNewSKU(skuChange.getNewSKU());
									
									finalizedBuy.setDrop_1(skuChange.getDrop_1());
									finalizedBuy.setLevel(skuChange.getLevel());
									finalizedBuy.setLevelID(skuChange.getLevelId());
									finalizedBuy.setDelete(skuChange.getDelete());
									finalizedBuy.setEffectiveDate(skuChange.getEffectiveDate());
									break;
								}
							}
						}
						
												
						// miscellaneous details
						finalizedBuy.setPoType("ZWMK");
						finalizedBuy.setNewness("X");
						finalizedBuy.setOrderReason("S01");
						finalizedBuy.setShippingInstruction("5");
						finalizedBuy.setCreatedBy("Uploaded Buy");
						finalizedBuy.setUploadDate(new Date());
						finalizedBuy.setCreateDate(new Date());	
						finalizedBuy.setStorageLocation("1"); // Always "1"	
						finalizedBuy.setPurchaseGroup("D02");
						
						finalizedBuy.setStyleColor(MoicUtil.getConcatenatedStyleColor(joorOrderItem.getItemNumber(), joorOrderItem.getItemColorCode()));
						finalizedBuy.setWidth(null == joorOrderItem.getWidth() ? "" : joorOrderItem.getWidth());
						finalizedBuy.setStyleNumber(null == joorOrderItem.getItemNumber() ? "" :  joorOrderItem.getItemNumber());
						
						String resultColorName = joorOrderItem.getItemColor().trim();
						if(joorOrderItem.getItemColor() != null && joorOrderItem.getItemColor().trim().contains(MoicConstant.SPACE)) {
							resultColorName = joorOrderItem.getItemColor().trim().substring(0, joorOrderItem.getItemColor().trim().lastIndexOf(MoicConstant.SPACE));
						}
						finalizedBuy.setColorName(null == resultColorName ? "" : resultColorName.trim());
						
						String resultColorCode = joorOrderItem.getItemColorCode().trim();
						if(joorOrderItem.getItemColorCode() != null && joorOrderItem.getItemColorCode().trim().contains(MoicConstant.SPACE)) {
							resultColorCode = joorOrderItem.getItemColorCode().trim().substring(0, joorOrderItem.getItemColorCode().trim().lastIndexOf(MoicConstant.SPACE));
						}
						finalizedBuy.setColorCode(null == resultColorCode ? "" : resultColorCode.trim());
						
						// JoorOrderItem details
						if (joorOrderItem.getItemQuantity() != null) {
							finalizedBuy.setTotalBuyQuantity(joorOrderItem.getItemQuantity());
						}
						finalizedBuy.setSKU(joorOrderItem.getCombinedSku());
						finalizedBuy.setOrderTotalUSD(joorOrderItem.getItemTotalUSD());
						
						// JoorOrderStyleItem
						finalizedBuy.setSeason(fashionSeason);
						
						finalizedBuy.setClassDesc(joorOrderStyleItemRepository.getStyleItemTagValue(joorOrderItem.getItemColorCode(), joorOrderItem.getItemNumber(), joorOrderItem.getItemSize(), joorOrderItem.getItemStyleId(), joorOrderItem.getOrderID(), MoicConstant.STYLE_ITEM_TAG_GROUP_CLASS));
						finalizedBuy.setSubClassDesc(joorOrderStyleItemRepository.getStyleItemTagValue(joorOrderItem.getItemColorCode(), joorOrderItem.getItemNumber(), joorOrderItem.getItemSize(), joorOrderItem.getItemStyleId(), joorOrderItem.getOrderID(), MoicConstant.STYLE_ITEM_TAG_GROUP_SUB_CLASS));
						finalizedBuy.setOrderID(joorOrderItem.getOrderID());
						finalizedBuy.setSize(joorOrderItem.getItemSize());
						finalizedBuy.setEuSize(joorOrderItem.getEuSize());
						finalizedBuy.setUkSize(joorOrderItem.getUkSize());
												
						// JoorOrder details
						if (optionalJoorOrder.isPresent()) {
							finalizedBuy.setOrderNotes(optionalJoorOrder.get().getOrderComments());
							finalizedBuy.setJoorOrderType(optionalJoorOrder.get().getOrderType());
							finalizedBuy.setScheduledDeliveryDate(optionalJoorOrder.get().getDateShipStart());
							
							Integer monthNumber = null;
							if(null != optionalJoorOrder.get().getDateShipStart())
								monthNumber = optionalJoorOrder.get().getDateShipStart().getMonth();
							finalizedBuy.setExFactMonth(null != monthNumber ? MoicUtil.getMonthName(monthNumber - 1): "");
							
							finalizedBuy.setSoCancelDate(optionalJoorOrder.get().getDateShipEnd());
							
							if (optionalJoorOrder.get().getCustomerPurchaseOrder() != null) {
								finalizedBuy.setPoNumber(optionalJoorOrder.get().getCustomerPurchaseOrder());
							}
						}
						
						PreBuy preBuy = preBuyRepository.findBySeasonCodeAndSKU(fashionSeason, joorOrderItem.getCombinedSku());
						if (preBuy != null) {
							finalizedBuy.setPreBuySKU(preBuy.getIsPreBuySKU());
							finalizedBuy.setCoo(preBuy.getCoo());

							// Use Primary when Country Of Origin is non-Asia
							// Use Secondary when Country Of Origin is Asia then Use Tertiary for everything
							// else Use Tertiary for everything else
							if (preBuy.getCoo() != null) {
								if(MoicConstant.ASIA.equalsIgnoreCase(preBuy.getCoo().trim())) {
									finalizedBuy.setSalesDocType(customerMaster.get(0).getSalesOrderTypeSecondary());
								}
								else if(MoicConstant.NON_ASIA.equalsIgnoreCase(preBuy.getCoo().trim())) {
									if(preBuy.getIsPreBuySKU().equals(Boolean.FALSE)) {
										finalizedBuy.setSalesDocType(customerMaster.get(0).getSalesOrderTypePrimary());
									}
									else {
										if(customerMaster.get(0).getImpactedByPreBuy().equals(null) || customerMaster.get(0).getImpactedByPreBuy().equals(Boolean.TRUE))
											finalizedBuy.setSalesDocType(customerMaster.get(0).getSalesOrderTypePrimary());
										else
											finalizedBuy.setSalesDocType(customerMaster.get(0).getSalesOrderTypeTertiary());
									}
								
								}
							}
						} 
						else {
							//If no preBuy exist COO is Non-Asia & Pre-Buy SKU is False
							finalizedBuy.setPreBuySKU(false);
							finalizedBuy.setCoo(MoicConstant.NON_ASIA);
							finalizedBuy.setSalesDocType(customerMaster.get(0).getSalesOrderTypePrimary());
						}
						
						// CustomerMaster details
						finalizedBuy.setVendor(customerMaster.get(0).getSalesOrg());
						finalizedBuy.setSite(customerMaster.get(0).getSite());
						finalizedBuy.setExcludeFromSOCreation(customerMaster.get(0).getExcludeFromSOCreation());
						finalizedBuy.setSalesOrg(customerMaster.get(0).getSalesOrg());
						finalizedBuy.setDistributionChannel(customerMaster.get(0).getDistributionChannel());
						finalizedBuy.setDivision(customerMaster.get(0).getDivision());
						finalizedBuy.setSoldToNumber(customerMaster.get(0).getSoldToNumber());
						finalizedBuy.setShipToNumber(customerMaster.get(0).getShipToNumber());
						finalizedBuy.setSoldToDescription(customerMaster.get(0).getSoldToDescription());
						finalizedBuy.setCountry(customerMaster.get(0).getShippingCountry());
						finalizedBuy.setCity(customerMaster.get(0).getShippingCity());
						finalizedBuy.setRetailCurrency(customerMaster.get(0).getSuggestedRetailPriceCurrency());
						finalizedBuy.setWholesaleCurrency(customerMaster.get(0).getWholeSalePriceCurrency());
						
						//Site value based on sales doc type for alt site
						if(!finalizedBuy.getSalesDocType().isEmpty() && finalizedBuy.getSalesDocType().equals(customerMaster.get(0).getSalesDocTypeforAltSite())){
							finalizedBuy.setSite(customerMaster.get(0).getAltSite());
						}
						
						if (customerMaster.get(0).getChannelID() != null && customerMaster.get(0).getChannelID() > 0) {
							Optional<Channel> channelOptional = channelRepository.findById(customerMaster.get(0).getChannelID());
							if (channelOptional.isPresent())
								finalizedBuy.setChannel(channelOptional.get().getChannelName());
						}
						if (customerMaster.get(0).getSegmentID() != null && customerMaster.get(0).getSegmentID() > 0) {
							Optional<Segment> segmentOptional = segmentRepository.findById(customerMaster.get(0).getSegmentID());
							if (segmentOptional.isPresent()) {
								finalizedBuy.setReqSegment(segmentOptional.get().getSegmentCode());
								finalizedBuy.setStockSegment(segmentOptional.get().getSegmentCode());
							}
						}
						if (customerMaster.get(0).getTarget() != null) {
							finalizedBuy.setTarget(customerMaster.get(0).getTarget());
						}
						
						//Channel details
						finalizedBuy.setChannel(null == custMasterChannel?"":custMasterChannel);
											
						// Segment details
						if (null != customerMaster.get(0).getSegmentID()) {
							Optional<Segment> optSegment = segmentRepository
									.findById(customerMaster.get(0).getSegmentID());
							if (optSegment.isPresent())
								finalizedBuy.setReqSegment(optSegment.get().getSegmentCode());
						}
						String productSKUName = joorOrderItem.getItemColorCode().trim();
						if(joorOrderItem.getItemColorCode().trim().contains(MoicConstant.SPACE))
							productSKUName = joorOrderItem.getItemColorCode().trim().substring(0, joorOrderItem.getItemColorCode().trim().indexOf(MoicConstant.SPACE));
						
						List<ProductSKU> productSKUs = productSKURepository.findByStyleIdAndSKUName(joorOrderItem.getItemNumber(), productSKUName);
						
						// JoorOrderDivision
						Optional<JoorOrderDivisions> optJoorOrderDivision = joorOrderDivisionsRepository.findById(optionalJoorOrder.get().getOrderId());
						if(optJoorOrderDivision.isPresent()) {
							finalizedBuy.setDepartmentDesc(optJoorOrderDivision.get().getDivisionName());
						}
						
						if(productSKUs.size() > 0) {
							// ProductSKU details
							ProductSKU productSKU = productSKUs.get(0);
							finalizedBuy.setSkuIntroDate(productSKU.getSKUIntroDate());
							
							finalizedBuy.setDepartment(null == productSKU.getDepartment() ? "" : productSKU.getDepartment());
							finalizedBuy.setParentsClass(null == productSKU.getSku_class() ? "" : productSKU.getSku_class());
							finalizedBuy.setSubClassCode(null == productSKU.getSubclass() ? "" : productSKU.getSubclass());
							finalizedBuy.setMaterialName(null == productSKU.getSKUTeam() ? "" : productSKU.getSKUTeam());
							
							Optional<ProductMaster> optProductMaster = productMasterRepository
									.findById(productSKU.getPlmUniqueId());
	
							// ProductMaster details
							if (optProductMaster.isPresent()) {
								finalizedBuy.setStyleDescription(
										null == optProductMaster.get().getStyleDescription() ? "" : optProductMaster.get().getStyleDescription());
								finalizedBuy.setInStoreDate(optProductMaster.get().getInStoreDate());
								finalizedBuy.setRetailPrice(null == optProductMaster.get().getRetailPrice() ? 0 : optProductMaster.get().getRetailPrice());
								finalizedBuy.setWholesalePrice(null == optProductMaster.get().getWholesalePrice() ? 0 : optProductMaster.get().getWholesalePrice());
								
								//  mapping with item_name				
								finalizedBuy.setStyleName(null ==joorOrderItem.getItemName()? "" :joorOrderItem.getItemName());
								
								if (optProductMaster.get().getTargetCost() != null) {
									finalizedBuy.setTargetCost(optProductMaster.get().getTargetCost());
								}
							}
														
							//ProductSizeScaleMoa details for UPC
							List<ProductSizeScaleMoa> productSizeScaleMoas = productSizeScaleMoaRepository.findByCombinedSku(finalizedBuy.getNewSKU());
							for(ProductSizeScaleMoa moa :productSizeScaleMoas) {
								if(null != moa.getUPC() && !moa.getUPC().isEmpty()) {
									finalizedBuy.setUpcCode(moa.getUPC());
									break;
								}
							}					
						}
						try {
							FinalizedBuyKeys finalizedBuyKeys = new FinalizedBuyKeys(finalizedBuy.getSoldToNumber(), finalizedBuy.getSeason(), finalizedBuy.getOldSKU(), finalizedBuy.getNewSKU(), finalizedBuy.getOrderID());
							if(!finalizedBuyRepository.existsById(finalizedBuyKeys)) {
								LOGGER.debug("finalizedBuy :: " + finalizedBuy);
							}
							else {
								LOGGER.debug("Skipped, JoorOrderItem already exist in finalizedbuy :: " + joorOrderItem);
							}
	
							finalizedBuyRepository.save(finalizedBuy);

						}
						catch(JpaSystemException e) {
							LOGGER.error("duplicate key violates unique constraint :: " + e.getMessage());
							e.printStackTrace();
						}
					}
				}
			}
		}catch(Exception e){
			LOGGER.error("Something went wrong in filterAndPopulateFinalizedBuy \n");
			e.printStackTrace();
		}
		LOGGER.debug("filterAndPopulateFinalizedBuy Ended...");
	}
}
