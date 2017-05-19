package com.qph.app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qph.app.domain.RoleRepository;
import com.qph.app.domain.UserRepository;
import com.qph.app.domain.pojo.SysRole;
import com.qph.app.domain.pojo.SysUser;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserDaoTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserDaoTest.class);
	
	
	@Test
	public void testSave(){
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		for (int i = 0 , j = 30 ; i < j ; i++){
			SysUser user = new SysUser();
			user.setUsername("han"+i);
			user.setPassword("0");
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
	public void testGetData(){
		
		SysUser user3 = userRepository.findByUsername("admin");
		logger.info("user3 roles==>"+user3.getRoleList().size());
		
		SysUser user4 = userRepository.findByUsername("admin");
		logger.info("user4 roles==>"+user4.getRoleList().size());
		
		List<SysUser> users = userRepository.findAll();
		logger.info("users==>"+users.size());
		
		Pageable page = new PageRequest(0, 15);
		Page<SysUser> page1 = userRepository.findAll(page);
		logger.info("page1==>"+page1.getContent().size());
		logger.info("page1 hasNext==>"+page1.hasNext());
		logger.info("page1 getPageNumber==>"+page1.nextPageable().getPageNumber());
	}
	
}
