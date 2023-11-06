package com.tapestry.moic.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tapestry.moic.entity.JoorOrder;


/**
 * The Interface IJoorOrderInBoundService.
 * 
 * @version 0.0.1
 * 
 */
@Service
public interface IJoorOrderInBoundService {
	
	public JoorOrder addJoorOrder(JoorOrder joorOrder);
	
	public List<JoorOrder> getAllJoorOrder();
	
	public Optional<JoorOrder> getSpecificJoorOrder(int orderID);
}
