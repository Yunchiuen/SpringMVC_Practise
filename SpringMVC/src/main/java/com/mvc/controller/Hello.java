package com.mvc.controller;

import java.util.Date;
import java.util.Optional;

import javax.ws.rs.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/hello")
public class Hello {
	@RequestMapping(value = "/greet")
	@ResponseBody
	public String greet() {
		return "hello greet";
	}

	@RequestMapping(value = "/time")
	@ResponseBody
	public String time() {
		return new Date().toString();
	}

	/*
	 * 路徑:/hello/sayhi?name=Yun&age=18 
	 * 帶入:name與age兩個參數
	 */
	@RequestMapping(value = "/sayhi")
	@ResponseBody
	public String sayhi(@RequestParam(value = "name", defaultValue = "unkown", required = false) String name,
			@RequestParam(value = "age", defaultValue = "20", required = false) Integer age) {
		return "name:" + name + " age:" + age;
	}

	/*
	 * 路徑:/hello/bmi?h=170&w=60 
	 * 帶入:h與w兩個參數 
	 * 結果:bmi=20.76
	 */
	@RequestMapping(value = "/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h", required = false, defaultValue = "170") Double h,
			@RequestParam(value = "w", required = false, defaultValue = "60") Double w) {
		Double result = w / Math.pow(h / 100, 2);
		String bmi = String.format("h: %.2f w:%.2f bmi:%.2f", h, w, result);
		return bmi;
	}

	/*
	 * PathVariable 
	 * 路徑:/hello/exam/75 -> 結果:75 pass 
	 * 路徑:/hello/exam/45 -> 結果:45 fail
	 */
	@RequestMapping(value = "/exam/{score}")
	@ResponseBody
	public String exam(@PathVariable("score") Integer score) {
		return score + " " + ((score >= 60) ? "pass" : "fail");
	}

	/*
	 * PathVariable + RequestParam 
	 * 路徑:/hello/calc/add?x=30&y=20 -> 結果:50
	 * 路徑:/hello/calc/sub?x=30&y=20 -> 結果:10 
	 * 路徑:/hello/calc/sub?y=20		  -> 結果:20
	 * 路徑:/hello/calc/sub?x=0&y=20	  -> 結果:-20 
	 * 路徑:/hello/calc/add			  -> 結果:0
	 * 路徑:/hello/calc/div 			  -> 結果:"note" 無此路徑
	 */
	@RequestMapping(value = "/calc/{exp}")
	@ResponseBody
	public String calc(@PathVariable("exp") String exp,
			@RequestParam(value = "x", required = false) Optional<Integer> x,
			@RequestParam(value = "y", required = false) Optional<Integer> y) {

		if (x.isPresent() && y.isPresent()) {
			switch (exp) {
			case "add":
				return x.get() + y.get()+"";
			case "sub":
				return x.get() - y.get()+"";
			default:
				return "note";
			}
		}
		if (x.isPresent()) {
			return x.get()+"";
		}
		if (y.isPresent()) {
			return y.get()+"";
		}
		return "0";
	}

	/*
	 * PathVariable 
	 * 萬用字元 * 任意多字, 任意字元, ?任意一字
	 */
	@RequestMapping(value = "/any/*/java?")
	@ResponseBody
	public String exam() {
		return "any";
	}
	/*
	 * 得到多筆資料
	 * 
	 */
}
