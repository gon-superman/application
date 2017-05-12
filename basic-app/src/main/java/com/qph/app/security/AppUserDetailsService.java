package com.qph.app.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.qph.app.domain.UserRepository;
import com.qph.app.domain.pojo.SysRole;
import com.qph.app.domain.pojo.SysUser;


@Component
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository repository;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 SysUser sysUser = repository.findByUsername(username);
		 
		 if (sysUser == null){
			 throw new UsernameNotFoundException("username not exists !");
		 }
		 
		 Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		 Set<SysRole> roles = sysUser.getRoleList();
		 
		 if (roles != null && roles.size() > 0){
			 for (SysRole role : roles){
				 authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getId()));
			 }
		 }
		
		 User user = new User(username, sysUser.getPassword(), sysUser.isEnabled(), true, true, true, authorities);
		return user;
	}

}
