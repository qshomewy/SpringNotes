package com.qs.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : qshomewy
 * @description : 
 */
@Order(2)
@Aspect
@Component
public class SecondAdvice {

	@Pointcut("execution(* com.qs.service.OrderService.deleteOrder(..))")
    private void pointCut() {

    }
	
	public void before(JoinPoint joinPoint) {
		System.out.println("切面二before");
	}
	
	@AfterReturning(pointcut = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("切面二afterReturning" + result);
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("切面二around前");
        //调用目标方法
        Object proceed = joinPoint.proceed();
        System.out.println("切面二around后");
        return proceed;
    }


    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("切面二after");
    }
}
