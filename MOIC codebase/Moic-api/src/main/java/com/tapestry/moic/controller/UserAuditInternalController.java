package com.tapestry.moic.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.IUserAuditInternalController;
import com.tapestry.moic.repository.UserRepository;
import com.tapestry.moic.response.SuccessResponse;
import com.tapestry.moic.response.interfaces.MoicResponse;
import com.tapestry.moic.service.interfaces.IUserAuditInternalService;

@Component
public class UserAuditInternalController implements IUserAuditInternalController {

	private Logger logger = LoggerFactory.getLogger(UserAuditInternalController.class);

	@Autowired
	private IUserAuditInternalService userAuditInternalService;
	@Autowired
	UserRepository userRepository;

	@Value("${user.audit.report.download.filename}")
	String userAuditReportfileName;

	@Value("${userAuditLabels}")
	String userAuditLabels;
	@Override
	public ResponseEntity<InputStreamResource> downloadUserAuditReport(String userId, String fromdate,
			String todate) {
		logger.debug("Inside Controller method downloadUserReport()");
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(MoicConstant.DDMMYYYYHHMMSS);

		String formattedDate = myDateObj.format(myFormatObj);
		String userAuditfileName = userAuditReportfileName + "_" + fromdate + "_" + todate + "_"+ userId + "_"+formattedDate;

		ByteArrayInputStream in = userAuditInternalService.downloadUserAuditReport(userId, fromdate, todate);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + userAuditfileName + ".xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}	
	

	@Override
	public MoicResponse getUserAuditDefaultData() {
		logger.debug("Inside Controller method getConfigDefaultData()");
		Map<String, Object> responseMap = new HashMap<>();

		List<String> moicUserList = userRepository.findAllEmailAddress();
		
			
		responseMap.put(MoicConstant.COLUMNS, userAuditLabels);
		responseMap.put(MoicConstant.DATA, moicUserList);
				
		if (moicUserList != null && !moicUserList.isEmpty()) {
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.SUCCESS_MESSAGE, responseMap);
		} else {
			return new SuccessResponse(true, HttpStatus.OK, MoicConstant.NO_RECORDS_FOUND, null);
		}
	}


}