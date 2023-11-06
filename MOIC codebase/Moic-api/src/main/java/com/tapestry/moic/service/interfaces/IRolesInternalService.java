package com.tapestry.moic.service.interfaces;

import org.springframework.stereotype.Service;

import com.tapestry.moic.dto.RolesDto;

/**
 * The Interface IRolesInternalService.
 * 
 * @version 0.0.1
 * 
 */

@Service
public interface IRolesInternalService {

	public RolesDto getRolePermissions(String token);

}
