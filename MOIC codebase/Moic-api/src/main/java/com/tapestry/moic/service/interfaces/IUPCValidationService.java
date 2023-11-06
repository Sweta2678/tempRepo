package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IUPCValidationService {

	ByteArrayInputStream downloadUPCValidationBySeason(List<String> season);
}
