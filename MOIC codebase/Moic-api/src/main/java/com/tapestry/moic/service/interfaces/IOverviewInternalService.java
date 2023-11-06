package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * The Interface IOverviewInternalService.
 * 
 * @version 0.0.1
 * 
 */
@Service
public interface IOverviewInternalService {

	Map<String, Object> getOverviewDefaultData(String season, String channel);
	
	ByteArrayInputStream  finalizedBuyArchivedDownload(String season, String channel);
	ByteArrayInputStream  orderDownloadReport(List<String> season, String channel);
	
}
