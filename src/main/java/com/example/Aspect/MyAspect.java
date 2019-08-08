package com.example.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tianhao on 2019-08-08.
 */
@Configuration
@Aspect
public class MyAspect {
	// 前置通知和后置通知类似，了解即可。主要掌握环绕通知

	//	//前置通知
//	@Before("within(com.example.controller.MyAspectController)")
//	public void BeforeSearch(JoinPoint joinPoint){
//		System.out.println("目标类："+joinPoint.getTarget());
//		System.out.println("方法名："+joinPoint.getSignature().getName());
//		System.out.println("方法参数："+joinPoint.getArgs());
//		System.out.println("=========这是为查询所有开发的前置通知========");
//	}
//	//后置通知
//	@After("execution(* com.example.controller.MyAspectController.*(..))")
//	public void AfterTest(JoinPoint joinPoint){
//		System.out.println("===========后置通知===========");
//		System.out.println("目标类："+joinPoint.getTarget());
//		System.out.println("方法名："+joinPoint.getSignature().getName());
//		System.out.println("方法参数："+joinPoint.getArgs());
//	}
	@Around("execution(* com.example.controller.MyAspectController.*(..))")
	public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("目标类："+proceedingJoinPoint.getTarget());
		System.out.println("目标方法："+proceedingJoinPoint.getSignature().getName());
		System.out.println("目标方法参数："+proceedingJoinPoint.getArgs());
		System.out.println("==========进入环绕通知之前=========");
		Object proceed = null;
		try {
			proceed = proceedingJoinPoint.proceed();   //放行
			System.out.println("目标方法的返回值："+proceed);
			System.out.println("========执行目标方法之后的操作========");

			return proceed;      //将目标方法执行的结果返回

		} catch (Throwable throwable) {
			System.out.println("==========异常通知=========");
			throwable.printStackTrace();
		}
		return null;
	}


}