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
	
		
		//�� ���� ���Ŀ� ����
		@Around("targetmethod()")
		public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
			StopWatch watch = new StopWatch("���ð�");
			watch.start();
			//--���� ������ �� ������ ���򤩾����Ƿ�
			//�־����� �����ϵ��� �ؾ��Ѵ�
			Object obj = joinPoint.proceed();
			//��������
			watch.stop();
			System.out.println("�־��� ���� �ð�:" + watch.getTotalTimeMillis());
			System.out.println(watch.prettyPrint()+"StopWatchAdvice[�޼��� ȣ�� ]");
			
			return obj;
			
		}	
		
	

}
