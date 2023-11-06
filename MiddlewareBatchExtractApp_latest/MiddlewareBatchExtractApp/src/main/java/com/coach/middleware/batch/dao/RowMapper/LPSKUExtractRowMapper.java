package com.coach.middleware.batch.dao.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;


import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.RunBatch;
import com.coach.middleware.util.CoachUtil;
import com.coach.middleware.batch.dao.VO.LpskuExtractVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;


public class LPSKUExtractRowMapper implements RowMapper<LpskuExtractVO> {

		
	    public LpskuExtractVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	 
	    	LpskuExtractVO lpskuExtractVO = new LpskuExtractVO();
	    	lpskuExtractVO.setCollectioncode(rs.getString("collection"));
	    	
	    	lpskuExtractVO.setSizeScaleID(rs.getString("sizescalename"));
	    	
	    	
	    	
	    	
	    	lpskuExtractVO.setCREATESTAMP(rs.getString("createdate"));
	    	lpskuExtractVO.setMODIFYSTAMP(rs.getString("modifydate"));
	    	lpskuExtractVO.setSkumaterial(rs.getString("skumaterial"));
	    	
	    	
	    	lpskuExtractVO.setClassCode(rs.getString("classcode"));
	    	lpskuExtractVO.setMode(rs.getString("exemode"));
	    	lpskuExtractVO.setSkuUniqueid(rs.getString("skuuniqueid"));
	    	lpskuExtractVO.setProdUniqueid(rs.getString("produniqueid"));
	    	lpskuExtractVO.setSkuStypeid(rs.getString("skustyleid"));
	    	lpskuExtractVO.setSkuname(rs.getString("skuname"));
	       	lpskuExtractVO.setSlotNumber(rs.getString("slot"));
	    	lpskuExtractVO.setStyleNumber(rs.getString("Stylenumber"));
	    	lpskuExtractVO.setAbbreviatedStyleSKUFullName(rs.getString("abbrStyleName"));
	    	lpskuExtractVO.setDepartmentCode(rs.getString("department"));
	    	lpskuExtractVO.setSubClassCode(rs.getString("subClass"));
	    	
	    	
	    	lpskuExtractVO.setSubCollectionCode(rs.getString("subCollection"));
	    	
	    	
	    	
	    	
	    	lpskuExtractVO.setGenderCode(rs.getString("gender"));
	    	
	    	lpskuExtractVO.setSilhouetteCode(rs.getString("silhouette"));
	    	lpskuExtractVO.setFactoryStoreTypeProduct(rs.getString("factorystoreTypeProduct"));
                                                             
	    	lpskuExtractVO.setProductName(rs.getString("productName"));
	    	lpskuExtractVO.setPlanExclusion(rs.getString("planExclusion"));
	    	lpskuExtractVO.setShortcolorcode(rs.getString("skuCOLORUNIQUEID"));
	    		
	    	lpskuExtractVO.setMerchSKUstatus(rs.getString("skustatus"));
	    	lpskuExtractVO.setbOMstatus(rs.getString("skuBomStatus"));
	    	
	    	
	    	//****
	    	
	    	
	    	
	    	lpskuExtractVO.setTeam(rs.getString("team"));
	    	lpskuExtractVO.setDesigner(rs.getString("teamDesigner1"));
	    	lpskuExtractVO.setEngineer(rs.getString("teamEngineer"));
	    	lpskuExtractVO.setProdDevelopment(rs.getString("teamPreProduction"));
	    	lpskuExtractVO.setProductionMgr(rs.getString("teamProduction"));
	    	
	    	lpskuExtractVO.setHandbagSize(rs.getString("HandbagSize"));
	    	
	    	
	    	lpskuExtractVO.setSkuFactoryStoreType(rs.getString("factorystoreTypeSKU"));
	    	
	    	
	    	
	    	
	    	
	    	lpskuExtractVO.setProductName(rs.getString("productName"));
	    	lpskuExtractVO.setPlanExclusion(rs.getString("planExclusion"));
	     	lpskuExtractVO.setKeyStyleCosting(rs.getString("keystylecosting"));
	    	
	 	    lpskuExtractVO.setFirstEdit("");
	    	lpskuExtractVO.setSecondEdit("");
	    	lpskuExtractVO.setGcc(rs.getString("gcc"));
	    	lpskuExtractVO.setLicense(rs.getString("license"));
	    	
	    	
	    	//****
	    	
	    	

	    	
	    	
	    	lpskuExtractVO.setMaterialTypeCode(rs.getString("material"));
	    	
	    	
	    	lpskuExtractVO.setOriginalTargetcost(rs.getString("TARGETCOST"));
	    	lpskuExtractVO.setWhoePrice(rs.getString("WHOLESALEPRICE"));
	    	lpskuExtractVO.setRetailPrice(rs.getString("retailprice"));
	    	
	    	lpskuExtractVO.setSkuSignatureTypeCode(rs.getString("skuSIGNATURETYPE"));
	    	lpskuExtractVO.setPmSignatureTypeCode(rs.getString("pmSIGNATURETYPE"));
	    	
	    	
	    	lpskuExtractVO.setRenegade(rs.getString("Renegade"));
	    	
	    	lpskuExtractVO.setSkumasterid(rs.getString("Skumasterid"));
	    	
	    	
	    	
	    	
	    	
	    	
//	 ****   	
	    	
	    	
	    	lpskuExtractVO.setsKUIntroDate(rs.getString("skuintroDate"));
	    	lpskuExtractVO.setStyleIntroDate(rs.getString("introDate"));
	    	
