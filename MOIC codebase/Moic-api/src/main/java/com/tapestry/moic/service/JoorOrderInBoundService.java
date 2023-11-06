package com.tapestry.moic.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tapestry.moic.entity.JoorOrder;
import com.tapestry.moic.repository.JoorOrderRepository;
import com.tapestry.moic.service.interfaces.IJoorOrderInBoundService;

/**
 * The Class JoorOrderInBoundService.
 * 
 * @version 0.0.1
 * 
 */
@Component
public class JoorOrderInBoundService implements IJoorOrderInBoundService{

	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(JoorOrderInBoundService.class);
	
	/**
	 * Field joorOrderRepository;
	 */
	@Autowired
	private JoorOrderRepository joorOrderRepository;
	
	@Override
	public JoorOrder addJoorOrder(JoorOrder joorOrder) {
		LOGGER.debug("inside addProductMasterInfo ");
		if(joorOrder != null) {
			return joorOrderRepository.save(joorOrder);
		}
		return joorOrder;
	}

	@Override
	public List<JoorOrder> getAllJoorOrder(){
		return joorOrderRepository.findAll();
	}
	
	public Optional<JoorOrder> getSpecificJoorOrder(int orderID) {
		return null;
	}
}