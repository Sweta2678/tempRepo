package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.FashionSeason;

@Repository
public interface FashionSeasonRepository extends JpaRepository<FashionSeason, Integer>{
	
	List<String> getSeasonCodes();

	@Query(value = "select DISTINCT fs.seasoncode from fashionseason fs", nativeQuery = true)
	List<String> getAllSeasonCode();
}