	    	lpskuExtractVO.setsKUFSIntroDate(rs.getString("skufsIntroDate"));
	    	lpskuExtractVO.setStyleFSIntroDate(rs.getString("fsIntroDate"));
		    
	    	
	    	lpskuExtractVO.setStyleDeleteDate(rs.getString("deleteDate"));
	    	lpskuExtractVO.setsKUDeleteDate(rs.getString("skudeleteDate"));
	    	
	    	lpskuExtractVO.setsKUFSDeleteDate(rs.getString("skufsDeleteDate"));
	    	lpskuExtractVO.setStyleFSDeleteDate(rs.getString("fsDeleteDate"));
	    	
	    	
	    	String team = rs.getString("skuteam");
	    	if(team == null || team.trim().equals("")){
	    		team = rs.getString("team");
	    	}
	    	lpskuExtractVO.setTeam(team);
	    	
	    	
	    	
	    	
	    	lpskuExtractVO.setDualDevelopment(rs.getString("dualDevelopment"));
	    	if(lpskuExtractVO.getDualDevelopment() == null || lpskuExtractVO.getDualDevelopment().trim().equals("")){
	    		String val = rs.getString("productdualDevelopment");
	    		if(val !=null)
	    		{
	    			lpskuExtractVO.setDualDevelopment(val.toUpperCase());
	    		}
	    	}else{
	    		lpskuExtractVO.setDualDevelopment(lpskuExtractVO.getDualDevelopment().toUpperCase());
	    	}
	    	
	    	
	    	
	    	lpskuExtractVO.setOnetimeBuy(rs.getString("oneTimeBuy"));
	    	if(lpskuExtractVO.getOnetimeBuy() == null || lpskuExtractVO.getOnetimeBuy().trim().equals("")){
	    		String val = rs.getString("productoneTimeBuy");
	    		if(val !=null)
	    		{
	    			lpskuExtractVO.setOnetimeBuy(val.toUpperCase());
	    		}
	    	}else{
	    		lpskuExtractVO.setOnetimeBuy(lpskuExtractVO.getOnetimeBuy().toUpperCase());
	    	}
	    	
	    	
	    	
	    	lpskuExtractVO.setLongLeadTimeMaterial(rs.getString("longLeadTimeMaterial"));
	    	if(lpskuExtractVO.getLongLeadTimeMaterial() == null || lpskuExtractVO.getLongLeadTimeMaterial().trim().equals("")){
	    		String val = rs.getString("productlongLeadTimeMaterial");
	    		if(val !=null)
	    		{
	    			lpskuExtractVO.setLongLeadTimeMaterial(val.toUpperCase());
	    		}
	    		
	    	}else{
	    		lpskuExtractVO.setLongLeadTimeMaterial(lpskuExtractVO.getLongLeadTimeMaterial().toUpperCase());
	    	}
	    	
	    	
	    	try{
	    	BigDecimal targetcostvalue = null;
	    	String targetcost = rs.getString("SKUTARGETCOST");
	    	if(targetcost == null || targetcost.equals("") || targetcost.equals("0")){
	    		targetcost = rs.getString("targetCost");
	    		if(targetcost == null || targetcost.equals("") || targetcost.equals("0")){
	    			targetcostvalue = new BigDecimal("0.00");
	    		}else{
	    			targetcostvalue = new BigDecimal(targetcost);
	    		}
	    	}else{
	    		targetcostvalue = new BigDecimal(targetcost);
	    	}
	    	
	    	
//	    	if(targetcostvalue.trim().startsWith(".")){
//	    		targetcostvalue = "00"+targetcostvalue;
//	    	}
//	    	targetcostvalue = targetcostvalue + ".00";
//	    	StringTokenizer tokenizer = new StringTokenizer(targetcostvalue,"[.]");
//	    	String val1 = tokenizer.nextToken();
//	    	String val2 = tokenizer.nextToken();
//	    	val2 =val2 + "00";
//	    	val2 = val2.substring(0,2);
			lpskuExtractVO.setOriginalTargetcost(CoachUtil.formatWithoutSymbols(targetcostvalue.doubleValue(), 2));
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    	
	    	if(lpskuExtractVO.getSkumasterid()== null || lpskuExtractVO.getSkumasterid().trim().equals("") ||lpskuExtractVO.getSkumasterid().trim().equals("0")){
	    		
	    		if(lpskuExtractVO.getSkuUniqueid()==null || lpskuExtractVO.getSkuUniqueid().trim().equals("")){
		    		lpskuExtractVO.setSkumasterid("null");
		    	}else{
		    		lpskuExtractVO.setSkumasterid(lpskuExtractVO.getSkuUniqueid());
		    	}
			}
			else{	
	    		lpskuExtractVO.setSkumasterid(lpskuExtractVO.getSkumasterid());
				
			}
			
	    	
	    	
	    	

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			
			 try{
				 if(lpskuExtractVO.getCREATESTAMP() !=null && !lpskuExtractVO.getCREATESTAMP().trim().equals("") && lpskuExtractVO.getMODIFYSTAMP()!=null && !lpskuExtractVO.getMODIFYSTAMP().trim().equals(""))
				 {
					 java.util.Date date =  format.parse(lpskuExtractVO.getCREATESTAMP());
					 java.sql.Date dat = new java.sql.Date(date.getTime());
					
					 
					 java.util.Date date1 =  format.parse(lpskuExtractVO.getMODIFYSTAMP());
					 java.sql.Date dat1 =  new java.sql.Date(date1.getTime());
					
					 
					 lpskuExtractVO.setCREATETIMESTAMP(dat);//replaced by Final Edit
					 lpskuExtractVO.setMODIFYTIMESTAMP( dat1);//replaced by Final Edit
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			 }
	    	
			//RunBatch.readerrecords = RunBatch.readerrecords + 1;
	        return lpskuExtractVO;
	    } 
	
}
