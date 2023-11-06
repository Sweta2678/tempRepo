package com.tapestry.moic.outbound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.outbound.entity.FinalizedBuyOutBound;

@Repository
public interface FinalizedBuyOutboundRepository extends JpaRepository<FinalizedBuyOutBound, Long> {
	
		public List<FinalizedBuyOutBound> findBySeasonAndChannelIgnoreCase(String season,String channel );
		public List<FinalizedBuyOutBound> findBySeasonIgnoreCase(String season);
		
		public FinalizedBuyOutBound findBySoldToNumberAndSeasonAndOldSKUAndNewSKUAndPoNumber(String soldToNumber, String season,
				String oldSKU, String newSKU, String poNumber);
}
