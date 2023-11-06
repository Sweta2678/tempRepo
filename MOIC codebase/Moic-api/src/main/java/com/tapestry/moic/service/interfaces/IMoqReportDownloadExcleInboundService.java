package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.tapestry.moic.entity.MOQ;

public interface IMoqReportDownloadExcleInboundService {
	ByteArrayInputStream moqReportDownload (List<MOQ> moqList);
}
