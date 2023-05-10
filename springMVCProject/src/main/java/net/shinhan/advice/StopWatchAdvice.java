package net.shinhan.advice;

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
import org.springframework.util.StopWatch;

@Component
@Aspect
@Order(2)
public class StopWatchAdvice {

		@Pointcut("within(com.shinhan.model.EmpDAOMybatis)")
		public void targetmethod() {}
	
		
		//주 업무 전후에 수행
		@Around("targetmethod()")
		public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
			StopWatch watch = new StopWatch("계산시간");
			watch.start();
			//--보조 업무가 주 업무에 끼얻ㄹ었으므로
			//주업무를 수행하도록 해야한다
			Object obj = joinPoint.proceed();
			//보조업무
			watch.stop();
			System.out.println("주업무 수행 시간:" + watch.getTotalTimeMillis());
			System.out.println(watch.prettyPrint()+"StopWatchAdvice[메서드 호출 ]");
			
			return obj;
			
		}	
		
	

}
