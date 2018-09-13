package com.sp.avalon.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformanceTimingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		//Before method is invoked
		long startTime=System.nanoTime();

		try {
			//Proceed to target method
			Object returnValue= method.proceed(); /* This line is a must*/
			return returnValue; /* This line is a must else client doesnt get result back*/
		}
		finally {

			//After method has been invoked and finished running, the return value is catched by returnValue!!!!
			long endTime=System.nanoTime();

			long timeTaken=endTime-startTime;

			System.out.println("The method "+method.getMethod().getName()+" took "+ timeTaken);
		}

	}

}
