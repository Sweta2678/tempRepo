package com.tapestry.moic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tapestry.moic.entity.JobAudit;

@Repository
public interface JobAuditRepository extends JpaRepository<JobAudit, Long>{

}
