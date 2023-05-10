package net.shinhan.advice;

import org.aopalliance.intercept.Joinpoint;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class LoggingAdvice{

	@Pointcut("execution(* com.shinhan.model.*.select*(..))")
	public void cut1() {}
	
	//주 업무 전후에 수행
	@Around("cut1()")
	public Object aroundTarget2(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[주 업무 전에 수행]"+joinPoint.getSignature().getName()+"");
		//--보조 업무가 주 업무에 끼얻ㄹ었으므로
		//주업무를 수행하도록 해야한다
		Object obj = joinPoint.proceed();

		System.out.println("[주 업무 후에 수행]"+joinPoint.getSignature().getName()+"");
		return obj;
		
	}
	

}
