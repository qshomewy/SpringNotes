package com.qs.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author : qshomewy
 * @description : 自定义切面
 */
public class CustomAdvice {

	public void before(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("before前置通知, 方法" + name + ", 参数"+Arrays.toString(args));
	}
	
	// 可以访问返回值, 抛出异常后不会被执行
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("afterReturning后置通知, 返回" + result);
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("around环绕通知, 前");
        // 调用目标方法
		Object proceed = joinPoint.proceed();
        System.out.println("around环绕通知, 后");
        return proceed;
	}
	
	public void afterException(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterException异常通知, " + exception);
	}
	
	// 总会执行，但是不能访问到返回值
	public void after(JoinPoint joinPoint) {
		System.out.println("after后置通知");
	}
}
