package com.tapestry.moic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.JoorOrderDoors;

@Repository
public interface JoorOrderDoorsRepository extends JpaRepository<JoorOrderDoors, Long> {

}
