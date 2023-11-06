package com.coach.middleware.batch.itemWriter;

import org.springframework.batch.item.file.transform.FieldExtractor;

import com.coach.middleware.batch.dao.VO.NightlyOutBoundVO;

public class ProdMastItemExtractor implements FieldExtractor<NightlyOutBoundVO> {

	@Override
	public Object[] extract(NightlyOutBoundVO item) {
		// TODO Auto-generated method stub

		if (item.getStaticLine() != null) {
			return new Object[] { item.getStaticLine() };
		} else if (item.getProdMastWriteRow() != null) {
			return new Object[] { item.getProdMastWriteRow() };
		} else {
			return new Object[] { item.getStyleNum(), item.getIntrodate(),
					item.getFsIntrodate(), item.getDept(),
					item.getStyleClass(), item.getSubClass(),
					item.getSkuName(), item.getStyleuniqueid(),
					item.getCrossBody(), item.getCollaboration(),
					item.getGifting(), item.getProductSegmentation(),
					item.getAttitudinalSegments(), item.getStyleDesc(),
					item.getDeleteDate(), item.getFsDeleteDate(),
					item.getTargetCost(), item.getOrgRetailPrice(),
					item.getWhPrice(), item.getTotalCost(),
					item.getAvgWorkingCost1(), item.getFinalStdCost(),
					item.getCollection(), item.getSkuIntroYear(),
					item.getSkuIntroDate(), item.getStyleIntroDate(),
					item.getSkuDeleteDate(), item.getSkuFsIntroYear(),
					item.getSkuFsIntroDate(), item.getStyleFsIntroDate(),
					item.getSkuFSDeleteDate(), item.getColorFamily(),
					item.getColorName(), item.getColorUniqueID(),
					item.getMaterial(), item.getSilhouette(),
					item.getHardwareColor(), item.getGender(),
					item.getSpecialProduct(), item.getAbcCode(),
					item.getPlanExclusion(), item.getChannelExclusive(),
					item.getStyleGroup(), item.getParentStyle(),
					item.getSignatureType(), item.getMpg(), item.getStatus(),
					item.getUpcCode(), item.getExotic(),
					item.getSubCollection(), item.getSizeScale(),
					item.getPint(), item.getSoleType(), item.getToeType(),
					item.getToeShape(), item.getHeelHeight1(),
					item.getWatchCaseSize(), item.getFunctionality(),
					item.getFaceColor(), item.getWatchCaseShape(),
					item.getLens(), item.getProductIdentity(),
					item.getAttitude(), item.getMerDesc(), item.getLicensed(),

			};
		}
	}

}
