package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tapestry.moic.dto.MOQDto;
import com.tapestry.moic.dto.PreBuyDto;

/**
 *  The Interface IMOQInternalService.
 * 
 * @version 0.0.1
 * 
 */

@Service
public interface IMOQInternalService {

	Map<String, Object> getMOQDefaultData(String season, String channel) ;

	Boolean updateMOQData(MOQDto moqDto, String token);

	public String saveMOQ(String sheetName,MultipartFile file ,int headersLength, String token);

	public String savePreBuy(String sheetName,MultipartFile file,int headersLength, String token);
	
	public ByteArrayInputStream  downloadMoqList(String season);
	
	public ByteArrayInputStream moqDownloadBySeasonAndChannel(List<String> season, String channel );
	
	public List<PreBuyDto> getPreBuyDefaultData();
	
	public Map<String, Object> generateMOQOverviewData(String season, String channel);
	
	public ByteArrayInputStream downloadAllPreBuyList();
}
