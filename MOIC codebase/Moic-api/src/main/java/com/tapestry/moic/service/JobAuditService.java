package com.tapestry.moic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.entity.JobAudit;
import com.tapestry.moic.repository.JobAuditRepository;
import com.tapestry.moic.service.interfaces.IJobAuditService;


@Component
public class JobAuditService implements IJobAuditService {
	
	@Autowired
	JobAuditRepository jobAuditRepository;

	@Override
	public JobAudit saveJobAuditDetails(JobAudit jobAudit) {
		
		JobAudit audit = jobAuditRepository.save(jobAudit);
		return audit;
	}

}
