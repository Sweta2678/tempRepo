package com.tapestry.moic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.JoorOrderDivisions;

@Repository
public interface JoorOrderDivisionsRepository extends JpaRepository<JoorOrderDivisions, Long> {

}
