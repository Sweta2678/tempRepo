package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tapestry.moic.entity.MOQReport;

@Repository
@Transactional
public interface MOQReportRepository extends JpaRepository<MOQReport, Long> {
	List<MOQReport> findBySeasonAndChannelIgnoreCase(String season,String channel );
	
	List<MOQReport> findBySeasonIgnoreCase(String season);
	
	List<Long> getAllOrderIds();
	
	List<MOQReport> findBySeasonIn(List<String> seasons);
	
	List<MOQReport> findByChannelAndSeasonIn(String channel, List<String> seasons);
}