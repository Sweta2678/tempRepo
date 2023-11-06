package com.tapestry.moic.service.interfaces;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tapestry.moic.entity.MOQ;


@Service
public interface IMoqReportDownloadInboundService {
	public List<MOQ> IMoqReportDownloadReport(String channel,String season);
}

