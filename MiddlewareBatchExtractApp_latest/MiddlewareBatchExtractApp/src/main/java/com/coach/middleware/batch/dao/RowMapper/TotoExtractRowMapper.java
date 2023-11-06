package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.TotoExtractVO;

public class TotoExtractRowMapper implements RowMapper<TotoExtractVO> {

		
	    public TotoExtractVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	 
		 TotoExtractVO totoExtractVO = new TotoExtractVO();
	     
		 totoExtractVO.setStyleName(rs.getString("PRODUCTNAME"));
		 totoExtractVO.setFactoryStoreTypeProduct(rs.getString("FACTORYSTORETYPEPRODUCT"));
		 totoExtractVO.setStyleNumber(rs.getString("STYLENUMBER"));
		 totoExtractVO.setDepartment(rs.getString("DEPARTMENT"));
		 totoExtractVO.setClassName(rs.getString("STYLECLASS"));
		 totoExtractVO.setSubClass(rs.getString("SUBCLASS"));
		 totoExtractVO.setRetailPrice(rs.getString("RETAILPRICE"));
		//SEASONGROUPNAME
		 totoExtractVO.setIntroDate(rs.getString("INTRODATE"));
		 totoExtractVO.setFsIntroDate(rs.getString("FSINTRODATE"));
		 //totoExtractVO.setIntroDate(rs.getString("SEASONGROUPNAME"));
		 //totoExtractVO.setIntroDate("seasonGrpName");
		 
		 totoExtractVO.setTotoColors(rs.getString("TOTOCOLORS"));
		 totoExtractVO.setTotoCountryofOrigin(rs.getString("TOTOCOUNTRYOFORIGIN"));
		 totoExtractVO.setTotoSizes(rs.getString("TOTOSIZES"));
		 totoExtractVO.setGender(rs.getString("GENDER"));
		 totoExtractVO.setSilhouette(rs.getString("SILHOUETTE"));
		 totoExtractVO.setTotoCollection(rs.getString("TOTOCOLLECTION"));
		 totoExtractVO.setTotoLengthcm(rs.getString("TOTOLENGTHCM"));
		 totoExtractVO.setTotoHeightcm(rs.getString("TOTOHEIGHTCM"));
		 totoExtractVO.setTotoWidthcm(rs.getString("TOTOWIDTHCM"));
		 totoExtractVO.setJewelryDimensions(rs.getString("JEWELRYDIMENSIONS"));
		 totoExtractVO.setKeyRingDimensions(rs.getString("KEYRINGDIMENSIONS"));
		 totoExtractVO.setTotoStrapLengthIn(rs.getString("TOTOSTRAPLENGTHIN"));
		 totoExtractVO.setTotoDropLengthIn(rs.getString("TOTODROPLENGTHIN"));
		 totoExtractVO.setTotoShellMaterial(rs.getString("TOTOSHELLMATERIAL"));
		 totoExtractVO.setTotoTrimMaterial(rs.getString("TOTOTRIMMATERIAL"));
		 totoExtractVO.setTotoLiningMaterial(rs.getString("TOTOLININGMATERIAL"));
		 totoExtractVO.setUpperMaterial(rs.getString("UPPERMATERIAL"));
		 totoExtractVO.setOrganizationalPanel(rs.getString("ORGANIZATIONALPANEL"));
		 totoExtractVO.setTotoInteriorPocket(rs.getString("TOTOINTERIORPOCKET"));
		 totoExtractVO.setTotoExteriaorPocket(rs.getString("TOTOEXTERIORPOCKET"));
		 totoExtractVO.setClosures(rs.getString("CLOSURE"));
		 totoExtractVO.setCreditCardPocket(rs.getString("CREDITCARDPOCKET"));
		 totoExtractVO.setTotoBillfoldPocket(rs.getString("TOTOBILLFOLDPOCKETS"));
		 totoExtractVO.setIdWindow(rs.getString("IDWINDOW"));
		 totoExtractVO.setCoin(rs.getString("COIN"));
		 totoExtractVO.setTotoCheckBook(rs.getString("TOTOCHECKBOOK"));
		 totoExtractVO.setHeelHeight(rs.getString("HEELHEIGHT"));
		 totoExtractVO.setSoleType(rs.getString("TOTOTYPEOFSOLE"));
		 totoExtractVO.setAdditionalFeatures(rs.getString("ADDITIONALFEATURES"));
		 totoExtractVO.setCareCardCm(rs.getString("CARECARDCM"));
		 totoExtractVO.setLeatherCleaner(rs.getString("LEATHERCLEANER"));
		 totoExtractVO.setLeatherMoisturizer(rs.getString("LEATHERMOISTURIZER"));
		 totoExtractVO.setFabricCleaner(rs.getString("FABRICCLEANER"));
		 totoExtractVO.setDampCloth(rs.getString("DAMPCLOTH"));
	        return totoExtractVO;
	    } 
	
}
