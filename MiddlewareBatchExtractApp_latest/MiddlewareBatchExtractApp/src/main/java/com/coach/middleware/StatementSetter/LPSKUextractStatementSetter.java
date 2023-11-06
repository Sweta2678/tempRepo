package com.coach.middleware.StatementSetter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.coach.middleware.batch.dao.VO.LpskuExtractVO;

public class LPSKUextractStatementSetter implements ItemPreparedStatementSetter<LpskuExtractVO> {
	@Override
	public void setValues(LpskuExtractVO item,PreparedStatement ps) throws SQLException {
		
		try{
		ps.setString(1, item.getSlotNumber());
		ps.setString(2, item.getStyleNumber());//want to add Style Name
		ps.setString(3, item.getzSKU());
		
		if(item.getSkumasterid()== null || item.getSkumasterid().trim().equals("") ||item.getSkumasterid().trim().equals("0")){
			if(item.getSkuUniqueid() == null || item.getSkuUniqueid().trim().equals("") || item.getSkuUniqueid().trim().equals("0"))
			{
				ps.setString(4, "");
			}else{
				ps.setString(4, item.getSkuUniqueid());//Want to add SKUmasterid
			}
		}
		else{	
			ps.setString(4, item.getSkumasterid());//Want to add SKUmasterid
		}
		
		
		ps.setString(5, item.getSizescale());
		ps.setString(6, item.getsKUGroupCode());
		ps.setString(7, item.getsKUGroupDesc());
		ps.setString(8, item.getAbbreviatedStyleSKUFullName());
		ps.setString(9, item.getDepartmentCode());
		ps.setString(10, item.getClassCode());
		ps.setString(11, item.getSubClassCode());
		ps.setString(12, item.getCollectioncode());
		ps.setString(13, item.getCollectionDescription());
		ps.setString(14, item.getSubCollectionCode());
		ps.setString(15, item.getStyleIntroDate());
		ps.setString(16, item.getStyleFSIntroDate());
		ps.setString(17, item.getsKUIntroDate());
		ps.setString(18, item.getsKUFSIntroDate());
		ps.setString(19, item.getMaterialTypeCode());
		ps.setString(20, item.getMaterialTypeValue());
		
		
		
		ps.setString(21, item.getMerchSKUstatus());
		ps.setString(22, item.getbOMstatus());
		
		ps.setString(23, item.getRetailPrice());
		ps.setString(24, item.getWhoePrice());
		ps.setString(25, item.getOriginalTargetcost());
		
		ps.setString(26, item.getMaterialTypeCoded2());
		ps.setString(27, item.getSignatureTypeCode());
		ps.setString(28, item.getSignatureTypeDesc());
		
		
		ps.setString(29, item.getMaterialTypeDesc());
		ps.setString(30, item.getLongLeadTimeMaterial());
		ps.setString(31, item.getOnetimeBuy());
		ps.setString(32, item.getGenderCode());
		ps.setString(33, item.getTeam());
		ps.setString(34, item.getDesigner());
		ps.setString(35, item.getEngineer());
		ps.setString(36, item.getProdDevelopment());
		ps.setString(37, item.getProductionMgr());
		ps.setString(38, item.getSilhouetteCode());
		ps.setString(39, item.getSilhouetteDesc());
		
		
		ps.setString(40, item.getRenegade());
		
		ps.setString(41, item.getEarlyCosting());
		ps.setString(42, item.getDualDevelopment());
		ps.setString(43, item.getHandbagSize());
		ps.setString(44, item.getFactoryStoreTypeProduct());
		ps.setString(45, item.getSkuFactoryStoreType());
		ps.setString(46, item.getStyleDeleteDate());
		
		ps.setString(47, item.getStyleFSDeleteDate());
		ps.setString(48, item.getsKUDeleteDate());
		ps.setString(49, item.getsKUFSDeleteDate());
		ps.setString(50, item.getProductName());
		
		ps.setString(51, item.getPlanExclusion());
		ps.setString(52, item.getKeyStyleCosting());
		ps.setString(53, item.getSecondEdit());
		ps.setString(54, item.getFirstEdit());//replaced by Final Edit
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
			 java.sql.Date dat= null;
			 java.sql.Date dat1 = null;
			 if(item.getCREATESTAMP()!=null && item.getMODIFYSTAMP()!=null){
				 java.util.Date date =  format.parse(item.getCREATESTAMP());
				 dat = new java.sql.Date(date.getTime());
				
				 
				 java.util.Date date1 =  format.parse(item.getMODIFYSTAMP());
				 dat1 =  new java.sql.Date(date1.getTime());
			 }
			 
		ps.setDate(55,  dat);//replaced by Final Edit
		ps.setDate(56,  dat1);//replaced by Final Edit
		 
		ps.setString(57, item.getGcc());
		ps.setString(58, item.getLicense());
		ps.setString(59, item.getVendorRef());
		
		
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		
		
		
	
	}
}