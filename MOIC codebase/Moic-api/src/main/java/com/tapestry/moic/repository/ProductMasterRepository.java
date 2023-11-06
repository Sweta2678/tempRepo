package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.ProductMaster;

import com.tapestry.moic.dto.OrderReportDto;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, String> {
	
	@Query(value="select fb.totalBuyQuantity,fb.scheduledDeliveryDate,fb.targetCost from FinalizedBuy fb,ProductMaster pm where pm.seasonCode=fb.season and pm.channel=fb.channel and pm.seasonCode=?1 and pm.channel=?2",nativeQuery = true)
	public List<OrderReportDto> joinTwoTable(String seasonCode,String channelId);
	
	public List<ProductMaster> findBySeasonCode(String seasonCode );

}
