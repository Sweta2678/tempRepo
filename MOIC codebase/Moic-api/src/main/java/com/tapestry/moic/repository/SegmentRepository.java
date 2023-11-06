package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.Segment;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Integer> {

	Segment findBySegmentCodeIgnoreCase(String segmentCode);
	
	List<String> getSegmentCodes();
}
