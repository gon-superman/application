package com.qph.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qph.app.domain.pojo.SysUser;
import com.qph.app.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	String list(){
		return "/user/list";
	}
	
	Page<SysUser> getPageList(PageRequest pageable){
		
		return userService.findAll(pageable);
	}
	
	@PreAuthorize("hasPermission('user', 'add')")
	@PostMapping("/add")
	String add(SysUser user){
		return "/user/add";
	}
	
	@PreAuthorize("hasPermission('user', 'view')")
	@GetMapping("/view")
	SysUser view(Long id){
		return null;
	}
}
