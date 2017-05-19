package com.qph.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qph.app.domain.pojo.SysRole;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysRoleServiceTest {

	@Autowired
	private RoleService roleService;
	
	@Test
	public void testUseCache(){
		Long id = Long.valueOf(90);
		SysRole role1 = roleService.getOne(id);
		System.out.println("role1===>"+role1.getDescription());
		
		SysRole role2 = roleService.getOne(id);
		System.out.println("role1===>"+role2.getDescription());
		
		role2.setDescription("哈哈哈");
		roleService.save(role2);
		
		SysRole role3 = roleService.getOne(id);
		System.out.println("role1===>"+role3.getDescription());
	}
}
