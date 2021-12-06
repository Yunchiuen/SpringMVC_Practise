package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/hi")
public class Hi {
	@RequestMapping(value = "/greet")
	@ResponseBody
	public String greet() {
		return "hi greet";
	}
	
	
}
