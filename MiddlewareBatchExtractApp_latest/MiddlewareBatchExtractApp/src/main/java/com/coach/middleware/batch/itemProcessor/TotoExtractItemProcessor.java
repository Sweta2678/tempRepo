/**
 * 
 */
/**
 * @author AB263159
 *
 */
package com.coach.middleware.batch.itemProcessor;

import java.util.*;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.middleware.batch.dao.TotoExtractDAO;
import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;
import com.coach.middleware.batch.dto.TotoExtractDTO;

public class TotoExtractItemProcessor implements
		ItemProcessor<TotoExtractVO, List<TotoExtractVO>> {

	private static final Logger logger = Logger
			.getLogger(TotoExtractItemProcessor.class);

	@Autowired
	public TotoExtractDAO totoExtractDao;

	@Autowired
	private DataSource dataSource;

	TotoExtractDTO totoExtractDTO = new TotoExtractDTO();

	boolean fetchAttrStoreDet = true;
	int i = 0;

	final List<TotoExtractVO> totoExtractVOList = new ArrayList();
	final Set totoExtractVOSet = new HashSet();
	List<AttributeStoreVO> attributeStoreVOList = new ArrayList();

	private String FULLPRICETOFACTORY = "fullPriceToFactoryStore";
	private String FACTORYEXCLUSIVE = "factoryExclusive";
	private String STYLETYPE = null;
	private String styleNumToProcess = null;
	private List swDeptList = new ArrayList();
	private String swDepartments;

	@Override
	public List<TotoExtractVO> process(TotoExtractVO totoExtractVO)
			throws Exception {
		boolean rowOneAdded = false;
		
		if (swDeptList.contains(totoExtractVO.getDepartment().trim())) {
			logger.info("******Stuart Weitzman department*****"+ totoExtractVO.getDepartment());
			return totoExtractVOList;
		}
	
		// logs debug message
		try{
			
		if (fetchAttrStoreDet) {

			logger.info("TotoExtract Process - Fetching Attribute store details");

			getAttributeStoreValues(totoExtractVO);
			fetchAttrStoreDet = false;
		}

		boolean fullPricetoFactory = false;
		boolean factoryExclusive = false;
		styleNumToProcess = totoExtractVO.getStyleNumber();
		
		
		if(styleNumToProcess.length() < 6){
		if (totoExtractVO.getFactoryStoreTypeProduct() != null
				&& totoExtractVO.getFactoryStoreTypeProduct().equalsIgnoreCase(
						FULLPRICETOFACTORY)) {
			fullPricetoFactory = true;

			TotoExtractVO totoExtractVOFmt = getTotoExtractVOFmt(totoExtractVO);

			logger.info("Fstyle rows for fullPriceToFactory to be written");

			for (int i = 0; i < 2; i++) {

				if (!rowOneAdded) {
					STYLETYPE = "introStyle";
					totoExtractDTO = formatLineItem(totoExtractVO, STYLETYPE);
					totoExtractVO = populateFormattedTotoExtractVO(
							totoExtractDTO, totoExtractVO, STYLETYPE);
					if (totoExtractVOSet.add(totoExtractVO.getStyleNumber())) {
						totoExtractVOList.add(totoExtractVO);
						logger.info("Writing style row for fullPriceToFactory");
					} else {
						logger.info("Not added to set duplicate record  while writing row 1--->");
					}

					rowOneAdded = true;
				} else {

					STYLETYPE = "fsIntroStyle";
					totoExtractDTO = formatLineItem(totoExtractVOFmt, STYLETYPE);
					totoExtractVOFmt = populateFormattedTotoExtractVO(
							totoExtractDTO, totoExtractVOFmt, STYLETYPE);
					if (totoExtractVOSet.add(totoExtractVOFmt.getStyleNumber())) {
						totoExtractVOList.add(totoExtractVOFmt);
						logger.info("Writing Fstyle row for fullPriceToFactory");
					} else {
						logger.info("Not added to set duplicate record while writing row 2 --->");
					}

				}

			}

		}

		if (totoExtractVO.getFactoryStoreTypeProduct().equalsIgnoreCase(
				FACTORYEXCLUSIVE)) {
			factoryExclusive = true;
			String strFormatted = "F".concat(totoExtractVO.getStyleNumber());
			totoExtractVO.setStyleNumber(strFormatted);
			STYLETYPE = "fsIntroStyle";
			totoExtractDTO = formatLineItem(totoExtractVO, STYLETYPE);
			totoExtractVO = populateFormattedTotoExtractVO(totoExtractDTO,
					totoExtractVO, STYLETYPE);
			
			if (totoExtractVOSet.add(totoExtractVO.getStyleNumber())) {
				totoExtractVOList.add(totoExtractVO);
				logger.info("writing row factoryExclusive ");
			} else {
				logger.info("Not added to set duplicate record  while writing row 1--->");
			}

		}
		}else{
			fullPricetoFactory = false;
			factoryExclusive = false;
		}
		
		if (!fullPricetoFactory && !factoryExclusive) {
			STYLETYPE = "introStyle";
			totoExtractDTO = formatLineItem(totoExtractVO, STYLETYPE);
			totoExtractVO = populateFormattedTotoExtractVO(totoExtractDTO,
					totoExtractVO, STYLETYPE);
			if (totoExtractVOSet.add(totoExtractVO.getStyleNumber())) {
				totoExtractVOList.add(totoExtractVO);
				logger.info("writing row noFactory ");
			} else {
				logger.info("Not added to set duplicate record  while writing row 1--->");
			}

		}

		logger.info("TotoExtract object list size processed for writing"
				+ totoExtractVOList.size());
		
		}catch(Exception e){
			logger.error("style "+styleNumToProcess+" processing failed", e);
		}
		return totoExtractVOList;
	}

	private TotoExtractVO getTotoExtractVOFmt(TotoExtractVO totoExtractVO) throws Exception{
		
		TotoExtractVO totoExtractVONew = new TotoExtractVO();
		
		

		totoExtractVONew.setDerived(totoExtractVO.getDerived());
		totoExtractVONew.setStyleName(totoExtractVO.getStyleName());
		String strFormatted = "F".concat(totoExtractVO.getStyleNumber());
		totoExtractVONew.setStyleNumber(strFormatted);
		totoExtractVONew.setDepartment(totoExtractVO.getDepartment());
		totoExtractVONew.setClassName(totoExtractVO.getClassName());
		totoExtractVONew.setSubClass(totoExtractVO.getSubClass());

		// totoExtractVONew.setCategory(totoExtractVO.getCategory());

		totoExtractVONew.setRetailPrice(totoExtractVO.getRetailPrice());
		totoExtractVONew.setFsIntroDate(totoExtractVO.getFsIntroDate());
		totoExtractVONew.setTotoColors(totoExtractVO.getTotoColors());
		totoExtractVONew.setTotoCountryofOrigin(totoExtractVO
				.getTotoCountryofOrigin());
		totoExtractVONew.setTotoSizes(totoExtractVO.getTotoSizes());
		totoExtractVONew.setGender(totoExtractVO.getGender());
		totoExtractVONew.setSilhouette(totoExtractVO.getSilhouette());
		totoExtractVONew.setTotoCollection(totoExtractVO.getTotoCollection());
		totoExtractVONew.setTotoLengthcm(totoExtractVO.getTotoLengthcm());
		totoExtractVONew.setTotoHeightcm(totoExtractVO.getTotoHeightcm());
		totoExtractVONew.setTotoWidthcm(totoExtractVO.getTotoWidthcm());
		totoExtractVONew.setTotoLengthcm(totoExtractVO.getTotoLengthcm());
		totoExtractVONew.setTotoHeightcm(totoExtractVO.getTotoHeightcm());
		totoExtractVONew.setTotoWidthcm(totoExtractVO.getTotoWidthcm());

		totoExtractVONew.setTotoDropLengthIn(totoExtractVO
				.getTotoDropLengthIn());
		totoExtractVONew.setTotoStrapLengthIn(totoExtractVO
				.getTotoStrapLengthIn());
		totoExtractVONew.setTotoStrapLengthInInch(totoExtractVO
				.getTotoStrapLengthInInch());
		totoExtractVONew.setTotoDropLengthInInch(totoExtractVO
				.getTotoDropLengthInInch());

		totoExtractVONew.setJewelryDimensions(totoExtractVO
				.getJewelryDimensions());
		totoExtractVONew.setKeyRingDimensions(totoExtractVO
				.getKeyRingDimensions());
		totoExtractVONew.setTotoShellMaterial(totoExtractVO
				.getTotoShellMaterial());
		totoExtractVONew.setTotoTrimMaterial(totoExtractVO
				.getTotoTrimMaterial());
		totoExtractVONew.setTotoLiningMaterial(totoExtractVO
				.getTotoLiningMaterial());

		totoExtractVONew.setUpperMaterial(totoExtractVO.getUpperMaterial());
		totoExtractVONew.setOrganizationalPanel(totoExtractVO
				.getOrganizationalPanel());
		totoExtractVONew.setTotoInteriorPocket(totoExtractVO
				.getTotoInteriorPocket());

		totoExtractVONew.setTotoExteriaorPocket(totoExtractVO
				.getTotoExteriaorPocket());
		totoExtractVONew.setClosures(totoExtractVO.getClosures());
		totoExtractVONew.setCreditCardPocket(totoExtractVO
				.getCreditCardPocket());

		totoExtractVONew.setTotoBillfoldPocket(totoExtractVO
				.getTotoBillfoldPocket());
		totoExtractVONew.setIdWindow(totoExtractVO.getIdWindow());
		totoExtractVONew.setCoin(totoExtractVO.getCoin());

		totoExtractVONew.setTotoCheckBook(totoExtractVO.getTotoCheckBook());
		totoExtractVONew.setHeelHeight(totoExtractVO.getHeelHeight());
		totoExtractVONew.setSoleType(totoExtractVO.getSoleType());

		totoExtractVONew.setAdditionalFeatures(totoExtractVO
				.getAdditionalFeatures());
		totoExtractVONew.setCareCardCm(totoExtractVO.getCareCardCm());
		totoExtractVONew.setLeatherCleaner(totoExtractVO.getLeatherCleaner());

		totoExtractVONew.setLeatherMoisturizer(totoExtractVO
				.getLeatherMoisturizer());
		totoExtractVONew.setFabricCleaner(totoExtractVO.getFabricCleaner());
		totoExtractVONew.setDampCloth(totoExtractVO.getDampCloth());
		
		return totoExtractVONew;
	}

	private void getAttributeStoreValues(TotoExtractVO totoExtractVO) {

		if (totoExtractVO != null) {
			attributeStoreVOList = totoExtractDao
					.getTotoExtractAttrKeys(totoExtractVO);
		}
	}

	/* new code */

	private TotoExtractDTO formatLineItem(TotoExtractVO totoExtractVO,
			String STYLETYPE) throws Exception {
		StringBuffer sb = new StringBuffer();

		// format brand name
		if (totoExtractVO.getDepartment() != null) {
			if (totoExtractVO.getDepartment().trim().charAt(0) == 'D') {
				sb.append("\"").append("COH").append("\"");
				totoExtractDTO.setDerived(sb.toString());
			}
		}

		// format style name
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getStyleName() != null) {
			if (totoExtractVO.getStyleName().length() > 40) {
				sb.append("\"")
						.append(totoExtractVO.getStyleName().substring(0, 40))
						.append("\"");
			} else {
				sb.append("\"").append(totoExtractVO.getStyleName())
						.append("\"");
			}
			totoExtractDTO.setStyleName(sb.toString());
		} else {
			totoExtractDTO.setStyleName(sb.append("").toString());
		}

		// format style number
		if (totoExtractVO.getStyleNumber() != null) {
			sb = flushStringBuffer(sb);
			sb.append("\"").append(totoExtractVO.getStyleNumber().trim())
					.append("\"");
			totoExtractDTO.setStyleNumber(sb.toString());
		} else {
			totoExtractDTO.setStyleNumber(sb.append("").toString());
		}

		// format category
		String deptDisplay = "";
		String classNameDisplay = "";
		String subClassDisplay = "";

		for (AttributeStoreVO attributeStoreVO : attributeStoreVOList) {
			if (attributeStoreVO.getAttributeName().trim()
					.equalsIgnoreCase("department")) {
				if (attributeStoreVO.getValueKey().trim()
						.equalsIgnoreCase(totoExtractVO.getDepartment().trim())) {
					deptDisplay = attributeStoreVO.getDisplay2() != null ? attributeStoreVO
							.getDisplay2().trim() : attributeStoreVO
							.getDisplay();

					if (deptDisplay == null || deptDisplay.isEmpty()) {
						deptDisplay = totoExtractVO.getDepartment().trim();
					}
				}
			}
			if (attributeStoreVO.getAttributeName().trim()
					.equalsIgnoreCase("styleClass")) {
				if (attributeStoreVO.getValueKey().trim()
						.equalsIgnoreCase(totoExtractVO.getClassName().trim())) {
					classNameDisplay = attributeStoreVO.getDisplay2() != null ? attributeStoreVO
							.getDisplay2().trim() : attributeStoreVO
							.getDisplay();
				}
			}
			if (attributeStoreVO.getAttributeName().trim()
					.equalsIgnoreCase("subClass")) {
				if (attributeStoreVO.getValueKey().trim()
						.equalsIgnoreCase(totoExtractVO.getSubClass().trim())) {
					subClassDisplay = attributeStoreVO.getDisplay2() != null ? attributeStoreVO
							.getDisplay2().trim() : attributeStoreVO
							.getDisplay();
				}
			}

		}

		sb = flushStringBuffer(sb);
		sb.append("\"").append(deptDisplay).append("/")
				.append(classNameDisplay).append("/").append(subClassDisplay)
				.append("\"");
		totoExtractDTO.setCategory(sb.toString());

		// format retail price

		sb = flushStringBuffer(sb);
		if (totoExtractVO.getRetailPrice() != null) {

			sb.append("\"").append(totoExtractVO.getRetailPrice().trim())
					.append("\"");
			totoExtractDTO.setRetailPrice(sb.toString());
		} else {
			totoExtractDTO.setRetailPrice("\"\"");
		}

		sb = flushStringBuffer(sb);
		if (totoExtractVO.getIntroDate() != null) {
			sb.append("\"")
					.append(ToToUtil.getIntroWithCalendarYear(totoExtractVO
							.getIntroDate().trim())).append("\"");
			totoExtractDTO.setSeasonGroupName(sb.toString());
		} else {
			totoExtractDTO.setSeasonGroupName("\"\"");
		}
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getFsIntroDate() != null) {
			sb.append("\"")
					.append(ToToUtil.getIntroWithCalendarYear(totoExtractVO
							.getFsIntroDate().trim())).append("\"");
			totoExtractDTO.setFsSeasonGroupName(sb.toString());
		} else {
			totoExtractDTO.setFsSeasonGroupName("\"\"");
		}

		// format toto colors
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoColors() != null) {
			sb.append("\"").append(totoExtractVO.getTotoColors().trim())
					.append("\"");
			totoExtractDTO.setTotoColors(sb.toString());
		} else {
			totoExtractDTO.setTotoColors("\"\"");
		}

		// format toto country of origin
		if (totoExtractVO.getTotoCountryofOrigin() != null) {
			if (!totoExtractVO.getTotoCountryofOrigin().trim()
					.equalsIgnoreCase("0")) {
				totoExtractDTO.setTotoCountryofOrigin(attributemapper(
						"totoCountryofOrigin", totoExtractVO
								.getTotoCountryofOrigin().trim()));
			} else {
				totoExtractDTO.setTotoCountryofOrigin("\"\"");

			}
		} else {
			totoExtractDTO.setTotoCountryofOrigin("\"\"");
		}

		// format toto sizes
		if (totoExtractVO.getTotoSizes() != null) {
			totoExtractDTO.setTotoSizes(attributemapper("SIZES", totoExtractVO
					.getTotoSizes().trim()));

		} else {
			totoExtractDTO.setTotoSizes("\"\"");
		}

		// format Gender
		if (totoExtractVO.getGender() != null) {
			totoExtractDTO.setGender(attributemapper("gender", totoExtractVO
					.getGender().trim()));

		} else {
			totoExtractDTO.setGender("\"\"");
		}

		// format Silhouette
		if (totoExtractVO.getSilhouette() != null) {
			totoExtractDTO.setSilhouette(attributemapper("Silhouette",
					totoExtractVO.getSilhouette().trim()));
		} else {
			totoExtractDTO.setSilhouette("\"\"");
		}

		// format TotoCollection
		if (totoExtractVO.getTotoCollection() != null) {
			totoExtractDTO.setTotoCollection(attributemapper("collection",
					totoExtractVO.getTotoCollection().trim()));
		} else {
			totoExtractDTO.setTotoCollection("\"\"");

		}

		// format TotoLengthcm
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoLengthcm() != null
				&& totoExtractVO.getTotoLengthcm().trim() != "") {
			sb.append("\"")
					.append(ToToUtil.roundup(totoExtractVO.getTotoLengthcm()
							.trim())).append("\"");
			totoExtractDTO.setTotoLengthcm(sb.toString());
		} else {
			totoExtractDTO.setTotoLengthcm("\"\"");
		}

		// format TotoHeightcm
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoHeightcm() != null) {
			sb.append("\"")
					.append(ToToUtil.roundup(totoExtractVO.getTotoHeightcm()
							.trim())).append("\"");
			totoExtractDTO.setTotoHeightcm(sb.toString());
		} else {
			totoExtractDTO.setTotoHeightcm("\"\"");
		}

		// format TotoWidthcm
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoWidthcm() != null) {
			sb.append("\"")
					.append(ToToUtil.roundup(totoExtractVO.getTotoWidthcm()
							.trim())).append("\"");
			totoExtractDTO.setTotoWidthcm(sb.toString());
		} else {
			totoExtractDTO.setTotoWidthcm("\"\"");
		}

		// format TotoLengthcm in
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoLengthcm() != null) {
			sb.append("\"")
					.append(ToToUtil.ConvertCMtoInch(totoExtractVO
							.getTotoLengthcm().trim())).append("\"");
			totoExtractDTO.setTotoLengthIn(sb.toString());
		} else {
			totoExtractDTO.setTotoLengthIn("\"\"");
		}

		// format TotoHeightcm in
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoHeightcm() != null) {
			sb.append("\"")
					.append(ToToUtil.ConvertCMtoInch(totoExtractVO
							.getTotoHeightcm().trim())).append("\"");
			totoExtractDTO.setTotoHeightIn(sb.toString());
		} else {
			totoExtractDTO.setTotoHeightIn("\"\"");
		}

		// format TotoWidthcm in
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoWidthcm() != null) {
			sb.append("\"")
					.append(ToToUtil.ConvertCMtoInch(totoExtractVO
							.getTotoWidthcm().trim())).append("\"");
			totoExtractDTO.setTotoWidthIn(sb.toString());
		} else {
			totoExtractDTO.setTotoWidthIn("\"\"");
		}

		// format JewelryDimensions

		if (totoExtractVO.getJewelryDimensions() != null) {
			totoExtractDTO.setJewelryDimensions(attributemapper(
					"JewelryDimensions", totoExtractVO.getJewelryDimensions()
							.trim()));
		} else {
			totoExtractDTO.setJewelryDimensions("\"\"");
		}

		// format KeyRingDimensions
		if (totoExtractVO.getKeyRingDimensions() != null) {
			totoExtractDTO.setKeyRingDimensions(attributemapper(
					"KEYRINGDIMENSIONS", totoExtractVO.getKeyRingDimensions()
							.trim()));
		} else {
			totoExtractDTO.setKeyRingDimensions("\"\"");
		}

		// format totoStrapLengthIn
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoStrapLengthIn() != null) {
			sb.append("\"")
					.append(ToToUtil.roundup(totoExtractVO
							.getTotoStrapLengthIn().trim())).append("\"");
			totoExtractDTO.setTotoStrapLengthIn(sb.toString());
		} else {
			totoExtractDTO.setTotoStrapLengthIn("\"\"");
		}

		// format totoDropLengthIn
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoDropLengthIn() != null) {
			sb.append("\"")
					.append(ToToUtil.roundup(totoExtractVO
							.getTotoDropLengthIn().trim())).append("\"");
			totoExtractDTO.setTotoDropLengthIn(sb.toString());
		} else {
			totoExtractDTO.setTotoDropLengthIn("\"\"");
		}

		// format totoStrapLengthIn in
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoStrapLengthIn() != null) {
			sb.append("\"")
					.append(ToToUtil.ConvertCMtoInch(totoExtractVO
							.getTotoStrapLengthIn().trim())).append("\"");
			totoExtractDTO.setTotoStrapLengthInInch(sb.toString());
		} else {
			totoExtractDTO.setTotoStrapLengthInInch("\"\"");
		}

		// format totoDropLengthIn in
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoDropLengthIn() != null) {
			sb.append("\"")
					.append(ToToUtil.ConvertCMtoInch(totoExtractVO
							.getTotoDropLengthIn().trim())).append("\"");
			totoExtractDTO.setTotoDropLengthInInch(sb.toString());
		} else {
			totoExtractDTO.setTotoDropLengthInInch("\"\"");
		}

		// format totoShellMaterial
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoShellMaterial() != null) {
			sb.append("\"").append(totoExtractVO.getTotoShellMaterial().trim())
					.append("\"");
			totoExtractDTO.setTotoShellMaterial(sb.toString());
		} else {
			totoExtractDTO.setTotoShellMaterial("\"\"");
		}

		// format totoTrimMaterial
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoTrimMaterial() != null) {
			sb.append("\"").append(totoExtractVO.getTotoTrimMaterial())
					.append("\"");
			totoExtractDTO.setTotoTrimMaterial(sb.toString());
		} else {
			totoExtractDTO.setTotoTrimMaterial("\"\"");
		}

		// format totoLiningMaterial
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoLiningMaterial() != null) {
			sb.append("\"")
					.append(totoExtractVO.getTotoLiningMaterial().trim())
					.append("\"");
			totoExtractDTO.setTotoLiningMaterial(sb.toString());
		} else {
			totoExtractDTO.setTotoLiningMaterial("\"\"");
		}

		// format upperMaterial
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getUpperMaterial() != null) {
			sb.append("\"").append(totoExtractVO.getUpperMaterial())
					.append("\"");
			totoExtractDTO.setUpperMaterial(sb.toString());
		} else {
			totoExtractDTO.setUpperMaterial("\"\"");

		}

		// format OrganizationalPanel
		if (totoExtractVO.getOrganizationalPanel() != null) {
			totoExtractDTO.setOrganizationalPanel(attributemapper(
					"OrganizationalPanel", totoExtractVO
							.getOrganizationalPanel().trim()));
		} else {
			totoExtractDTO.setOrganizationalPanel("\"\"");

		}

		// format totoInteriorPocket
		if (totoExtractVO.getTotoInteriorPocket() != null) {
			totoExtractDTO.setTotoInteriorPocket(attributemapper(
					"TotoInteriorPocket", totoExtractVO.getTotoInteriorPocket()
							.trim()));
		} else {
			totoExtractDTO.setTotoInteriorPocket("\"\"");

		}
		// format totoExteriaorPocket
		if (totoExtractVO.getTotoExteriaorPocket() != null) {
			totoExtractDTO.setTotoExteriaorPocket(attributemapper(
					"TotoExteriaorPocket", totoExtractVO
							.getTotoExteriaorPocket().trim()));
		} else {
			totoExtractDTO.setTotoExteriaorPocket("\"\"");
		}

		// format Closures
		if (totoExtractVO.getClosures() != null) {
			totoExtractDTO.setClosures(attributemapper("Closure", totoExtractVO
					.getClosures().trim()));
		} else {
			totoExtractDTO.setClosures("\"\"");
		}

		// format CreditCardPocket
		if (totoExtractVO.getCreditCardPocket() != null) {
			totoExtractDTO.setCreditCardPocket(attributemapper(
					"CreditCardPocket", totoExtractVO.getCreditCardPocket()
							.trim()));
		} else {
			totoExtractDTO.setCreditCardPocket("\"\"");

		}
		// format totoBillfoldPocket
		if (totoExtractVO.getTotoBillfoldPocket() != null) {

			String subStrBillFoldVal = totoExtractVO.getTotoBillfoldPocket()
					.indexOf(":") > 0 ? totoExtractVO
					.getTotoBillfoldPocket()
					.substring(
							totoExtractVO.getTotoBillfoldPocket().indexOf(":") + 1)
					: totoExtractVO.getTotoBillfoldPocket().trim();

			totoExtractDTO.setTotoBillfoldPocket(attributemapper(
					"TotoBillfoldPocket", subStrBillFoldVal.trim()));
		} else {
			totoExtractDTO.setTotoBillfoldPocket("\"\"");

		}

		// format IDWindow
		if (totoExtractVO.getIdWindow() != null) {
			totoExtractDTO.setIdWindow(attributemapper("IdWindow",
					totoExtractVO.getIdWindow().trim()));

		} else {
			totoExtractDTO.setIdWindow("\"\"");

		}
		// format Coin
		if (totoExtractVO.getCoin() != null) {
			totoExtractDTO.setCoin(attributemapper("Coin", totoExtractVO
					.getCoin().trim()));
		} else {
			totoExtractDTO.setCoin("\"\"");

		}

		// format totoCheckBook
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getTotoCheckBook() != null) {
			sb.append("\"").append(totoExtractVO.getTotoCheckBook().trim())
					.append("\"");
			totoExtractDTO.setTotoCheckBook(sb.toString());
		} else {
			totoExtractDTO.setTotoCheckBook("\"\"");

		}
		// format heelHeight
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getHeelHeight() != null) {
			sb.append("\"").append(totoExtractVO.getHeelHeight().trim())
					.append("\"");
			totoExtractDTO.setHeelHeight(sb.toString());
		} else {
			totoExtractDTO.setHeelHeight("\"\"");
		}

		// format soleType
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getSoleType() != null) {
			totoExtractDTO.setSoleType(attributemapper("TOTOTYPEOFSOLE",
					totoExtractVO.getSoleType().trim()));
		} else {
			totoExtractDTO.setSoleType("\"\"");
		}

		// format Additional Features
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getAdditionalFeatures() != null) {
			sb.append("\"").append(totoExtractVO.getAdditionalFeatures())
					.append("\"");
			totoExtractDTO.setAdditionalFeatures(sb.toString());
		} else {
			totoExtractDTO.setAdditionalFeatures("\"\"");
		}

		// format careCardCm
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getCareCardCm() != null) {
			sb.append("\"").append(totoExtractVO.getCareCardCm().trim())
					.append("\"");
			totoExtractDTO.setCareCardCm(sb.toString());
		} else {
			totoExtractDTO.setCareCardCm("\"\"");
		}

		// format LeatherCleaner
		sb = flushStringBuffer(sb);
		if (totoExtractVO.getLeatherCleaner() != null) {
			sb.append("\"").append(totoExtractVO.getLeatherCleaner().trim())
					.append("\"");
			totoExtractDTO.setLeatherCleaner(sb.toString());
		} else {
			totoExtractDTO.setLeatherCleaner("\"\"");
		}

		// format LeatherMoisturizer
		if (totoExtractVO.getLeatherMoisturizer() != null) {
			/*
			 * System.out.println("LeatherMoisturizer :: "+totoExtractVO.
			 * getLeatherMoisturizer());
			 */
			totoExtractDTO.setLeatherMoisturizer(attributemapper(
					"LeatherMoisturizer", totoExtractVO.getLeatherMoisturizer()
							.trim()));

		} else {
			totoExtractDTO.setLeatherMoisturizer("\"\"");

		}

		// format FabricCleaner
		if (totoExtractVO.getFabricCleaner() != null) {
			totoExtractDTO.setFabricCleander(attributemapper("FabricCleaner",
					totoExtractVO.getFabricCleaner().trim()));
		} else {
			totoExtractDTO.setFabricCleander("\"\"");

		}

		// format DampCloth
		if (totoExtractVO.getDampCloth() != null) {
			totoExtractDTO.setDampCloth(attributemapper("DampCloth",
					totoExtractVO.getDampCloth().trim()));
		} else {
			totoExtractDTO.setDampCloth("\"\"");

		}

		return totoExtractDTO;
	}

	public String attributemapper(String name, String value) {
		boolean isvalueexist = false;
		StringBuffer sb = new StringBuffer();
		for (AttributeStoreVO attributeStoreVO : attributeStoreVOList) {
			if (attributeStoreVO.getAttributeName().equalsIgnoreCase(name)) {
				if (attributeStoreVO.getValueKey().equalsIgnoreCase(value)) {

					sb.append("\"")
							.append(attributeStoreVO.getDisplay2() != null ? attributeStoreVO
									.getDisplay2().trim() : attributeStoreVO
									.getDisplay());
					sb.append("\"");
					// totoExtractDTO.setTotoCountryofOrigin(sb.toString());
					return sb.toString();
				}
			}
		}
		if (!isvalueexist) {
			sb = flushStringBuffer(sb);
			sb.append("\"").append(value).append("\"");
			return sb.toString();
		}
		if (sb.toString().equals("")) {
			return "\"\"";
		}

		return "\"\"";
	}

	private TotoExtractVO populateFormattedTotoExtractVO(
			TotoExtractDTO totoExtractDTO, TotoExtractVO totoExtractVO,
			String STYLETYPE) throws Exception{

		totoExtractVO.setDerived(totoExtractDTO.getDerived().trim());
		totoExtractVO.setStyleName(totoExtractDTO.getStyleName().trim());
		totoExtractVO.setStyleNumber(totoExtractDTO.getStyleNumber().trim());
		totoExtractVO.setCategory(totoExtractDTO.getCategory().trim());

		totoExtractVO.setRetailPrice(totoExtractDTO.getRetailPrice().trim());
		totoExtractVO.setIntroDate(totoExtractDTO.getSeasonGroupName().trim());
		totoExtractVO.setFsIntroDate(totoExtractDTO.getFsSeasonGroupName()
				.trim());
		if (STYLETYPE != null && STYLETYPE.equalsIgnoreCase("introStyle")) {
			totoExtractVO.setSeasonGroupName(totoExtractDTO
					.getSeasonGroupName().trim());

		}
		if (STYLETYPE != null && STYLETYPE.equalsIgnoreCase("FsIntroStyle")) {
			totoExtractVO.setSeasonGroupName(totoExtractDTO
					.getFsSeasonGroupName().trim());

		}

		totoExtractVO.setTotoColors(totoExtractDTO.getTotoColors().trim());
		totoExtractVO.setTotoCountryofOrigin(totoExtractDTO
				.getTotoCountryofOrigin().trim());
		totoExtractVO.setTotoSizes(totoExtractDTO.getTotoSizes().trim());
		totoExtractVO.setGender(totoExtractDTO.getGender().trim());
		totoExtractVO.setSilhouette(totoExtractDTO.getSilhouette().trim());
		totoExtractVO.setTotoCollection(totoExtractDTO.getTotoCollection()
				.trim());
		totoExtractVO.setTotoLengthcm(totoExtractDTO.getTotoLengthcm().trim());
		totoExtractVO.setTotoHeightcm(totoExtractDTO.getTotoHeightcm().trim());
		totoExtractVO.setTotoWidthcm(totoExtractDTO.getTotoWidthcm().trim());

		totoExtractVO.setTotoLengthIn(totoExtractDTO.getTotoLengthIn().trim());
		totoExtractVO.setTotoHeightIn(totoExtractDTO.getTotoHeightIn().trim());
		totoExtractVO.setTotoWidthIn(totoExtractDTO.getTotoWidthIn().trim());

		totoExtractVO.setJewelryDimensions(totoExtractDTO
				.getJewelryDimensions().trim());
		totoExtractVO.setKeyRingDimensions(totoExtractDTO
				.getKeyRingDimensions().trim());

		totoExtractVO.setTotoStrapLengthIn(totoExtractDTO
				.getTotoStrapLengthIn().trim());
		totoExtractVO.setTotoDropLengthIn(totoExtractDTO.getTotoDropLengthIn()
				.trim());
		totoExtractVO.setTotoStrapLengthInInch(totoExtractDTO
				.getTotoStrapLengthInInch().trim());
		totoExtractVO.setTotoDropLengthInInch(totoExtractDTO
				.getTotoDropLengthInInch().trim());

		totoExtractVO.setTotoShellMaterial(totoExtractDTO
				.getTotoShellMaterial().trim());
		totoExtractVO.setTotoTrimMaterial(totoExtractDTO.getTotoTrimMaterial()
				.trim());
		totoExtractVO.setTotoLiningMaterial(totoExtractDTO
				.getTotoLiningMaterial().trim());

		totoExtractVO
				.setUpperMaterial(totoExtractDTO.getUpperMaterial().trim());
		totoExtractVO.setOrganizationalPanel(totoExtractDTO
				.getOrganizationalPanel().trim());
		totoExtractVO.setTotoInteriorPocket(totoExtractDTO
				.getTotoInteriorPocket().trim());

		totoExtractVO.setTotoExteriaorPocket(totoExtractDTO
				.getTotoExteriaorPocket().trim());
		totoExtractVO.setClosures(totoExtractDTO.getClosures().trim());
		totoExtractVO.setCreditCardPocket(totoExtractDTO.getCreditCardPocket()
				.trim());

		totoExtractVO.setTotoBillfoldPocket(totoExtractDTO
				.getTotoBillfoldPocket().trim());
		totoExtractVO.setIdWindow(totoExtractDTO.getIdWindow().trim());
		totoExtractVO.setCoin(totoExtractDTO.getCoin().trim());

		totoExtractVO
				.setTotoCheckBook(totoExtractDTO.getTotoCheckBook().trim());
		totoExtractVO.setHeelHeight(totoExtractDTO.getHeelHeight().trim());
		totoExtractVO.setSoleType(totoExtractDTO.getSoleType().trim());

		totoExtractVO.setAdditionalFeatures(totoExtractDTO
				.getAdditionalFeatures());
		totoExtractVO.setCareCardCm(totoExtractDTO.getCareCardCm().trim());
		totoExtractVO.setLeatherCleaner(totoExtractDTO.getLeatherCleaner()
				.trim());

		totoExtractVO.setLeatherMoisturizer(totoExtractDTO
				.getLeatherMoisturizer().trim());
		totoExtractVO.setFabricCleaner(totoExtractDTO.getFabricCleander()
				.trim());
		totoExtractVO.setDampCloth(totoExtractDTO.getDampCloth().trim());

		return totoExtractVO;
	}

	private StringBuffer flushStringBuffer(StringBuffer sb) {
		if (sb.length() > 0) {
			sb.setLength(0);
		}

		return sb;
	}

	public TotoExtractDAO getTotoExtractDao() {
		return totoExtractDao;
	}

	public void setTotoExtractDao(TotoExtractDAO totoExtractDao) {
		this.totoExtractDao = totoExtractDao;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<TotoExtractVO> getTotoExtractVOList() {
		return totoExtractVOList;
	}
	
	public void setSWDepartmentlist() {
		if(swDepartments != null && !swDepartments.isEmpty())
		{
			String[] swDeptArray = swDepartments.split(",");
			swDeptList = Arrays.asList(swDeptArray); 
		}			
	}

	/**
	 * @return the swDepartments
	 */
	public String getSwDepartments() {
		return swDepartments;
	}

	/**
	 * @param swDepartments the swDepartments to set
	 */
	public void setSwDepartments(String swDepartments) {
		this.swDepartments = swDepartments;
	}
	
	

}