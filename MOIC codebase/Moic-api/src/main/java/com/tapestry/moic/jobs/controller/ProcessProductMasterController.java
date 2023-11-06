package com.tapestry.moic.jobs.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.entity.JobAudit;
import com.tapestry.moic.entity.ProductGlobalPricing;
import com.tapestry.moic.entity.ProductMaster;
import com.tapestry.moic.entity.ProductSKU;
import com.tapestry.moic.entity.ProductSizeScale;
import com.tapestry.moic.entity.ProductSizeScaleMoa;
import com.tapestry.moic.entity.key.ProductSizeScaleMoaKeys;
import com.tapestry.moic.inbound.entity.ProductGlobalPricingStaging;
import com.tapestry.moic.inbound.entity.ProductMasterStaging;
import com.tapestry.moic.inbound.entity.ProductSizeScaleMoaStaging;
import com.tapestry.moic.inbound.entity.ProductSizeScaleStaging;
import com.tapestry.moic.inbound.entity.ProductSkuStaging;
import com.tapestry.moic.jobs.controller.interfaces.IProcessProductMasterController;
import com.tapestry.moic.repository.ProductGlobalPricingRepository;
import com.tapestry.moic.repository.ProductMasterRepository;
import com.tapestry.moic.repository.ProductSKURepository;
import com.tapestry.moic.repository.ProductSizeScaleMoaRepository;
import com.tapestry.moic.repository.ProductSizeScaleRepository;
import com.tapestry.moic.response.ErrorResponse;
import com.tapestry.moic.response.JobsSuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.JobAuditService;
import com.tapestry.moic.service.ProductGlobalPricingStagingService;
import com.tapestry.moic.service.ProductMasterInBoundService;
import com.tapestry.moic.service.ProductSKUInboundService;
import com.tapestry.moic.service.ProductSizeScaleInboundService;
import com.tapestry.moic.service.ProductSizeScaleMoaInboundService;
import com.tapestry.moic.utils.MoicUtil;

@Component
public class ProcessProductMasterController implements IProcessProductMasterController{

private Logger LOGGER = LoggerFactory.getLogger(ProcessProductMasterController.class);

	
	@Autowired
	ProductMasterInBoundService productMasterInboundService;
	
	@Autowired
	ProductSKUInboundService productSkuInboundService;
	
	@Autowired
	ProductSizeScaleInboundService productSizeScaleInboundService; 
	
	@Autowired
	ProductSizeScaleMoaInboundService productSizeScaleMoaInboundService;
	
	@Autowired
	ProductGlobalPricingStagingService productGlobalPricingInboundService;
	
	@Autowired
	ProductMasterRepository productMasterRepo;
	
	@Autowired
	ProductSKURepository productSkuRepo;
	
	@Autowired
	ProductSizeScaleRepository productSizeScaleRepo;
	
	@Autowired
	ProductSizeScaleMoaRepository productSizeScaleMoaRepo;
	
	@Autowired
	ProductGlobalPricingRepository globalPricingRepo;
	
	@Autowired
	JobAuditService jobAuditService;
	
