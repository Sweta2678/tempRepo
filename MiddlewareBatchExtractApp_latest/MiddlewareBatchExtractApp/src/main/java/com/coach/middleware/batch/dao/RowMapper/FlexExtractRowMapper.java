package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.FlexExtractVO;
import com.coach.middleware.batch.dao.VO.LpskuExtractVO;

public class FlexExtractRowMapper implements RowMapper<FlexExtractVO>{
	
	@Override
	public FlexExtractVO mapRow(ResultSet rs, int arg1) throws SQLException {
		FlexExtractVO lpskuExtractVO = new FlexExtractVO();
	
		lpskuExtractVO.setCREATESTAMP(rs.getString("CREATESTAMP"));
    	lpskuExtractVO.setMODIFYSTAMP(rs.getString("MODIFYSTAMP"));
    	
    	lpskuExtractVO.setSLOT(rs.getString("SLOT"));
    	lpskuExtractVO.setSTYLENUMBER(rs.getString("STYLENUMBER"));
    	lpskuExtractVO.setSKUNAME(rs.getString("SKUNAME"));
    	lpskuExtractVO.setSKUMASTERID(rs.getString("SKUMASTERID"));
       	lpskuExtractVO.setSIZESCALESIZE(rs.getString("SIZESCALESIZE"));
       	lpskuExtractVO.setSKUGROUPCODE(rs.getString("SKUGROUPCODE"));
        lpskuExtractVO.setSKUGROUPDESC(rs.getString("SKUGROUPDESC"));
        lpskuExtractVO.setABBRSTYLENAMECOLOR(rs.getString("ABBRSTYLENAMECOLOR"));
        lpskuExtractVO.setDEPARTMENT(rs.getString("DEPARTMENT"));
        lpskuExtractVO.setCCLASS(rs.getString("CCLASS"));
        lpskuExtractVO.setSUBCLASS(rs.getString("SUBCLASS"));
        lpskuExtractVO.setCOLLECTION(rs.getString("COLLECTION"));
      
        lpskuExtractVO.setCOLLECTIONDESC(rs.getString("COLLECTIONDESC"));
        
        lpskuExtractVO.setSUBCOLLECTION(rs.getString("SUBCOLLECTION"));
      		
        lpskuExtractVO.setINTRODATE(rs.getString("INTRODATE"));
        lpskuExtractVO.setFSINTRODATE(rs.getString("FSINTRODATE"));
        lpskuExtractVO.setSKUINTRODATE(rs.getString("SKUINTRODATE"));
        lpskuExtractVO.setSKUFSINTRODATE(rs.getString("SKUFSINTRODATE"));
        lpskuExtractVO.setMATERIALTYPE(rs.getString("MATERIALTYPE"));
        lpskuExtractVO.setMATERIALVALUE(rs.getString("MATERIALVALUE"));
        lpskuExtractVO.setSKUSTATUS(rs.getString("SKUSTATUS"));
        lpskuExtractVO.setSKUBOMSTATUS(rs.getString("SKUBOMSTATUS"));
        lpskuExtractVO.setRETAILPRICE(rs.getString("RETAILPRICE"));
        lpskuExtractVO.setWHPRICE(rs.getString("WHPRICE"));
        lpskuExtractVO.setTARGETCOST(rs.getString("TARGETCOST"));
        lpskuExtractVO.setMATERIALCODE(rs.getString("MATERIALCODE"));
        lpskuExtractVO.setSIGNATURETYPE(rs.getString("SIGNATURETYPE"));
        lpskuExtractVO.setSIGNATUREDESC(rs.getString("SIGNATUREDESC"));
        lpskuExtractVO.setMATERIALDESC(rs.getString("MATERIALDESC"));
        lpskuExtractVO.setLONGLEADTIMEMATERIAL(rs.getString("LONGLEADTIMEMATERIAL"));
        lpskuExtractVO.setONETIMEBUY(rs.getString("ONETIMEBUY"));
        lpskuExtractVO.setGENDER(rs.getString("GENDER"));
        lpskuExtractVO.setTEAM(rs.getString("TEAM"));
        lpskuExtractVO.setTEAMDESIGNER1(rs.getString("TEAMDESIGNER1"));
        lpskuExtractVO.setTEAMENGINEER(rs.getString("TEAMENGINEER"));
        lpskuExtractVO.setTEAMPREPRODUCTION(rs.getString("TEAMPREPRODUCTION"));
        lpskuExtractVO.setTEAMPRODUCTION(rs.getString("TEAMPRODUCTION"));
        lpskuExtractVO.setSILHOUETTECODE(rs.getString("SILHOUETTECODE"));
        lpskuExtractVO.setSILHOUETTEDESC(rs.getString("SILHOUETTEDESC"));
        lpskuExtractVO.setRENEGADE(rs.getString("RENEGADE"));
        lpskuExtractVO.setEARLYCOSTING(rs.getString("EARLYCOSTING"));
        lpskuExtractVO.setDUALDEVELOPMENT(rs.getString("DUALDEVELOPMENT"));
        lpskuExtractVO.setHANDBAGSIZE(rs.getString("HANDBAGSIZE"));
        lpskuExtractVO.setFACTORYSTORETYPEPRODUCT(rs.getString("FACTORYSTORETYPEPRODUCT"));
        lpskuExtractVO.setFACTORYSTORETYPESKU(rs.getString("FACTORYSTORETYPESKU"));
        lpskuExtractVO.setDELETEDATE(rs.getString("DELETEDATE"));
        lpskuExtractVO.setFSDELETEDATE(rs.getString("FSDELETEDATE"));
        lpskuExtractVO.setSKUDELETEDATE(rs.getString("SKUDELETEDATE"));
        lpskuExtractVO.setSKUFSDELETEDATE(rs.getString("SKUFSDELETEDATE"));
        lpskuExtractVO.setPRODUCTNAME(rs.getString("PRODUCTNAME"));
        lpskuExtractVO.setPLANEXCLUSION(rs.getString("PLANEXCLUSION"));
        lpskuExtractVO.setKEYSTYLECOSTING(rs.getString("KEYSTYLECOSTING"));
        lpskuExtractVO.setSECONDEDITSG(rs.getString("SECONDEDITSG"));
        lpskuExtractVO.setFINALEDITSG(rs.getString("FINALEDITSG"));
        lpskuExtractVO.setGCC(rs.getString("GCC"));
        lpskuExtractVO.setLICENSE(rs.getString("LICENSED"));
        lpskuExtractVO.setVENDORREF(rs.getString("VENDORREFERENCENUMBER"));
        return lpskuExtractVO;
	}


										
			
			
			
}
