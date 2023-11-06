package com.tapestry.moic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.SizeConversion;

@Repository
public interface SizeConversionRepository extends JpaRepository<SizeConversion, Integer>{

	SizeConversion findByUsSize(String usSize);
	
}
