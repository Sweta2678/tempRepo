package com.coach.middleware.batch.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.coach.middleware.batch.dao.RowMapper.AttributeStoreMapper;
import com.coach.middleware.batch.dao.RowMapper.FullPriceMOARowMapper;
import com.coach.middleware.batch.dao.RowMapper.PRODMASTFullPriceRowMapper;
import com.coach.middleware.batch.dao.RowMapper.SizeScaleMapper;
import com.coach.middleware.batch.dao.VO.AttributeStoreVO;
import com.coach.middleware.batch.dao.VO.FullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.dao.VO.PRODMASTFullPriceMOAVO;
import com.coach.middleware.batch.dao.VO.RpFtpAbcVO;
import com.coach.middleware.batch.dao.VO.SizeScaleVO;
import com.coach.middleware.batch.dao.VO.TotoExtractVO;
import com.coach.middleware.batch.itemProcessor.ProdMastItemProcessor;

public class NightlyOutBoundDaoImpl implements NightlyOutBoundDAO{
	
	
	private static final Logger logger = Logger
			.getLogger(NightlyOutBoundDaoImpl.class);
	
	@Autowired
	DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	List<AttributeStoreVO> attributeStoreVOList = new ArrayList();
	List<SizeScaleVO> sizeScaleMappingList = new ArrayList();
	List<PRODMASTFullPriceMOAVO> costMoaList = new ArrayList();
	List<RpFtpAbcVO> rpFtpAbcList = new ArrayList();
	
	@Override
	public List<AttributeStoreVO> getTotoExtractAttrKeys(
			NightlyOutBoundVO nightlyOutBoundVO) {
		
		logger.info("inside NightlyOutBoundDaoImpl  get Attribute Keys-->");	
		
		jdbcTemplate = new JdbcTemplate(dataSource); 
		
		jdbcTemplate.setFetchSize(1000);
		
		
		String sql = "select ats.display,ats.attributename,ats.display2,ats.display3,ats.display4,ats.valuekey from ATTRIBUTESTORE ats" +
				" where UPPER(ats.attributename)='DEPARTMENT'" +
				" or UPPER(ats.attributename)='STYLECLASS' or UPPER(ats.attributename)='SUBCLASS' or UPPER(ats.attributename)='FACTORYSTORETYPEPRODUCT'" +
				" or UPPER(ats.attributename)='SIZES' or UPPER(ats.attributename)='GENDER' or UPPER(ats.attributename)='SILHOUETTE'" +
				" or UPPER(ats.attributename)='COMPOSITION'  or UPPER(ats.attributename)='COLORFAMILY' or UPPER(ats.attributename)='FACECOLOR' " +
				" or UPPER(ats.attributename)='MATERIAL' or UPPER(ats.attributename)='HARDWARECOLOR' " +
				" or UPPER(ats.attributename)='SPECIALPRODUCT' or UPPER(ats.attributename)='ABCCODE' or UPPER(ats.attributename)='PLANEXCLUSION' " +
				" or UPPER(ats.attributename)='MASTERSTYLE' or UPPER(ats.attributename)='STATUS' or UPPER(ats.attributename)='PLATFORM'" +
				" or UPPER(ats.attributename)='HANDBAGSIZE' or UPPER(ats.attributename)='LOGOTYPE' or UPPER(ats.attributename)='FAMILY'"+
				" or UPPER(ats.attributename)='ATTITUDINALSEGMENTS' or UPPER(ats.attributename)='PRODUCTSEGMENTATION' or UPPER(ats.attributename)='PRINT'"+
				" or UPPER(ats.attributename)='EMBELLISHMENT' or UPPER(ats.attributename)='TOETYPE' or UPPER(ats.attributename)='SOLETYPE'"+
				" or UPPER(ats.attributename)='TOESHAPE' or UPPER(ats.attributename)='HEELHEIGHT' or UPPER(ats.attributename)='CASESIZE'"+
				" or UPPER(ats.attributename)='CASESHAPE' or UPPER(ats.attributename)='LENSTYPE' or UPPER(ats.attributename)='FUNCTIONALITY'"+
				" or UPPER(ats.attributename)='FACECOLOR' or UPPER(ats.attributename)='GIFTNG' or UPPER(ats.attributename)='COLLABORATION'"+
				" or UPPER(ats.attributename)='PRODUCTIDENTITY' or UPPER(ats.attributename)='CROSSBODY' or UPPER(ats.attributename)='UPDATED'" +
				" or UPPER(ats.attributename)='LICENSED' or UPPER(ats.attributename)='CHANNELEXCLUSIVE'";
		
		attributeStoreVOList = (List<AttributeStoreVO>) jdbcTemplate.query(sql, new Object[] {}, new AttributeStoreMapper());
		
		return attributeStoreVOList;
	}

