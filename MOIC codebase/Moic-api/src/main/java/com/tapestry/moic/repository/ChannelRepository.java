package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer>{
	
	Channel findByChannelNameIgnoreCase(String channelName);
	
	List<String> getChannelNames();
	
	List<Channel> findByChannelNameIn(List<String> channelName);
}
