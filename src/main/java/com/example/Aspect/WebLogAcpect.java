package com.example.Aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Tianhao on 2019-08-08.
 */
@Aspect
@Component
public class WebLogAcpect {
	private Logger logger = LoggerFactory.getLogger(WebLogAcpect.class);



	/**
	 * 定义切入点，切入点为com.example.controller下的所有函数
	 */
	@Pointcut("execution(public * com.example.controller..*.*(..))")
	public void webLog(){}

	/**
	 * 前置通知：在连接点之前执行的通知
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
//		// 接收到请求，记录请求内容
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = attributes.getRequest();
//
//		// 记录下请求内容
//		logger.info("URL : " + request.getRequestURL().toString());
//		logger.info("HTTP_METHOD : " + request.getMethod());
//		logger.info("IP : " + request.getRemoteAddr());
//		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

		//TODO 上面注释的写法和下面的写法都是一样的 没有区别

		logger.info("前置通知");
		//获取目标方法的参数信息
		Object[] obj = joinPoint.getArgs();
		//AOP代理类的信息
		joinPoint.getThis();
		//代理的目标对象
		joinPoint.getTarget();
		//用的最多 通知的签名
		Signature signature = joinPoint.getSignature();
		//代理的是哪一个方法
		logger.info("代理的是哪一个方法"+signature.getName());
		//AOP代理类的名字
		logger.info("AOP代理类的名字"+signature.getDeclaringTypeName());
		//AOP代理类的类（class）信息
		signature.getDeclaringType();
		//获取RequestAttributes
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		//从获取RequestAttributes中获取HttpServletRequest的信息
		HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
		//如果要获取Session信息的话，可以这样写：
		//HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
		//获取请求参数
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String,String> parameterMap = Maps.newHashMap();
		while (enumeration.hasMoreElements()){
			String parameter = enumeration.nextElement();
			parameterMap.put(parameter,request.getParameter(parameter));
		}
		String str = JSON.toJSONString(parameterMap);
		if(obj.length > 0) {
			logger.info("请求的参数信息为："+str);
		}
	}

	@AfterReturning(returning = "ret",pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
	}

	@AfterReturning(pointcut = "webLog()",returning = "ret",argNames = "ret")
	public void doAfterReturningAdvice2(String ret){
		logger.info("第二个后置返回通知的返回值："+ret);
	}

//	@AfterThrowing(pointcut = "webLog()",throwing = "exception")
//	public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
//		//目标方法名：
//		logger.info(joinPoint.getSignature().getName());
//			if(exception instanceof NullPointerException){
//			logger.info("发生了空指针异常!!!!!");
//		}
//	}


}
