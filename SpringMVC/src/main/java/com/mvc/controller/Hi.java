package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/hi")
public class Hi {
	@RequestMapping(value = "/{welcome}",method = RequestMethod.GET)
	@ResponseBody
	public String greet(@PathVariable("welcome")String welcome,
						@RequestParam("name") String name) {
		//解決路徑中文問題
		//預設編碼ISO-8859-1
		//改變編碼UTF-8
		try {
			welcome=new String(welcome.getBytes("ISO-8859-1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return welcome+" "+name;
	}
	
	@GetMapping(value = "/sayhi")
	public ModelAndView sayhi() {
		ModelAndView mav=new ModelAndView();
		//mav.setViewName("/WEB-INF/view/sayhi.jsp");
		//在springmvc-servlet.xml定義ViewResolver 標籤的寫法
		mav.setViewName("/sayhi");
		mav.addObject("username", "john");
		return mav;
	}
	
	@GetMapping(value = "/sayhi2")
	public String sayhi2(Model model) {
		model.addAttribute("username", "Mary");
		//return "/WEB-INF/view/sayhi.jsp";//直接回傳view名子("字串")
		return "/sayhi";
	}
	
}
