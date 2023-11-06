package com.tapestry.moic.service.interfaces;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.JobAudit;

@Service
public interface IJobAuditService {
	
	JobAudit saveJobAuditDetails(JobAudit jobAudit);

}
