package com.qph.app.service;

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

import com.qph.app.domain.pojo.SysRole;
import com.qph.app.domain.pojo.SysUser;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserServiceTest.class);
	
	@Test
	public void testGetData(){
		Pageable page = new PageRequest(0, 15);
		Page<SysUser> page1 = userService.findAll(page);
		logger.info("page1==>"+page1.getContent().size());
		
		SysUser user = userService.findByUsername("admin");
		logger.info("user password==>"+user.getPassword());
		
		SysRole role = roleService.getOne(Long.valueOf(1));
		logger.info("role==>"+role.getName());
	}
	
	@Test
	public void testDelete(){
		userService.deleteAll();
		roleService.deleteAll();
	}
	
	@Test
	public void testUseCache(){
		Long id = Long.valueOf(121);
		SysUser user = userService.getOne(id);
		System.out.println("test===>"+user.getUsername());
		
		SysUser user0 = userService.getOne(id);
		System.out.println("test0===>"+user0.getUsername());
		
		SysUser sysUser = new SysUser();
		sysUser.setId(id);
		sysUser.setUsername("cacheChange");
		user.setPassword("0");
		userService.save(sysUser);
		
		SysUser user1 = userService.getOne(id);
		System.out.println("test1===>"+user1.getUsername());
		
//		userService.delete(Long.valueOf(91));
//		SysUser user2 = userService.getOne(id);
//		System.out.println("test3===>"+user2==null);
	}
	
}
