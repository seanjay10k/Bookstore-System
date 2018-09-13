package com.sp.avalon.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceTimingAdviceAnnotated {

	@Pointcut("execution(* com.sp.avalon.services.*.*(..))")
	public void allServiceMethods() {};

	@Around("allServiceMethods()")
	public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable
	{
		//Before method is invoked
		long startTime=System.nanoTime();

		try {
			//Proceed to target method
			Object returnValue=method.proceed(); /* This line is a must*/
			return returnValue; /* This line is a must else client doesnt get result back*/
		}
		finally {

			//After method has been invoked and finished running, the return value is catched by returnValue!!!!
			long endTime=System.nanoTime();

			long timeTaken=endTime-startTime;

			System.out.println("The method "+method.getSignature().getName()+" took "+ timeTaken);
		}
	}
	@Before("allServiceMethods()")
	public void beforeAdviceTesting(JoinPoint method) {
		System.out.println("Now entering a method "+method.getSignature().getName());
	}
}