	@Override
	public List<SizeScaleVO> getSizeScaleMappingList(
			NightlyOutBoundVO nightlyOutBoundVO) {
		
		logger.info("inside NightlyOutBoundDaoImpl  getSizeScaleMappingList()-->");
		
		jdbcTemplate = new JdbcTemplate(dataSource); 
		
		jdbcTemplate.setFetchSize(1000);
		
		
		String sql = "select sm.uniqueid sizescale,sm.sizescalemsg,sm.sizelist from sizescalemaster sm ";

		
		sizeScaleMappingList = (List<SizeScaleVO>) jdbcTemplate.query(sql, new Object[] {}, new SizeScaleMapper());
		
		return sizeScaleMappingList;
	}

	@Override
	public List<PRODMASTFullPriceMOAVO> getCostMoaList(
			NightlyOutBoundVO nightlyOutBoundVO) {

logger.info("inside NightlyOutBoundDaoImpl  getCostMoaList()-->");
		
		jdbcTemplate = new JdbcTemplate(dataSource); 
		
		jdbcTemplate.setFetchSize(1000);
		
		
		/*String sql = "select moa.owneruniqueid owneruniqueId,moa.targetCost TargetCost,moa.retailPrice RetailPrice,moa.WholeSalePrice WHPrice,moa.TotalCost TotalCost " +
					 "from Merchandisingfullpricetable moa where moa.owneruniqueid IN ("+StringUtils.collectionToCommaDelimitedString(nightlyOutBoundVO.getSkuUniqueIdList())+") and" +
					 " moa.EndDate is null";*/
		String sql = "select moa.owneruniqueid owneruniqueId,moa.targetCost TargetCost,moa.retailPrice RetailPrice,moa.WholeSalePrice WHPrice,moa.TotalCost TotalCost " +
		 "from Merchandisingfullpricetable moa where moa.owneruniqueid IN ("+StringUtils.collectionToCommaDelimitedString(nightlyOutBoundVO.getSkuUniqueIdList())+") and ((startdate <= sysdate and enddate >= sysdate)"+
		 "or (startdate <= sysdate and enddate is null))"; 
		
		costMoaList = (List<PRODMASTFullPriceMOAVO>) jdbcTemplate.query(sql, new Object[] {}, new PRODMASTFullPriceRowMapper());
		//logger.info("Size of costMoaList NightlyOutBoundDaoImpl ==>"+costMoaList.size());
		
		return costMoaList;
	}
	
	@Override
	public List<RpFtpAbcVO> getRpFtpAbcList(
			NightlyOutBoundVO nightlyOutBoundVO) {

logger.info("inside NightlyOutBoundDaoImpl  getRpFtpAbcList()-->");
		
		jdbcTemplate = new JdbcTemplate(dataSource); 
		
		//jdbcTemplate.setFetchSize(1000);
		
		List rpFtpAbcList = new ArrayList();
		rpFtpAbcList.addAll(nightlyOutBoundVO.getStyleNumList());
		
		String sql = "select rp.abccode,rp.mpg,rp.style,rp.skuname from RPFTPABC rp where " +
					 "rp.style IN ("+StringUtils.collectionToCommaDelimitedString(rpFtpAbcList)+")";
		//"+StringUtils.collectionToCommaDelimitedString(nightlyOutBoundVO.getStyleNumList())+"
		
		rpFtpAbcList = (List<RpFtpAbcVO>) jdbcTemplate.query(sql, new Object[] {}, new RpFtpAbcRowMapper());
		logger.info("Size of rpFtpAbcList NightlyOutBoundDaoImpl ==>"+rpFtpAbcList.size());
		
		return rpFtpAbcList;
	}
	
	

}
