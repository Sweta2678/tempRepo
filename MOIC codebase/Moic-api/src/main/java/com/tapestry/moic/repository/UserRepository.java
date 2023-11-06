package com.tapestry.moic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.MoicUser;

@Repository
public interface UserRepository extends JpaRepository<MoicUser, String>{

	MoicUser findByEmailAddress(String EmailAddress);
	
	@Query(value="select distinct emailaddress from moic_user", nativeQuery=true)
	List<String> findAllEmailAddress();
}
