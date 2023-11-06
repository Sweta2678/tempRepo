package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.FinalizedBuyArchived;

@Repository
public interface FinalizedBuyArchivedRepository extends JpaRepository<FinalizedBuyArchived, Long> {

	public List<FinalizedBuyArchived> findBySeasonAndChannelContainingIgnoreCase(@Param("season")String season,@Param("channel") String channel );
	public List<FinalizedBuyArchived> findBySeasonIgnoreCase(String season);
	
	public Boolean existsFinalizedBuyArchivedByOrderID(Long orderID);
	
}
