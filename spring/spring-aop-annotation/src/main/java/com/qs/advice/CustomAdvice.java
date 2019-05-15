package com.qs.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author : qshomewy
 * @description : 自定义切面
 */
@Aspect
@Component// 除了加上@Aspect外，还需要声明为spring的组件 @Aspect只是一个切面声明
public class CustomAdvice {
	
	/**
     * 使用 || , or  表示或
     * 使用 && , and 表示与
     * ! 表示非
     */
	@Pointcut("execution(* com.qs.service.OrderService.*(..)) && !execution(* com.qs.service.OrderService.deleteOrder(..))")
	private void pointCut() {
		
	}

	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("before前置通知, 方法" + name + ", 传参" + Arrays.toString(args));
	}
	
	// 抛出异常后不会被执行
	@AfterReturning(pointcut = "pointCut()", returning = "result")
	// returning 参数用于指定返回结果与哪一个参数绑定
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("afterReturning后置结果通知, 返回" + result);
	}
	
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("around环绕通知, 前");
        // 调用目标方法
		Object proceed = joinPoint.proceed();
        System.out.println("around环绕通知, 后");
        return proceed;
	}
	
	@AfterThrowing(pointcut = "pointCut()", throwing = "exception")
	public void afterException(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterException异常通知, " + exception);
	}
	
	//  总会执行 但是不能访问到返回值
	@After("pointCut()")
	public void after(JoinPoint joinPoint) {
		System.out.println("after后置通知");
	}
}
