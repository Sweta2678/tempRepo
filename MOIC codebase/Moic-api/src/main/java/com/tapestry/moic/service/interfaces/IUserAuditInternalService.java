package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Service;

@Service
public interface IUserAuditInternalService {

	ByteArrayInputStream downloadUserAuditReport(String userId,  String fromdate,String todate);
}
