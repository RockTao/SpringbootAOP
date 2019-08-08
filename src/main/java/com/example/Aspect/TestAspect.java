package com.example.Aspect;

import com.example.annoation.TestAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * Created by Tianhao on 2019-08-08.
 */
@Aspect // FOR AOP
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行, 当然也可以不写这注解, 对于写和不写@order的两个切面, 有@order的优先于无@order的执行; 都有@order时, 越小越执先执行
@Component
public class TestAspect {
//
//	// @Before可以有两者写法, @annotation(形参test)
//	@Before("@annotation(test1)")// 拦截被TestAnnotation注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)，具体百度一下资料一大堆
//	public void beforeTest(JoinPoint point, TestAnnotation test1) throws Throwable {
//		System.out.println("beforeTest: test1" + test1.value());   // 直接获取注解参数
//	}
//
//	@After("@annotation(test2)")
//	public void afterTest(JoinPoint point, TestAnnotation test2) {
//		System.out.println("afterTest:" + test2.value());  // 直接获取注解参数
//	}


	// 指定切面
	@Pointcut("@annotation(com.example.annoation.TestAnnotation)")//对应的是注解所在路劲
	public void annotationPointCut() {
	}
	// @Before可以有两者写法, @annotation(函数名annotationPointCut)
	@Before("annotationPointCut()")
	public void before(JoinPoint joinPoint) {
		MethodSignature sign = (MethodSignature) joinPoint.getSignature();
		Method method = sign.getMethod();
		TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);   // 获取指定注解实例
		System.out.println("打印：" + annotation.value() + " 前置日志1");   // 获取注解实例的参数
	}
	@After("annotationPointCut()")
	public void afterTTT(JoinPoint point) {
		MethodSignature sign = (MethodSignature) point.getSignature();
		Method method = sign.getMethod();
		TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);  // 获取指定注解实例
		System.out.println("打印自带参数：" + annotation.value() + " 后置日志1");  // 获取注解实例的参数
	}


}
