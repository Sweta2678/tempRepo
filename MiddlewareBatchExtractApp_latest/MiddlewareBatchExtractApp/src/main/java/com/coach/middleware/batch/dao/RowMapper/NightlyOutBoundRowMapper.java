package com.coach.middleware.batch.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;
import com.coach.middleware.batch.itemProcessor.ProdMastItemProcessor;


public class NightlyOutBoundRowMapper implements RowMapper<NightlyOutBoundVO> {
	
	private static final Logger logger = Logger
			.getLogger(NightlyOutBoundRowMapper.class);

	public List<String> skuUniqueIdList = new ArrayList();
	public Set<String> styleNumList = new HashSet();
	@Override
	public NightlyOutBoundVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub
		
		NightlyOutBoundVO nightlyOutBoundVO = new NightlyOutBoundVO();
		
		nightlyOutBoundVO.setStyleNum(rs.getString("styleNum"));
		if(nightlyOutBoundVO.getStyleNum() != null){
			styleNumList.add("'"+nightlyOutBoundVO.getStyleNum()+"'");
		}
		nightlyOutBoundVO.setIntrodate(rs.getString("introdate"));
		nightlyOutBoundVO.setFsIntrodate(rs.getString("fsIntrodate"));
		nightlyOutBoundVO.setDept(rs.getString("dept"));
		nightlyOutBoundVO.setStyleClass(rs.getString("styleClass"));
		nightlyOutBoundVO.setSubClass(rs.getString("subClass"));
		nightlyOutBoundVO.setSkuName(rs.getString("skuName"));
		nightlyOutBoundVO.setStyleuniqueid(rs.getString("styleuniqueid"));
		nightlyOutBoundVO.setSkuUniqueId(rs.getString("skuUniqueId"));
		if(nightlyOutBoundVO.getSkuUniqueId() != null){
			skuUniqueIdList.add(nightlyOutBoundVO.getSkuUniqueId());
		}
		nightlyOutBoundVO.setCrossBody(rs.getString("crossBody"));
		nightlyOutBoundVO.setCollaboration(rs.getString("Collaboration"));
		nightlyOutBoundVO.setGifting(rs.getString("Gifting"));
		nightlyOutBoundVO.setProductSegmentation(rs.getString("productSegmentation"));
		nightlyOutBoundVO.setAttitudinalSegments(rs.getString("attitudinalSegments"));
		nightlyOutBoundVO.setStyleDesc(rs.getString("styleDesc"));
		nightlyOutBoundVO.setDeleteDate(rs.getString("deleteDate"));
		nightlyOutBoundVO.setFsDeleteDate(rs.getString("fsDeleteDate"));
		//nightlyOutBoundVO.setTargetCost(rs.getString(null));
		nightlyOutBoundVO.setSkuTargetCost(rs.getString("skuTargetCost"));
		nightlyOutBoundVO.setProdTargetCost(rs.getString("prodTargetCost"));
		//nightlyOutBoundVO.setOrgRetailPrice(null);
		//nightlyOutBoundVO.setWhPrice(null);
		//nightlyOutBoundVO.setTotalCost(null);
		nightlyOutBoundVO.setSkuTotalCost(rs.getString("skuTotalCost"));
		nightlyOutBoundVO.setProdTotalCost(rs.getString("prodTotalCost"));
		nightlyOutBoundVO.setAvgWorkingCost1(rs.getString("AvgWorkingCost1"));
		nightlyOutBoundVO.setSkuAvgWorkingCost1(rs.getString("skuAvgWorkingCost1"));
		nightlyOutBoundVO.setFinalStdCost(rs.getString("FinalStdCost"));
		nightlyOutBoundVO.setProdStdCost(rs.getString("prodStdCost"));
		nightlyOutBoundVO.setCollection(rs.getString("collection"));
		nightlyOutBoundVO.setSkuIntroYear(rs.getString("skuIntroYear"));
		nightlyOutBoundVO.setStyleIntroYear(rs.getString("styleIntroYear"));
		nightlyOutBoundVO.setSkuDeleteYear(rs.getString("SkuDeleteYear"));
		nightlyOutBoundVO.setStyleDeleteYear(rs.getString("StyleDeleteYear"));
		
		nightlyOutBoundVO.setSkuIntroDate(rs.getString("skuIntroDate"));
		nightlyOutBoundVO.setStyleIntroDate(rs.getString("StyleIntroDate"));
		nightlyOutBoundVO.setSkuDeleteDate(rs.getString("skuDeleteDate"));
		nightlyOutBoundVO.setSkuFsIntroYear(rs.getString("skuFsIntroYear"));
		nightlyOutBoundVO.setStyleFsIntroYear(rs.getString("StyleFsIntroYear"));
		nightlyOutBoundVO.setSkuFsIntroDate(rs.getString("skuFsIntroDate"));
		nightlyOutBoundVO.setStyleFsIntroDate(rs.getString("StyleFsIntroDate"));
		nightlyOutBoundVO.setSkuFSDeleteDate(rs.getString("skuFSDeleteDate"));
		nightlyOutBoundVO.setStyleFSDeleteDate(rs.getString("StyleFSDeleteDate"));
		nightlyOutBoundVO.setSkuFsDeleteYear(rs.getString("SkuFsDeleteYear"));
		nightlyOutBoundVO.setStyleFsDeleteYear(rs.getString("StyleFsDeleteYear"));
		nightlyOutBoundVO.setStyleDeleteDate(rs.getString("styleDeleteDate"));
		
