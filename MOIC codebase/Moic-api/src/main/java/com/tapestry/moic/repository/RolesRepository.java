package com.tapestry.moic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{

	Roles findByRoleId(Integer roleId);
	
	Roles findByRole(String role);
}
