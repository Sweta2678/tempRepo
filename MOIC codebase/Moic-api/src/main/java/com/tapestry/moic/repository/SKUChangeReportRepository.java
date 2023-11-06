package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.SKUChangeReport;

@Repository
public interface SKUChangeReportRepository extends JpaRepository<SKUChangeReport, Long> {
	
	List<SKUChangeReport> findBySeasonAndChannelIgnoreCase(String season, String channel);

	List<SKUChangeReport> findBySeasonIgnoreCase(String season);
	
	List<Long> getAllOrderIds();
	
	List<SKUChangeReport> findBySeasonIn(List<String> seasons);
	
	List<SKUChangeReport> findByChannelAndSeasonIn(String channel, List<String> seasons);
}