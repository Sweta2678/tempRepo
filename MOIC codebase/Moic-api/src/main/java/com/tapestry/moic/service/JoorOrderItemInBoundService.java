package com.tapestry.moic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.entity.JoorOrderItem;
import com.tapestry.moic.repository.JoorOrderItemRepository;
import com.tapestry.moic.service.interfaces.IJoorOrderItemInBoundService;

/**
 * The Class IJoorOrderItemInBoundService.
 * 
 * @version 0.0.1
 * 
 */
@Component
public class JoorOrderItemInBoundService implements IJoorOrderItemInBoundService{

	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(JoorOrderItemInBoundService.class);
	
	/**
	 * Field productMasterRepository;
	 */
	@Autowired
	private JoorOrderItemRepository joorOrderItemRepository;
	
	@Override
	public JoorOrderItem addJoorOrderItemInfo(JoorOrderItem joorOrderItem) {
		LOGGER.debug("inside addProductMasterInfo ");
		if(joorOrderItem != null) {
			return joorOrderItemRepository.save(joorOrderItem);
		}
		return joorOrderItem;
	}

	@Override
	public List<JoorOrderItem> getAllJoorOrderItemInfo() {
		LOGGER.debug("inside addProductMasterInfo ");
		List <JoorOrderItem> joorOrderItemList = new ArrayList<>();
		joorOrderItemList = joorOrderItemRepository.findAll();
		return joorOrderItemList;	
	}
}
