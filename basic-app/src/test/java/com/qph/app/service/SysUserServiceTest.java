package com.qph.app.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qph.app.domain.RoleRepository;
import com.qph.app.domain.UserRepository;
import com.qph.app.domain.pojo.SysRole;
import com.qph.app.domain.pojo.SysUser;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserServiceTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Test
	public void testSave(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		for (int i = 0 , j = 9 ; i < j ; i++){
			SysUser user = new SysUser();
			user.setUsername("user"+i);
			user.setPassword(encoder.encode("0"));
			user.setEnabled(true);
			userRepository.save(user);
			
			SysRole role = new SysRole();
			role.setName("role"+i);
			roleRepository.save(role);
		}
	}
	
	@Test
	public void testSaveRoleAndUser(){
		SysUser user1 = userRepository.findOne(Long.valueOf(1));
		SysUser user2 = userRepository.findOne(Long.valueOf(2));
		SysUser user3 = userRepository.findOne(Long.valueOf(3));
		
		SysRole role1 = roleRepository.findOne(Long.valueOf(1));
		SysRole role2 = roleRepository.findOne(Long.valueOf(3));
		
		Set<SysUser> userList = new HashSet<>();
		userList.add(user3);
		userList.add(user2);
		
		Set<SysRole> roleList = new HashSet<>();
		roleList.add(role1);
		roleList.add(role2);
		
		user1.setRoleList(roleList);
		role2.setUserList(userList);
		
		userRepository.save(user1);
		roleRepository.save(role2);//失败，因为多对多关联只能由一方维护关系
	}
	
	@Test
	public void getGetData(){
		
		SysUser user3 = userRepository.findByUsername("admin");
		SysUser user4 = userRepository.findByUsername("admin");
		
		System.out.println(user3.getRoleList().size());
		System.out.println(user4.getRoleList().size());
	}
}
