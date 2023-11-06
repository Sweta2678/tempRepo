package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.ESSSTYLECOLORExtractVO;
import com.coach.middleware.util.PMDBUtil;

public class ESSSTYLECOLORExtractRowMapper implements RowMapper<ESSSTYLECOLORExtractVO>{
	@Override
	public ESSSTYLECOLORExtractVO mapRow(ResultSet rs, int arg1) throws SQLException {
		ESSSTYLECOLORExtractVO ESSSTYLECOLORExtractVO = new ESSSTYLECOLORExtractVO();
		ESSSTYLECOLORExtractVO.setDEFAULTALIAS(PMDBUtil.checkForNull(rs.getString("DEFAULTALIAS")));
		ESSSTYLECOLORExtractVO.setSTYLE(PMDBUtil.checkForNull(rs.getString("STYLE")));
		ESSSTYLECOLORExtractVO.setSTYLEDESC(PMDBUtil.checkForNull(rs.getString("STYLEDESC")));
		ESSSTYLECOLORExtractVO.setCOLOR(PMDBUtil.checkForNull(rs.getString("COLOR")));
		ESSSTYLECOLORExtractVO.setCOLORDESC(PMDBUtil.checkForNull(rs.getString("COLORDESC")));
		ESSSTYLECOLORExtractVO.setSTYLECOLOR(PMDBUtil.checkForNull(rs.getString("STYLECOLOR")));
		ESSSTYLECOLORExtractVO.setDEPARTMENT(PMDBUtil.checkForNull(rs.getString("DEPARTMENT")));
		ESSSTYLECOLORExtractVO.setDEPARTMENTDESC(PMDBUtil.checkForNull(rs.getString("DEPARTMENTDESC")));
		ESSSTYLECOLORExtractVO.setCLASS(PMDBUtil.checkForNull(rs.getString("CLASS")));
		ESSSTYLECOLORExtractVO.setDEPTCLASS(PMDBUtil.checkForNull(rs.getString("DEPTCLASS")));
		ESSSTYLECOLORExtractVO.setCLASSDESC(PMDBUtil.checkForNull(rs.getString("CLASSDESC")));
		ESSSTYLECOLORExtractVO.setSUBCLASS(PMDBUtil.checkForNull(rs.getString("SUBCLASS")));
		ESSSTYLECOLORExtractVO.setDEPTCLASSSUB(PMDBUtil.checkForNull(rs.getString("DEPTCLASSSUB")));
		ESSSTYLECOLORExtractVO.setSUBCLASSDESC(PMDBUtil.checkForNull(rs.getString("SUBCLASSDESC")));
		ESSSTYLECOLORExtractVO.setSTYLEINTROYEAR(PMDBUtil.checkForNull(rs.getString("STYLEINTROYEAR")));
		ESSSTYLECOLORExtractVO.setSTYLEINTRODATE(PMDBUtil.checkForNull(rs.getString("STYLEINTRODATE")));
		ESSSTYLECOLORExtractVO.setINTROYEAR(PMDBUtil.checkForNull(rs.getString("INTROYEAR")));
		ESSSTYLECOLORExtractVO.setSTYLEDELETEDATE(PMDBUtil.checkForNull(rs.getString("STYLEDELETEDATE")));
		ESSSTYLECOLORExtractVO.setSTYLEDELETEYEAR(PMDBUtil.checkForNull(rs.getString("STYLEDELETEYEAR")));
		ESSSTYLECOLORExtractVO.setINTRODATE(PMDBUtil.checkForNull(rs.getString("INTRODATE")));
		ESSSTYLECOLORExtractVO.setDELETEDATE(PMDBUtil.checkForNull(rs.getString("DELETEDATE")));
		ESSSTYLECOLORExtractVO.setDELETEYEAR(PMDBUtil.checkForNull(rs.getString("DELETEYEAR")));
		ESSSTYLECOLORExtractVO.setRTLPRICE(PMDBUtil.checkForNull(rs.getString("RTLPRICE")));
		ESSSTYLECOLORExtractVO.setWHPRICE(PMDBUtil.checkForNull(rs.getString("WHPRICE")));
		ESSSTYLECOLORExtractVO.setTARGETCOST(PMDBUtil.checkForNull(rs.getString("TARGETCOST")));
		ESSSTYLECOLORExtractVO.setWORKINGCOST(PMDBUtil.checkForNull(rs.getString("WORKINGCOST")));
		ESSSTYLECOLORExtractVO.setSTDCOST(PMDBUtil.checkForNull(rs.getString("STDCOST")));
		ESSSTYLECOLORExtractVO.setCOLORGROUPDESC(PMDBUtil.checkForNull(rs.getString("COLORGROUPDESC")));
		ESSSTYLECOLORExtractVO.setCOLLECTION(PMDBUtil.checkForNull(rs.getString("COLLECTION")));
		ESSSTYLECOLORExtractVO.setCOLLECTIONDESC(PMDBUtil.checkForNull(rs.getString("COLLECTIONDESC")));
		ESSSTYLECOLORExtractVO.setSUBCOLLECTION(PMDBUtil.checkForNull(rs.getString("SUBCOLLECTION")));
		ESSSTYLECOLORExtractVO.setSUBCOLLECTIONDESC(PMDBUtil.checkForNull(rs.getString("SUBCOLLECTIONDESC")));
		ESSSTYLECOLORExtractVO.setSEASON(PMDBUtil.checkForNull(rs.getString("SEASON")));
		ESSSTYLECOLORExtractVO.setSEASONDESC(PMDBUtil.checkForNull(rs.getString("SEASONDESC")));
		ESSSTYLECOLORExtractVO.setHARDWARECOLOR(PMDBUtil.checkForNull(rs.getString("HARDWARECOLOR")));
		ESSSTYLECOLORExtractVO.setHARDWARECOLORDESC(PMDBUtil.checkForNull(rs.getString("HARDWARECOLORDESC")));
		ESSSTYLECOLORExtractVO.setMATERIAL(PMDBUtil.checkForNull(rs.getString("MATERIAL")));
		ESSSTYLECOLORExtractVO.setMATERIALDESC(PMDBUtil.checkForNull(rs.getString("MATERIALDESC")));
		ESSSTYLECOLORExtractVO.setMATERIALGROUP(PMDBUtil.checkForNull(rs.getString("MATERIALGROUP")));
		ESSSTYLECOLORExtractVO.setMATERIALGROUPDESC(PMDBUtil.checkForNull(rs.getString("MATERIALGROUPDESC")));
		ESSSTYLECOLORExtractVO.setSILHOUETTE(PMDBUtil.checkForNull(rs.getString("SILHOUETTE")));
		ESSSTYLECOLORExtractVO.setSILHOUETTEDESC(PMDBUtil.checkForNull(rs.getString("SILHOUETTEDESC")));
		ESSSTYLECOLORExtractVO.setSIGNATURETYPE(PMDBUtil.checkForNull(rs.getString("SIGNATURETYPE")));
		ESSSTYLECOLORExtractVO.setSIGNATURETYPEDESC(PMDBUtil.checkForNull(rs.getString("SIGNATURETYPEDESC")));
		ESSSTYLECOLORExtractVO.setGENDER(PMDBUtil.checkForNull(rs.getString("GENDER")));
		ESSSTYLECOLORExtractVO.setGENDERDESC(PMDBUtil.checkForNull(rs.getString("GENDERDESC")));
		ESSSTYLECOLORExtractVO.setLICENSED(PMDBUtil.checkForNull(rs.getString("LICENSED")));
		ESSSTYLECOLORExtractVO.setPLANEXCLUSION(PMDBUtil.checkForNull(rs.getString("PLANEXCLUSION")));
		ESSSTYLECOLORExtractVO.setSTATUS(PMDBUtil.checkForNull(rs.getString("STATUS")));
		ESSSTYLECOLORExtractVO.setSPECIALPRODUCT(PMDBUtil.checkForNull(rs.getString("SPECIALPRODUCT")));
		ESSSTYLECOLORExtractVO.setSPECIALPRODUCTDESC(PMDBUtil.checkForNull(rs.getString("SPECIALPRODUCTDESC")));
		ESSSTYLECOLORExtractVO.setHANDBAGSIZE(PMDBUtil.checkForNull(rs.getString("HANDBAGSIZE")));
		ESSSTYLECOLORExtractVO.setPARENTSTYLE(PMDBUtil.checkForNull(rs.getString("PARENTSTYLE")));
		ESSSTYLECOLORExtractVO.setSTYLEGROUP(PMDBUtil.checkForNull(rs.getString("STYLEGROUP")));
		ESSSTYLECOLORExtractVO.setBENCHMARKSTYLE(PMDBUtil.checkForNull(rs.getString("BENCHMARKSTYLE")));
		ESSSTYLECOLORExtractVO.setESSCHANNELEXCLUSIVE(PMDBUtil.checkForNull(rs.getString("ESSCHANNELEXCLUSIVE")));
		ESSSTYLECOLORExtractVO.setSTYLEFSINTROYEAR(PMDBUtil.checkForNull(rs.getString("STYLEFSINTROYEAR")));
		ESSSTYLECOLORExtractVO.setSTYLEFSINTRODATE(PMDBUtil.checkForNull(rs.getString("STYLEFSINTRODATE")));
		ESSSTYLECOLORExtractVO.setFSINTROYEAR(PMDBUtil.checkForNull(rs.getString("FSINTROYEAR")));
		ESSSTYLECOLORExtractVO.setSTYLEFSDELETEDATE(PMDBUtil.checkForNull(rs.getString("STYLEFSDELETEDATE")));
		ESSSTYLECOLORExtractVO.setSTYLEFSDELETEYEAR(PMDBUtil.checkForNull(rs.getString("STYLEFSDELETEYEAR")));
		ESSSTYLECOLORExtractVO.setFSINTRODATE(PMDBUtil.checkForNull(rs.getString("FSINTRODATE")));
		ESSSTYLECOLORExtractVO.setFSDELETEDATE(PMDBUtil.checkForNull(rs.getString("FSDELETEDATE")));
		ESSSTYLECOLORExtractVO.setFSDELETEYEAR(PMDBUtil.checkForNull(rs.getString("FSDELETEYEAR")));
		ESSSTYLECOLORExtractVO.setFACTORYTYPE(PMDBUtil.checkForNull(rs.getString("FACTORYTYPE")));
		return ESSSTYLECOLORExtractVO;
	}

}
