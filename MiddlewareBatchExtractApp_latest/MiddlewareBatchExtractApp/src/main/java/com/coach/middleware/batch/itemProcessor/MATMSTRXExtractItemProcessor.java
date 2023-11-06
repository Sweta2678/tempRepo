package com.coach.middleware.batch.itemProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.coach.middleware.batch.dao.LPPRICESExtractDao;
import com.coach.middleware.batch.dao.MATMSTRXExtractDao;
import com.coach.middleware.batch.dao.RowMapper.MATMSTRXExtractRowMapper;
import com.coach.middleware.batch.dao.RowMapper.MaterialPriceRowMapper;
import com.coach.middleware.batch.dao.VO.LPPRICESExtractVO;
import com.coach.middleware.batch.dao.VO.MATMSTRXExtractVO;
import com.coach.middleware.batch.dao.VO.MaterialPriceVo;
import com.coach.middleware.util.MaterialConstants;
import com.coach.middleware.util.PMDBUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class MATMSTRXExtractItemProcessor implements
		ItemProcessor<MATMSTRXExtractVO, MATMSTRXExtractVO> {
	public static MATMSTRXExtractDao MATMSTRXExtractDao;
	private DataSource dataSource;
	public static int index = 0;
	public static List<MATMSTRXExtractVO> MATMSTRXExtractVOList = new ArrayList();
	private HashMap<String, MaterialPriceVo> priceMap = new HashMap<String, MaterialPriceVo>();
	private static final Logger logger = Logger
			.getLogger(MATMSTRXExtractItemProcessor.class);

	@Override
	public MATMSTRXExtractVO process(MATMSTRXExtractVO item) throws Exception {
		if (index == 0) {
			logger.info("MATMSTRXExtractItemProcessor.process started");
			MATMSTRXExtractDao.getAttributeFromLookups();
			logger.info("MATMSTRXExtractItemProcessor.process getAttributeFromLookups completed");
			MATMSTRXExtractDao.getVariantFromLookups();
			logger.info("MATMSTRXExtractItemProcessor.process getVariantFromLookups completed");
			priceMap = MATMSTRXExtractDao
					.getStandardCost(MATMSTRXExtractRowMapper.materialUniqueIDList);
			index = 1;
		}
		StringBuffer sb = new StringBuffer();
		getBusinessRulesForNonParent(item);
		return item;
	}

	private void getBusinessRulesForNonParent(MATMSTRXExtractVO item) {
		logger.info("MATMSTRXExtractItemProcessor.getBusinessRulesForNonParent cmnumber :" + item.getCMNUMBER());
		try {
			String qastatus = MATMSTRXExtractDao.getStatusLookups(
					item.getQASTATUS(), "QASTATUS");
			String status = MATMSTRXExtractDao.getStatusLookups(
					item.getSTATUS(), "MSCStatus");
			item.setSTATUS(PMDBUtil.checkForNull(status));
			item.setQASTATUS(PMDBUtil.checkForNull(qastatus));
			item.setMATERIALDESC(getMaterialDesc(item));
			String subType = MATMSTRXExtractDao.getAttributeLookups(
					item.getSUBTYPE(), "MATERIALSUBTYPE",
					item.getMATERIALDESC());
			String surfaceFinish = MATMSTRXExtractDao.getAttributeLookups(
					item.getSURFACEFINISH(), "SURFACEFINISH",
					item.getMATERIALDESC());
			String variant = MATMSTRXExtractDao.getVariantLookups(
					item.getVARIANT(), item.getMATERIALDESC());
			item.setSUBTYPELKP(PMDBUtil.checkForNull(subType));
			item.setSURFACEFINISH(PMDBUtil.checkForNull(surfaceFinish));
			item.setVARIANT(PMDBUtil.checkForNull(variant));
			String mtype = MaterialConstants.EMPTY;
			int leadtime;
			leadtime = item.getPRDLEADTIME() * 7;
			String uom = MaterialConstants.EMPTY;
			if (priceMap.containsKey(item.getUNIQUEID())) {
				item.setSTDCOSTMATERIAL(priceMap.get(item.getUNIQUEID())
						.getPrice());
				item.setFROMDATE(priceMap.get(item.getUNIQUEID()).getFromDate());
			} else {
				item.setSTDCOSTMATERIAL("0");
				item.setFROMDATE("");
			}
			getColorCode(item);
			getCMValuesFOrNonParent(item);

			if (item.getCMNUMBER().startsWith(
					MaterialConstants.CMNUM_START_WITH_L)) {
				mtype = MaterialConstants.ZLEA;
				uom = item.getUOM();
			} else {
				mtype = MaterialConstants.ROH;
				uom = item.getUOM();
			}
			if (item.getCREATETS() != null)
				item.setCREATEDATE(PMDBUtil.convertTimestampToDate(item
						.getCREATETS()));
			if (item.getUPDATETS() != null)
				item.setUPDATEDATE(PMDBUtil.convertTimestampToDate(item
						.getUPDATETS()));

			item.setMATERIALTYPE(mtype);
			item.setUOM(uom.toUpperCase());
			item.setPRDLEADTIME(leadtime);
			item.setCUTTABLEWIDTH(PMDBUtil.underscore(item.getCUTTABLEWIDTH()));
			item.setSUBTYPE(PMDBUtil.toUpperCase(item.getSUBTYPE()));
			item.setESTIMATED(PMDBUtil.checkEstimate(PMDBUtil.getEstimate(item
					.getESTIMATED())));
			logger.info("MATMSTRXExtractItemProcessor.getBusinessRulesForNonParent ends-");
		} catch (Exception e) {
			logger.error("Error in MATMSTRXExtractItemProcessor.getBusinessRulesForNonParent  : "
					+ e.getMessage());
		}

	}

	private void getColorCode(MATMSTRXExtractVO item) {
		
		if (item.getCOLORSUBTYPE().equalsIgnoreCase(
				MaterialConstants.MATERIAL_PRINTS)) {
			item.setCOLORCODE(item.getPRINTNUMBER());
		} else if (item.getCOLORSUBTYPE().contains(
				MaterialConstants.MATERIAL_HARDWARE)) {
			item.setCOLORCODE(item.getFINISHCODE());
		} else if (item.getCOLORSUBTYPE().contains(
				MaterialConstants.MATERIAL_SOLIDS)) {
			item.setCOLORCODE(item.getSOLIDCOLORCODE());
		}
		logger.info("MATMSTRXExtractItemProcessor.getColorCode : " + item.getCOLORCODE());
		/*else{
			item.setCOLORCODE(PMDBUtil.checkColorCodeNull(item.getFGCOLORCODE()));
		}*/
	}

	private String getMaterialDesc(MATMSTRXExtractVO item) {
		try{
		String matType = item.getCMNUMBER().substring(0, 1);
		String matDesc = null;
		if (MaterialConstants.CMNUM_START_WITH_T.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_TEXTILE;
			item.setCONTENT2(getLookupValue(
					PMDBUtil.checkForNull(item.getFABRICCONTENT()), matDesc));
		} else if (MaterialConstants.CMNUM_START_WITH_E.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_EDGESTAIN;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_N.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_ENAMEL;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_F.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_FILLER;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_H.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_HARDWARE;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_L.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_LEATHER;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_M.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_MISC;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_P.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_PACKAGING;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_X.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_PRE_CONCEPT;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_S.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_THREAD;
			item.setCONTENT2("");
		} else if (MaterialConstants.CMNUM_START_WITH_W.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_WOVENTRIM;
			item.setCONTENT2(getLookupValue(
					PMDBUtil.checkForNull(item.getTOTALCONTENT()), matDesc));
		} else if (MaterialConstants.CMNUM_START_WITH_Z.equals(matType)) {
			matDesc = MaterialConstants.MATERIAL_ZIPPER;
			item.setCONTENT2("");
		}
		
		logger.info("MATMSTRXExtractItemProcessor.getMaterialDesc :"+matDesc);
		return matDesc.trim();
		}catch(Exception e){
			logger.error("MATMSTRXExtractItemProcessor.getMaterialDesc Error : "+e.getLocalizedMessage());
			return "";
		}
		
	}

	private void getCMValuesFOrNonParent(MATMSTRXExtractVO item) {
		try {
			if (item.getCMNUMBER().endsWith("PT")) {
				item.setCMNUMBERANDCOLORCODE1(item.getCMNUMBER().concat(
						PMDBUtil.space(MaterialConstants.CMNUM_LENGTH
								- item.getCMNUMBER().length())));

			} else {
				item.setCMNUMBERANDCOLORCODE1(item
						.getCMNUMBER()
						.concat(MaterialConstants.HYPHEN
								.concat(item.getCOLORCODE())
								.concat(PMDBUtil.space(MaterialConstants.CMNUM_LENGTH
										- (item.getCMNUMBER())
												.concat(MaterialConstants.HYPHEN)
												.concat(item.getCOLORCODE())
												.length()))));
			}
			if (item.getCMNUMBER().endsWith("PT")) {

				// sb.append(PMDBUtil.space(MaterialConstants.CMNUM_LENGTH));

			} else if (item.getCMNUMBER().startsWith(
					MaterialConstants.CMNUM_START_WITH_T)) {

				if (item.getCOLORCODE().equals(MaterialConstants.EMPTY)
						|| item.getCOLORCODE() == null) {
					item.setCMNUMBERANDCOLORCODE2(item.getCMNUMBER().concat(
							PMDBUtil.space(MaterialConstants.CMNUM_LENGTH
									- item.getCMNUMBER().trim().length())));
				} else {
					item.setCMNUMBERANDCOLORCODE2(item
							.getCMNUMBER()
							.substring(0, item.getCMNUMBER().length() - 2)
							.concat(item.getCOLORCODE())
							.concat(PMDBUtil
									.space(MaterialConstants.CMNUM_LENGTH
											- (item.getCMNUMBER()
													.substring(
															0,
															item.getCMNUMBER()
																	.length() - 2)
													.length() + (item
													.getCOLORCODE().length())))));
				}
			} else {
				item.setCMNUMBERANDCOLORCODE2(item
						.getCMNUMBER()
						.trim()
						.concat(PMDBUtil.space(MaterialConstants.CMNUM_LENGTH
								- item.getCMNUMBER().length())));
			}
			logger.info("MATMSTRXExtractItemProcessor.getCMValuesFOrNonParent :"+item.getCMNUMBERANDCOLORCODE2());
		} catch (Exception e) {
			logger.error("Error on appending cmnumber with color code: "
					+ e.getMessage());
		}
	}

	public static String getLookupValue(String contentValue, String matDesc) {
		logger.info("MATMSTRXExtractItemProcessor.getLookupValue :" + contentValue + " for "
				+ matDesc);
		StringBuffer sb = new StringBuffer();
		String contentValue2 = contentValue.replace("|~*~|", ", ");
		StringTokenizer st = new StringTokenizer(contentValue2, ", ");
		while (st.hasMoreElements()) {
			String sub = st.nextElement().toString();
			StringTokenizer st2 = new StringTokenizer(sub, " ");
			while (st2.hasMoreElements()) {
				String key = st2.nextToken();
				if (!key.contains("%")) {
					String display = MATMSTRXExtractDao.getStatusLookups(key,
							"FABRICCONTENT");
					contentValue2 = contentValue2.replace(key, display);
				}
			}
		}
		if (contentValue2.trim().endsWith(",")) {
			contentValue2 = contentValue2.substring(0,
					contentValue2.lastIndexOf(","));
		}
		return contentValue2;
	}

	public MATMSTRXExtractDao getMATMSTRXExtractDao() {
		return MATMSTRXExtractDao;
	}

	public void setMATMSTRXExtractDao(MATMSTRXExtractDao mATMSTRXExtractDao) {
		MATMSTRXExtractDao = mATMSTRXExtractDao;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
