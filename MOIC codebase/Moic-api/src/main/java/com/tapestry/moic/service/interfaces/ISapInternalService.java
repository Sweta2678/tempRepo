package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.FinalizedBuy;

@Service
public interface ISapInternalService {

	List<FinalizedBuy> sapUploadBySeasonAndChennel(List<String> season, String channel, String token);

	ByteArrayInputStream downloadPreviewS4(List<String> season, String channel);
}