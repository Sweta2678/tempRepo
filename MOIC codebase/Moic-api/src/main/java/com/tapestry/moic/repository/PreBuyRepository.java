package com.tapestry.moic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.PreBuy;

@Repository
public interface PreBuyRepository extends JpaRepository<PreBuy, String> {

	public List<PreBuy> findBySeasonCode(String SeasonCode);
	public PreBuy findBySeasonCodeAndSKU(String SeasonCode,String sku);
	public List<PreBuy> findAll();
	
	@Query(value="select * from prebuy where userid=?1 and (modifieddate between ?2 and ?3 )", nativeQuery=true)
	List<PreBuy> findPreBuyAuditDetail(String userId, Date fromdate,Date todate);
}