		nightlyOutBoundVO.setColorFamily(rs.getString("colorFamily"));
		nightlyOutBoundVO.setColorName(rs.getString("colorName"));
		nightlyOutBoundVO.setColorUniqueID(rs.getString("ColorUniqueID"));
		nightlyOutBoundVO.setMaterial(rs.getString("Material"));
		nightlyOutBoundVO.setSilhouette(rs.getString("Silhouette"));
		nightlyOutBoundVO.setHardwareColor(rs.getString("hardwareColor"));
		nightlyOutBoundVO.setGender(rs.getString("gender"));
		nightlyOutBoundVO.setSpecialProduct(rs.getString("specialProduct"));
		nightlyOutBoundVO.setAbcCode("");
		nightlyOutBoundVO.setPlanExclusion(rs.getString("planExclusion"));
		nightlyOutBoundVO.setChannelExclusive(rs.getString("ChannelExclusive"));
		nightlyOutBoundVO.setStyleGroup(rs.getString("StyleGroup"));
		nightlyOutBoundVO.setParentStyle(rs.getString("ParentStyle"));
		nightlyOutBoundVO.setSignatureType(rs.getString("signatureType"));
		nightlyOutBoundVO.setMpg("0.0");
		nightlyOutBoundVO.setStatus(rs.getString("Status"));
		nightlyOutBoundVO.setUpcCode(rs.getString("UPCCode"));
		nightlyOutBoundVO.setExotic(rs.getString("Exotic"));
		nightlyOutBoundVO.setSubCollection(rs.getString("subCollection"));
		nightlyOutBoundVO.setSizeScale(rs.getString("sizeScale"));
		nightlyOutBoundVO.setPint(rs.getString("pint"));
		nightlyOutBoundVO.setSoleType(rs.getString("Soletype"));
		nightlyOutBoundVO.setToeType(rs.getString("toeType"));
		nightlyOutBoundVO.setToeShape(rs.getString("toeShape"));
		nightlyOutBoundVO.setHeelHeight1(rs.getString("heelHeight1"));
		nightlyOutBoundVO.setWatchCaseSize(rs.getString("watchCaseSize"));
		nightlyOutBoundVO.setFunctionality(rs.getString("functionality"));
		nightlyOutBoundVO.setFaceColor(rs.getString("faceColor"));
		nightlyOutBoundVO.setWatchCaseShape(rs.getString("caseShape"));
		nightlyOutBoundVO.setLens(rs.getString("lens"));
		nightlyOutBoundVO.setProductIdentity(rs.getString("productIdentity"));
		nightlyOutBoundVO.setAttitude(rs.getString("attitude"));
		nightlyOutBoundVO.setMerDesc(rs.getString("merDesc"));
		nightlyOutBoundVO.setLicensed(rs.getString("licensed"));
		nightlyOutBoundVO.setUpdated(rs.getString("updated"));
		nightlyOutBoundVO.setFactoryType(rs.getString("factoryType"));
		nightlyOutBoundVO.setCurrentCost(rs.getString("currentCost"));
		nightlyOutBoundVO.setEmbellishment(rs.getString("embellishment"));
		nightlyOutBoundVO.setHandBagSize(rs.getString("handbagsize"));
		nightlyOutBoundVO.setSkuHardwareColor(rs.getString("skuhardwarecolor"));
		nightlyOutBoundVO.setProdRetailPrice(rs.getString("prodRetailPrice"));
		nightlyOutBoundVO.setProdWhPrice(rs.getString("prodWhPrice"));
		nightlyOutBoundVO.setSkuSignatureType(rs.getString("skuSignatureType"));
		nightlyOutBoundVO.setSkuMaterial(rs.getString("skuMaterial"));
		nightlyOutBoundVO.setSkuUniqueIdList(skuUniqueIdList);
		nightlyOutBoundVO.setStyleNumList(styleNumList);
		return nightlyOutBoundVO;
	}
	public List<String> getSkuUniqueIdList() {
		return skuUniqueIdList;
	}
	public void setSkuUniqueIdList(List<String> skuUniqueIdList) {
		this.skuUniqueIdList = skuUniqueIdList;
	}
	public Set<String> getStyleNumList() {
		return styleNumList;
	}
	public void setStyleNumList(Set<String> styleNumList) {
		this.styleNumList = styleNumList;
	}

}
