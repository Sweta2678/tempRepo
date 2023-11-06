package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tapestry.moic.dto.SkuChangeDto;

@Service
public interface ISkuChangeInternalService {
	
	Map<String, Object> getSKUChangeDefaultData(String season);
	
	Boolean updateSKUChangeData(SkuChangeDto skuChangeDto, String token);
	
	List<SkuChangeDto> excelToSKUChanges(String sheetName, MultipartFile file ,int headersLength, String token);

	ByteArrayInputStream downloadSKUChangeList(String season);
	
	Map<String, Object> generateSKUChangeOverviewData(String season, String channel);

	ByteArrayInputStream downloadSKUChangeReport(List<String> season,String channel);

	String saveSKUChangeUploadExcel(List<SkuChangeDto> skuChanges, String token);

}
