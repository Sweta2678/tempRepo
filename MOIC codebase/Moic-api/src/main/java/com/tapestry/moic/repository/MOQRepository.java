package com.tapestry.moic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.MOQ;


@Repository
public interface MOQRepository extends JpaRepository<MOQ, Long> {
	
	List<MOQ> findBySeasonCode(String seasonCode);
	MOQ findBySeasonCodeAndSku(String seasonCode, String sku);
	
	List<MOQ> findByDelete(Boolean delete);
	
	List<MOQ> findByDeleteAndSeasonCode(Boolean delete, String seasonCode);
	
	MOQ findByIdAuto(Long idAuto);
	
	List<String> getSkusByDeleteAndSeasonCode(Boolean delete, String seasonCode);
	
	List<MOQ> findByDeleteAndSku(Boolean delete, String sku);
	
	@Query(value="select * from moq where userid=?1 and (modifieddate between ?2 and ?3 )", nativeQuery=true)
	List<MOQ> findMOQAuditDetail(String userId, Date fromdate,Date todate);
	
}
