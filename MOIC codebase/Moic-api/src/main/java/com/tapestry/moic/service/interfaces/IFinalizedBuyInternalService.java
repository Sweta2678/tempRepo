package com.tapestry.moic.service.interfaces;

import org.springframework.stereotype.Service;

/**
 *  The Interface IFinalizedBuyInternalService.
 * 
 * @version 0.0.1
 * 
 */

@Service
public interface IFinalizedBuyInternalService {

	void populateSKUChangeReport();
	
	void populateMOQReport();
	
	void filterAndPopulateFinalizedBuy();
}
