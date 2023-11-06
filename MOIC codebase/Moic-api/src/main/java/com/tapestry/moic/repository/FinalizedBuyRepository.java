package com.tapestry.moic.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.FinalizedBuy;
import com.tapestry.moic.entity.key.FinalizedBuyKeys;

@Repository
public interface FinalizedBuyRepository extends JpaRepository<FinalizedBuy, FinalizedBuyKeys> {
		
	public List<FinalizedBuy> findBySeasonAndChannelIgnoreCase(String season,String channel );
	
	public List<FinalizedBuy> findBySeasonIgnoreCase(String season);
	
	@Query(value = "select m.skuid from finalizedbuy t cross join lateral(\r\n"
			+ "values(t.sku, 'sku'), (t.oldsku, 'oldsku'), (t.newsku, 'newsku')) as m(skuid, skutype)\r\n"
			+ "where t.season IN :season and t.upccode is null\r\n", nativeQuery = true)
	public Set<String> getAllSKUsBySeasonCodeAndUPCIsNull(@Param("season") List<String> season);
	
	public List<FinalizedBuy> findBySeasonIn(List<String> seasons);
	
	public List<FinalizedBuy> findByChannelAndSeasonIn(String channel, List<String> seasons);
	
	public List<FinalizedBuy> findBySoldToNumberAndSeasonAndNewSKUAndOrderIDAndUpcCode(String soldToNumber, String season,
			String newSKU, Long orderID, String upcCode);
}
