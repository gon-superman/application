package com.qph.app.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AppPermissionEvaluator implements PermissionEvaluator{

	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		
		authentication.getAuthorities();
		
		return true;
	}

	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		
		return true;
	}

}
