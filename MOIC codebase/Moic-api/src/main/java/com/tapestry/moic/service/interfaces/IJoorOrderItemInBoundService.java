package com.tapestry.moic.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.JoorOrderItem;

/**
 * The Interface IJoorOrderItemInBoundService.
 * 
 * @version 0.0.1
 * 
 */
@Service
public interface IJoorOrderItemInBoundService {
	
	JoorOrderItem addJoorOrderItemInfo(JoorOrderItem joorOrderItem);
	
	List<JoorOrderItem> getAllJoorOrderItemInfo();
}
