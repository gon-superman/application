package com.qph.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

	@GetMapping("/list")
	String list(){
		return "/role/list";
	}
	
	@GetMapping
	String add(){
		return "/role/add";
	}
}
