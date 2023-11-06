package com.tapestry.moic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.SkuChange;

@Repository
public interface SkuChangeRepository extends JpaRepository<SkuChange, Long> {

	SkuChange findByOldSKUAndSeason(String oldSKU ,String season);

	List<SkuChange> findByoldSKU(String OldSKU);
	
	List<SkuChange> findByOldSKUAndSeasonAndDelete(String oldSKU, String season, Boolean delete);

	List<SkuChange> findBySeason(String season);

	List<SkuChange> findByDelete(Boolean delete);

	SkuChange findByIdAuto(Long idAuto);
	
	List<SkuChange> findBySeasonAndOldSKU(String season, String oldSKU );
	
	SkuChange  findByOldSKUAndSeasonAndLevelAndLevelId(String oldSKU ,String season,String level,String levelId);

	List<SkuChange> findByDeleteAndSeason(Boolean delete, String season);
	
	List<String> getOldSkusByDeleteAndSeason(Boolean delete, String season);
	
	@Query(value="select * from skuchange where userid=?1  and (modifieddate between ?2 and ?3 )", nativeQuery=true)
	List<SkuChange> findSkuChangeAuditDetail(String userId, Date fromdate,Date todate);


}
