package com.tapestry.moic.service.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.FinalizedBuy;

@Service
public interface IExcelService {
	
	ByteArrayInputStream exportOrder(List<FinalizedBuy> orderList);
}
