package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tapestry.moic.dto.ConfigDto;

/**
 * The Interface IConfigInternalService.
 * 
 * @version 0.0.1
 * 
 */
@Service
public interface IConfigInternalService {

	List<ConfigDto> getConfigDefaultData();

	void updateConfigData(ConfigDto configDto, String token);

	void saveConfigUploadExcel(String sheetName, MultipartFile file,int headersLength, String token);

	List<ConfigDto> getConfigReportData();
	
	ByteArrayInputStream downloadCustomerReportExcle(List<ConfigDto> configDto);

}
