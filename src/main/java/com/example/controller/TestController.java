package com.example.controller;

import com.example.annoation.TestAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tianhao on 2019-08-08.
 */
@RestController
@RequestMapping("/con")
public class TestController {
	@RequestMapping("/show")
	@ResponseBody
	@TestAnnotation("111111111111111111ss")   // 加上测试注解
	public String aop(){
		System.out.println("2222222222");
		return "2222222222";
	}
}
