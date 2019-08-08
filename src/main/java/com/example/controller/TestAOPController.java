package com.example.controller;

/**
 * Created by Tianhao on 2019-08-08.
 */
import com.example.annoation.TestAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/test")
public class TestAOPController {


	@RequestMapping("/show3")
	@ResponseBody
	@TestAnnotation("测试")   // 加上测试注解
	public String getById() {
		System.out.println("11111111111111s");
		return "hello";
	}
}
