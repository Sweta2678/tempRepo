package com.tapestry.moic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.dto.RolesDto;
import com.tapestry.moic.dto.TokenDto;
import com.tapestry.moic.entity.MoicUser;
import com.tapestry.moic.entity.Roles;
import com.tapestry.moic.repository.RolesRepository;
import com.tapestry.moic.repository.UserRepository;
import com.tapestry.moic.service.interfaces.IRolesInternalService;
import com.tapestry.moic.utils.MoicUtil;

/**
 * The Class RolesInternalService.
 * 
 * @version 0.0.1
 * 
 */
@Component
public class RolesInternalService implements IRolesInternalService {
	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(RolesInternalService.class);

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public RolesDto getRolePermissions(String token) {
		LOGGER.debug("Inside getRolePermissions Service token :: " + token);

		TokenDto tokenDto = MoicUtil.getDecryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, token);
		if(tokenDto != null && !tokenDto.getEmailAddress().isEmpty()) {
			
			MoicUser moicUser = userRepository.findByEmailAddress(tokenDto.getEmailAddress());
			if(moicUser == null)
				return null;
			else {
				Roles roles = rolesRepository.findByRoleId(moicUser.getRoleId());
				ObjectMapper objectMapper = new ObjectMapper();
				RolesDto rolesDto = objectMapper.convertValue(roles, RolesDto.class);
				return rolesDto;
			}
		}
		return null;
	}
}