	boolean isProductMasterProcessedSuccessfully;
	String existCodeProductMaster;

	
	@Override
	public MoicResponse processProductMasterStaging() {

		int recordsCount = 0;
		String errMsg = null;
		
		try {
			
			List<ProductMasterStaging> productMasterStagingsList = productMasterInboundService.getAllProductMaster();
			isProductMasterProcessedSuccessfully = true;
			if(!productMasterStagingsList.isEmpty()) {
				
				for(ProductMasterStaging productMasterStaging : productMasterStagingsList) {
					
					try {
						recordsCount++;
						ProductMaster productMaster = new ProductMaster();	
						boolean isProductExist = productMasterRepo.existsById(MoicUtil.getWholeNumberIdValue(productMasterStaging.getPlmUniqueID()));
						
						if(!isProductExist) {
							BeanUtils.copyProperties(productMasterStaging, productMaster);
							productMaster.setPlmUniqueID(MoicUtil.getWholeNumberIdValue(productMasterStaging.getPlmUniqueID()));
							productMaster.setCreateDate(new Date());
							productMaster.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
							ProductMaster addMaster = productMasterInboundService.addProductMasterInfo(productMaster);
							if(addMaster != null) {
								LOGGER.debug("Product Master record created successfully"+addMaster.getPlmUniqueID());
							}
						}
						else {
							Optional<ProductMaster> master = productMasterRepo.findById(MoicUtil.getWholeNumberIdValue(productMasterStaging.getPlmUniqueID()));
							if(master.isPresent()) {
								productMaster = master.get();
								BeanUtils.copyProperties(productMasterStaging, productMaster, "plmUniqueID");
								productMaster.setModifieDate(new Date());
								productMaster.setModifiedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
								ProductMaster updateMaster = productMasterInboundService.addProductMasterInfo(productMaster);
								if(updateMaster != null) {
									LOGGER.debug("Product Master record updated successfully"+updateMaster.getPlmUniqueID());
								}
							}
						}
					}
					catch(Exception e) {
						isProductMasterProcessedSuccessfully = false;
						LOGGER.error("Failed to process Product Master due to error:"+e.getMessage()+" plmUniqueID:"+productMasterStaging.getPlmUniqueID());
					}
				}
			}
			
			List<ProductSkuStaging> productSkuStagingsList = productSkuInboundService.getAllProdcutSkuList();
			
			if(!productSkuStagingsList.isEmpty()) {
				
				for(ProductSkuStaging productSkuStaging : productSkuStagingsList) {
					
					try {
						recordsCount++;
						ProductSKU productSKU = new ProductSKU();
						
						boolean isProductSKUExist = productSkuRepo.existsById(MoicUtil.getWholeNumberIdValue(productSkuStaging.getSKUID()));
						
						if(!isProductSKUExist) {
							BeanUtils.copyProperties(productSkuStaging, productSKU);
							productSKU.setSKUID(MoicUtil.getWholeNumberIdValue(productSKU.getSKUID()));
							productSKU.setPlmUniqueId(MoicUtil.getWholeNumberIdValue(productSKU.getPlmUniqueId()));
							productSKU.setCreateDate(new Date());
							productSKU.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
							ProductSKU addSKU = productSkuInboundService.addProductSKUInfo(productSKU);
							if(addSKU != null) {
								LOGGER.debug("Product SKU record created successfully"+addSKU.getSKUID());
							}
						}
						else {
							Optional<ProductSKU> sku = productSkuRepo.findById(MoicUtil.getWholeNumberIdValue(productSkuStaging.getSKUID()));
							if(sku.isPresent()) {
								productSKU = sku.get();
								BeanUtils.copyProperties(productSkuStaging, productSKU, "SKUID");
								productSKU.setPlmUniqueId(MoicUtil.getWholeNumberIdValue(productSKU.getPlmUniqueId()));
								productSKU.setModifieDate(new Date());
								productSKU.setModifiedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
								ProductSKU updateSKU = productSkuInboundService.addProductSKUInfo(productSKU);
								if(updateSKU != null) {
									LOGGER.debug("Product SKU record updated successfully"+updateSKU.getSKUID());
								}
							}
						}
					}
					catch(Exception e) {
						isProductMasterProcessedSuccessfully = false;
						LOGGER.error("Falied to process Product Sku due to error:"+e.getMessage()+" SKUID:"+productSkuStaging.getSKUID());
					}
				}
				
			}
			
			List<ProductSizeScaleStaging> productSizeScaleStagingsList = productSizeScaleInboundService.getAllProductSizeScaleStaging();
			
			if(!productSizeScaleStagingsList.isEmpty()) {
				
				for(ProductSizeScaleStaging productSizeScaleStaging : productSizeScaleStagingsList) {
					
					try {
						recordsCount++;
						ProductSizeScale productSizeScale = new ProductSizeScale();
						boolean isProductSizeScaleExist = productSizeScaleRepo.existsById(productSizeScaleStaging.getPlmUniqueId());
						
						if(!isProductSizeScaleExist) {
							BeanUtils.copyProperties(productSizeScaleStaging, productSizeScale, "plmUniqueID");
							productSizeScale.setSizeScaleId(productSizeScaleStaging.getPlmUniqueId());
							productSizeScale.setCreateDate(new Date());
							productSizeScale.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
							ProductSizeScale addSizeScale = productSizeScaleInboundService.addProductSizeScaleInfo(productSizeScale);
							if(addSizeScale != null) {
								LOGGER.debug("Product Sizescale record created successfully"+addSizeScale.getSizeScaleId());
							}
						}
						else {
							Optional<ProductSizeScale> sizeScale = productSizeScaleRepo.findById(productSizeScaleStaging.getPlmUniqueId());
							if(sizeScale.isPresent()) {
								productSizeScale = sizeScale.get();
								BeanUtils.copyProperties(productSizeScaleStaging, productSizeScale, "plmUniqueID");
								productSizeScale.setModifieDate(new Date());
								productSizeScale.setModifiedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
								ProductSizeScale updateSizeScale = productSizeScaleInboundService.addProductSizeScaleInfo(productSizeScale);
								if(updateSizeScale != null) {
									LOGGER.debug("Product Sizescale record updated successfully"+updateSizeScale.getSizeScaleId());
								}
							}
						}
					}
					catch(Exception e) {
						isProductMasterProcessedSuccessfully = false;
						LOGGER.error("Falied to process Product Sizescale due to error:"+e.getMessage()+ " plmUniqueID:"+productSizeScaleStaging.getPlmUniqueId());
					}
				}
			}
			
			List<ProductSizeScaleMoaStaging> productSizeScaleMoaStagingsList = productSizeScaleMoaInboundService.getAllProductSizeScaleMoaStaging();
			
			if(!productSizeScaleMoaStagingsList.isEmpty()) {
				
				for(ProductSizeScaleMoaStaging productSizeScaleMoaStaging : productSizeScaleMoaStagingsList) {
					
					try {
						recordsCount++;
						ProductSizeScaleMoa productSizeScaleMoa = new ProductSizeScaleMoa();
						ProductSizeScaleMoaKeys moaKeys1 = new ProductSizeScaleMoaKeys(productSizeScaleMoaStaging.getSizeName(), productSizeScaleMoaStaging.getSKUUniqueID().toString(), productSizeScaleMoaStaging.getStyleUniqueID().toString());
						boolean isProductSizeScaleMoaExist = productSizeScaleMoaRepo.existsById(moaKeys1);
					
						if(!isProductSizeScaleMoaExist) {
							BeanUtils.copyProperties(productSizeScaleMoaStaging, productSizeScaleMoa,"SKUUniqueID", "sizeName", "styleUniqueID");
							productSizeScaleMoa.setSizeName(productSizeScaleMoaStaging.getSizeName());
							productSizeScaleMoa.setSKUUniqueID(String.valueOf(productSizeScaleMoaStaging.getSKUUniqueID()));
							productSizeScaleMoa.setStyleUniqueID(String.valueOf(productSizeScaleMoaStaging.getStyleUniqueID()));
							productSizeScaleMoa.setCreateDate(new Date());
							productSizeScaleMoa.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
							
							ProductSKU productSKU = productSkuRepo.getBySKUID(productSizeScaleMoa.getSKUUniqueID());
							if(null != productSKU) {
								productSizeScaleMoa.setCombinedSku(MoicUtil.getCombinedSkuForMoa(productSKU.getStyleId(), 
										productSKU.getSKUName(), productSizeScaleMoa.getSizeName()));
							}
							
							ProductSizeScaleMoa addSizeScaleMoa = productSizeScaleMoaInboundService.addProductSizeScaleMoaInof(productSizeScaleMoa);
							if(addSizeScaleMoa != null) {
								LOGGER.debug("Product Sizescalemoa record created successfully:"+addSizeScaleMoa.getSKUUniqueID());
							}
						}
						else {
							ProductSizeScaleMoaKeys moaKeys = new ProductSizeScaleMoaKeys(productSizeScaleMoaStaging.getSizeName(), productSizeScaleMoaStaging.getSKUUniqueID().toString(), productSizeScaleMoaStaging.getStyleUniqueID().toString());
							Optional<ProductSizeScaleMoa> sizeScaleMoa = productSizeScaleMoaRepo.findById(moaKeys);
							if(sizeScaleMoa.isPresent()) {
								productSizeScaleMoa = sizeScaleMoa.get();
								BeanUtils.copyProperties(productSizeScaleMoaStaging, productSizeScaleMoa, "SKUUniqueID", "sizeName", "styleUniqueID");
								productSizeScaleMoa.setModifieDate(new Date());
								productSizeScaleMoa.setModifiedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
								
								ProductSKU productSKU = productSkuRepo.getBySKUID(productSizeScaleMoa.getSKUUniqueID());
								if(null != productSKU) {
									productSizeScaleMoa.setCombinedSku(MoicUtil.getCombinedSkuForMoa(productSKU.getStyleId(), 
											productSKU.getSKUName(), productSizeScaleMoa.getSizeName()));
								}
								
								ProductSizeScaleMoa updateSizeScale = productSizeScaleMoaInboundService.addProductSizeScaleMoaInof(productSizeScaleMoa);
								if(updateSizeScale != null) {
									LOGGER.debug("Product Sizescalemoa record updated successfully:"+updateSizeScale.getSKUUniqueID());
								}
								
							}
						}
					}
					catch(Exception e) {
						isProductMasterProcessedSuccessfully = false;
						e.printStackTrace();
					}
				}
			}
			
			List<ProductGlobalPricingStaging> globalPricingStagingsList = productGlobalPricingInboundService.getAllProductGlobalPricingStagList();
			
			if(!globalPricingStagingsList.isEmpty()) {
				
				for(ProductGlobalPricingStaging globalPricingStaging : globalPricingStagingsList) {
				
					try {
						recordsCount++;
						ProductGlobalPricing productGlobalPricing = new ProductGlobalPricing();
						boolean isGlobalPricingExist = globalPricingRepo.existsById(globalPricingStaging.getPlmUniqueID()); 
						
						if(!isGlobalPricingExist) {
							BeanUtils.copyProperties(globalPricingStaging, productGlobalPricing);
							productGlobalPricing.setCreateDate(new Date());
							productGlobalPricing.setCreatedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
							ProductGlobalPricing addGlobalPricing = productGlobalPricingInboundService.addProductGlobalPricingInfo(productGlobalPricing);
							if(addGlobalPricing != null) {
								LOGGER.debug("Product Globalpricing record created successfully:"+addGlobalPricing.getPlmUniqueID());
							}
						}
						else {
							Optional<ProductGlobalPricing> globalPricing = globalPricingRepo.findById(globalPricingStaging.getPlmUniqueID());
							if(globalPricing.isPresent()) {
								productGlobalPricing = globalPricing.get();
								BeanUtils.copyProperties(globalPricingStaging, productGlobalPricing, "plmUniqueID");
								productGlobalPricing.setModifieDate(new Date());
								productGlobalPricing.setModifiedBy(MoicConstant.INBOUND_RECORDS_CREATED_BY);
								ProductGlobalPricing updateGlobalPricing = productGlobalPricingInboundService.addProductGlobalPricingInfo(productGlobalPricing);
								if(updateGlobalPricing != null) {
									LOGGER.debug("Product Globalpricing record updated successfully:"+ updateGlobalPricing.getPlmUniqueID());
								}
							}
						}
					}
					catch(Exception e){
						errMsg = errMsg+" "+e.getMessage();
						isProductMasterProcessedSuccessfully = false;
						LOGGER.error("Faile to process Product Globalpricing due to error: "+e.getMessage()+" plmUniqueID"+globalPricingStaging.getPlmUniqueID());
					}
				}
				
			}
		}
		catch(Exception e) {
			isProductMasterProcessedSuccessfully = false;
			LOGGER.error("Faled to process Product tables due to error "+e.getMessage());
		}
		finally {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			JobAudit audit = setJobAuditDetail(isProductMasterProcessedSuccessfully, timestamp, errMsg, recordsCount);
			jobAuditService.saveJobAuditDetails(audit);
		}
		
		if(isProductMasterProcessedSuccessfully) {
			existCodeProductMaster = MoicConstant.EXIT_CODE_PRODUCT_MASTER_INBOUND_SUCCESS;
			return new JobsSuccessResponse(true, HttpStatus.OK, existCodeProductMaster);
		}
		else {
			existCodeProductMaster = MoicConstant.EXIT_CODE_PRODUCT_MASTER_INBOUND_FAILURE;
			return new ErrorResponse(false, HttpStatus.EXPECTATION_FAILED, existCodeProductMaster);
		}
	
	}
	
	private JobAudit setJobAuditDetail(Boolean isProductMasterProcessedSuccessfully, Timestamp timestamp, String errMsg, int recordsCount) {
		JobAudit jobAudit = new JobAudit();
		jobAudit.setJobName(MoicConstant.PRODUCT_MASTER_STAGING);
		jobAudit.setJobStatus(isProductMasterProcessedSuccessfully ? MoicConstant.EXIT_CODE_PRODUCT_MASTER_INBOUND_SUCCESS : MoicConstant.EXIT_CODE_PRODUCT_MASTER_INBOUND_FAILURE);
		jobAudit.setFileName(MoicConstant.BLANK);
		jobAudit.setExecutionDateTime(timestamp);
		jobAudit.setFileStatus(MoicConstant.BLANK);
		jobAudit.setRecordsCount(recordsCount);
		jobAudit.setExceptionMessage(errMsg);
		jobAudit.setDataSummary(MoicConstant.BLANK);
		
		return jobAudit;
	}
}