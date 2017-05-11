package com.qph.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qph.app.domain.pojo.SysUser;


@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/list")
	String list(){
		return "/user/list";
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
