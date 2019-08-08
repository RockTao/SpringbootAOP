package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tianhao on 2019-08-08.
 */
@RestController
@RequestMapping("/myAapect")
public class MyAspectController {

	@RequestMapping("/getId")
	public Exception TestGetId(String id){
		System.out.println("id = [" + id + "]");
//		int a = 25/0;
//		return "id=" + id + "我是调戏你的" + a ;
		return new NullPointerException();
	}
}
