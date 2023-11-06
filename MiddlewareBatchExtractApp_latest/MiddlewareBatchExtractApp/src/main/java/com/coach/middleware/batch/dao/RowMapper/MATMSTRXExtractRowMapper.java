package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.MATMSTRXExtractVO;
import com.coach.middleware.util.PMDBUtil;

public class MATMSTRXExtractRowMapper implements RowMapper<MATMSTRXExtractVO>{
	private static final Logger logger = Logger.getLogger(MATMSTRXExtractRowMapper.class);
	public static List<String> materialUniqueIDList = new ArrayList<String>();
	@Override
	public MATMSTRXExtractVO mapRow(ResultSet rs, int rowNumber) throws SQLException {
		MATMSTRXExtractVO MATMSTRXExtractVO = new MATMSTRXExtractVO();
		try{
		MATMSTRXExtractVO.setCMNUMBER(PMDBUtil.checkForNull(rs.getString("CMNUMBER")));
		MATMSTRXExtractVO.setSHORTNAME(PMDBUtil.checkForNull(rs.getString("SHORTNAME")));
		MATMSTRXExtractVO.setMATERIALTYPE(PMDBUtil.checkForNull(rs.getString("MATERIALTYPE")));
		MATMSTRXExtractVO.setSTDCOSTMATERIAL(PMDBUtil.checkForNull(rs.getString("STDCOSTMATERIAL")));
		MATMSTRXExtractVO.setSUBTYPE(PMDBUtil.checkForNull(rs.getString("SUBTYPE")));
		MATMSTRXExtractVO.setUOM(PMDBUtil.checkForNull(rs.getString("UNITOFMEASURE")));
		MATMSTRXExtractVO.setPRDLEADTIME(rs.getInt("PRDLEADTIME"));
		MATMSTRXExtractVO.setCREATETS(rs.getTimestamp("CREATEDATE"));
		MATMSTRXExtractVO.setUPDATEDATE(PMDBUtil.checkForNull(rs.getString("UPDATEDATE")));
		MATMSTRXExtractVO.setUPDATETS(rs.getTimestamp("UPDATEDATE"));
		MATMSTRXExtractVO.setVENDORNUMBER(PMDBUtil.checkForNull(rs.getString("VENDORNUMBER")));
		MATMSTRXExtractVO.setSTATUS(PMDBUtil.checkForNull(rs.getString("STATUS")));
		MATMSTRXExtractVO.setQASTATUS(PMDBUtil.checkForNull(rs.getString("QASTATUS")));
		MATMSTRXExtractVO.setINCOTERMS(PMDBUtil.checkForNull(rs.getString("INCOTERMS")));
		MATMSTRXExtractVO.setSTDCOSTCURRENCY(PMDBUtil.checkForNull(rs.getString("STDCOSTCURRENCY")));
		MATMSTRXExtractVO.setCOUNTRY(PMDBUtil.checkForNull(rs.getString("COUNTRY")));
		MATMSTRXExtractVO.setCONTENT(PMDBUtil.checkForNull(rs.getString("CONTENT")));
		MATMSTRXExtractVO.setMETALCONTENT(PMDBUtil.checkForNull(rs.getString("METALCONTENT")));
		MATMSTRXExtractVO.setMATERIALCONTENT(PMDBUtil.checkForNull(rs.getString("MATERIALCONTENT")));
		MATMSTRXExtractVO.setFABRICCONTENT(PMDBUtil.checkForNull(rs.getString("FABRICCONTENT")));
		MATMSTRXExtractVO.setTOTALCONTENT(PMDBUtil.checkForNull(rs.getString("TOTALCONTENT")));
		MATMSTRXExtractVO.setCOLORNAME(PMDBUtil.checkForNull(rs.getString("COLORNAME")));
		MATMSTRXExtractVO.setCOLORAPPROVALCOMMENTS(PMDBUtil.checkForNull(rs.getString("COLORAPPROVALCOMMENTS")));
		MATMSTRXExtractVO.setINITIALINTRODATE(PMDBUtil.checkForNull(rs.getString("INITIALINTRODATE")));
		MATMSTRXExtractVO.setSUPPLIERARTICLENUMBER(PMDBUtil.checkForNull(rs.getString("SUPPLIERARTICLENUMBER")));
		MATMSTRXExtractVO.setCUTTABLEWIDTH(PMDBUtil.checkForNull(rs.getString("CUTTABLEWIDTH")));	
		MATMSTRXExtractVO.setSURFACEFINISH(PMDBUtil.checkForNull(rs.getString("SURFACEFINISH")));
		MATMSTRXExtractVO.setMATERIALNAME(PMDBUtil.checkForNull(rs.getString("MATERIALNAME")));
		MATMSTRXExtractVO.setFACEFABRICWEIGHT(PMDBUtil.checkForNull(rs.getString("FACEFABRICWEIGHT")));
		MATMSTRXExtractVO.setESTIMATED(PMDBUtil.checkForNull(rs.getString("ESTIMATED")));
		MATMSTRXExtractVO.setVARIANT(PMDBUtil.checkForNull(rs.getString("VARIANT")));
		MATMSTRXExtractVO.setBRAND(PMDBUtil.checkForNull(rs.getString("BRAND")));
		MATMSTRXExtractVO.setMCG(PMDBUtil.checkForNull(rs.getString("MCG")));
		MATMSTRXExtractVO.setGCC(PMDBUtil.checkForNull(rs.getString("GCC")));
		MATMSTRXExtractVO.setUSERACTION(PMDBUtil.checkForNull(rs.getString("USERACTION")));
		MATMSTRXExtractVO.setFGCOLORCODE(PMDBUtil.checkForNull(rs.getString("FGCOLORCODE")));
		MATMSTRXExtractVO.setSHORTCOLORNAME(PMDBUtil.checkForNull(rs.getString("SHORTCOLORNAME")));
		MATMSTRXExtractVO.setFINISHCODE(PMDBUtil.checkForNull(rs.getString("FINISHCODE")));
		MATMSTRXExtractVO.setSOLIDCOLORCODE(PMDBUtil.checkForNull(rs.getString("SOLIDCOLORCODE")));
		MATMSTRXExtractVO.setPRINTNUMBER(PMDBUtil.checkForNull(rs.getString("PRINTNUMBER")));
		MATMSTRXExtractVO.setCOLORSUBTYPE(PMDBUtil.checkForNull(rs.getString("COLORSUBTYPE")));
		MATMSTRXExtractVO.setSUPPLIERCOLORCODE(PMDBUtil.checkForNull(rs.getString("SUPPLIERCOLORCODE")));
		String uniqueID= rs.getString("UNIQUEID");
		//System.out.println("******************************"+uniqueID);
		MATMSTRXExtractVO.setUNIQUEID(PMDBUtil.checkForNull(rs.getString("UNIQUEID")));
		if(!materialUniqueIDList.contains(uniqueID))
		materialUniqueIDList.add(uniqueID);
		}catch (Exception e) {
			logger.error("Error in MATMSTRXRowMapper : "+e.getLocalizedMessage());	
			}
		return MATMSTRXExtractVO;
	}

}
