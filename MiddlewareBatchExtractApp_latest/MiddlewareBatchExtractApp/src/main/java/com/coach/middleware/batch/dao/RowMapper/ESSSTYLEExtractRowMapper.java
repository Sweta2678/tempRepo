package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.ESSSTYLEExtractVO;
import com.coach.middleware.util.PMDBUtil;

public class ESSSTYLEExtractRowMapper  implements RowMapper<ESSSTYLEExtractVO>{
	@Override
	public ESSSTYLEExtractVO mapRow(ResultSet rs, int arg1) throws SQLException {
	ESSSTYLEExtractVO ESSSTYLEExtractVO = new com.coach.middleware.batch.dao.VO.ESSSTYLEExtractVO();
	ESSSTYLEExtractVO.setSTYLE(PMDBUtil.checkForNull(rs.getString("STYLE")));
	ESSSTYLEExtractVO.setDEFAULTALIAS(PMDBUtil.checkForNull(rs.getString("DEFAULTALIAS")));
	ESSSTYLEExtractVO.setDEPARTMENT(PMDBUtil.checkForNull(rs.getString("DEPARTMENT")));
	ESSSTYLEExtractVO.setDEPARTMENTDESC(PMDBUtil.checkForNull(rs.getString("DEPARTMENTDESC")));
	ESSSTYLEExtractVO.setCLASS(PMDBUtil.checkForNull(rs.getString("CLASS")));
	ESSSTYLEExtractVO.setDEPTCLASS(PMDBUtil.checkForNull(rs.getString("DEPTCLASS")));
	ESSSTYLEExtractVO.setCLASSDESC(PMDBUtil.checkForNull(rs.getString("CLASSDESC")));
	ESSSTYLEExtractVO.setSUBCLASS(PMDBUtil.checkForNull(rs.getString("SUBCLASS")));
	ESSSTYLEExtractVO.setDEPTCLASSSUB(PMDBUtil.checkForNull(rs.getString("DEPTCLASSSUB")));
	ESSSTYLEExtractVO.setSUBCLASSDESC(PMDBUtil.checkForNull(rs.getString("SUBCLASSDESC")));
	ESSSTYLEExtractVO.setINTROYEAR(PMDBUtil.checkForNull(rs.getString("INTROYEAR")));
	ESSSTYLEExtractVO.setINTRODATE(PMDBUtil.checkForNull(rs.getString("INTRODATE")));
	ESSSTYLEExtractVO.setDELETEDATE(PMDBUtil.checkForNull(rs.getString("DELETEDATE")));
	ESSSTYLEExtractVO.setRTLPRICE(PMDBUtil.checkForNull(rs.getString("RTLPRICE")));
	ESSSTYLEExtractVO.setWHPRICE(PMDBUtil.checkForNull(rs.getString("WHPRICE")));
	ESSSTYLEExtractVO.setTARGETCOST(PMDBUtil.checkForNull(rs.getString("TARGETCOST")));
	ESSSTYLEExtractVO.setWORKINGCOST(PMDBUtil.checkForNull(rs.getString("WORKINGCOST")));
	ESSSTYLEExtractVO.setSTDCOST(PMDBUtil.checkForNull(rs.getString("STDCOST")));
	ESSSTYLEExtractVO.setCOLLECTION(PMDBUtil.checkForNull(rs.getString("COLLECTION")));
	ESSSTYLEExtractVO.setCOLLECTIONDESC(PMDBUtil.checkForNull(rs.getString("COLLECTIONDESC")));
	ESSSTYLEExtractVO.setSEASON(PMDBUtil.checkForNull(rs.getString("SEASON")));
	ESSSTYLEExtractVO.setSEASONDESC(PMDBUtil.checkForNull(rs.getString("SEASONDESC")));
	ESSSTYLEExtractVO.setSIGNATURETYPE(PMDBUtil.checkForNull(rs.getString("SIGNATURETYPE")));
	ESSSTYLEExtractVO.setSIGNATURETYPEDESC(PMDBUtil.checkForNull(rs.getString("SIGNATURETYPEDESC")));
	ESSSTYLEExtractVO.setHARDWARECOLOR(PMDBUtil.checkForNull(rs.getString("HARDWARECOLOR")));
	ESSSTYLEExtractVO.setHARDWARECOLORDESC(PMDBUtil.checkForNull(rs.getString("HARDWARECOLORDESC")));
	ESSSTYLEExtractVO.setMATERIAL(PMDBUtil.checkForNull(rs.getString("MATERIAL")));
	ESSSTYLEExtractVO.setMATERIALDESC(PMDBUtil.checkForNull(rs.getString("MATERIALDESC")));
	ESSSTYLEExtractVO.setMATERIALGROUP(PMDBUtil.checkForNull(rs.getString("MATERIALGROUP")));
	ESSSTYLEExtractVO.setMATERIALGROUPDESC(PMDBUtil.checkForNull(rs.getString("MATERIALGROUPDESC")));
	ESSSTYLEExtractVO.setSILHOUETTE(PMDBUtil.checkForNull(rs.getString("SILHOUETTE")));
	ESSSTYLEExtractVO.setSILHOUETTEDESC(PMDBUtil.checkForNull(rs.getString("SILHOUETTEDESC")));
	ESSSTYLEExtractVO.setGENDER(PMDBUtil.checkForNull(rs.getString("GENDER")));
	ESSSTYLEExtractVO.setGENDERDESC(PMDBUtil.checkForNull(rs.getString("GENDERDESC")));
	ESSSTYLEExtractVO.setUPDATED(PMDBUtil.checkForNull(rs.getString("UPDATED")));
	ESSSTYLEExtractVO.setSPECIALPRODUCT(PMDBUtil.checkForNull(rs.getString("SPECIALPRODUCT")));
	ESSSTYLEExtractVO.setHANDBAGSIZE(PMDBUtil.checkForNull(rs.getString("HANDBAGSIZE")));
	ESSSTYLEExtractVO.setSPECIALPRODUCTDESC(PMDBUtil.checkForNull(rs.getString("SPECIALPRODUCTDESC")));
	ESSSTYLEExtractVO.setPARENTSTYLE(PMDBUtil.checkForNull(rs.getString("PARENTSTYLE")));
	ESSSTYLEExtractVO.setSTYLEGROUP(PMDBUtil.checkForNull(rs.getString("STYLEGROUP")));
	ESSSTYLEExtractVO.setBENCHMARKSTYLE(PMDBUtil.checkForNull(rs.getString("BENCHMARKSTYLE")));
	ESSSTYLEExtractVO.setCHANNELEXCLUSIVE(PMDBUtil.checkForNull(rs.getString("CHANNELEXCLUSIVE")));
	ESSSTYLEExtractVO.setFSINTROYEAR(PMDBUtil.checkForNull(rs.getString("FSINTROYEAR")));
	ESSSTYLEExtractVO.setFSINTRODATE(PMDBUtil.checkForNull(rs.getString("FSINTRODATE")));
	ESSSTYLEExtractVO.setFSDELETEDATE(PMDBUtil.checkForNull(rs.getString("FSDELETEDATE")));
	ESSSTYLEExtractVO.setFACTORYTYPE(PMDBUtil.checkForNull(rs.getString("FACTORYTYPE")));
	ESSSTYLEExtractVO.setDELETEYEAR(PMDBUtil.checkForNull(rs.getString("DELETEYEAR")));
	ESSSTYLEExtractVO.setPLANEXCLUSION(PMDBUtil.checkForNull(rs.getString("PLANEXCLUSION")));
	ESSSTYLEExtractVO.setSTATUS(PMDBUtil.checkForNull(rs.getString("STATUS")));
	ESSSTYLEExtractVO.setFSDELETEYEAR(PMDBUtil.checkForNull(rs.getString("FSDELETEYEAR")));
	ESSSTYLEExtractVO.setLICENSED(PMDBUtil.checkForNull(rs.getString("LICENSED")));
	ESSSTYLEExtractVO.setSUBCOLLECTION(PMDBUtil.checkForNull(rs.getString("SUBCOLLECTION")));
	ESSSTYLEExtractVO.setSUBCOLLECTIONDESC(PMDBUtil.checkForNull(rs.getString("SUBCOLLECTIONDESC")));
	return ESSSTYLEExtractVO;
}}
