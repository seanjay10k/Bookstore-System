package com.sp.avalon.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundAdviceMock implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		/*do sth BEFORE method is executed +-
		 */

		try{
			//execute the method
			Object returnValue= method.proceed();// whatever method returns is now in the returnValue!!!!
			return returnValue;
		}
		finally 
		{
			/*do sth after method is done executing eg. stop timer.
			 * Now, you can CALCULATE how long the method took to execute and log it*/
			/*Note:
			 * try block is there because line 16 might return with an exception depending on which method is being executed.
			 * if exception was returned, line 17 does not execute! and this method is also terminated(??) by throwing an exception.
			 * We don't catch it here but pass it to the client coz Client called us.
			 * 
			 * finally block runs no matter what because 
			 * 1. The exception causing statement is in try block
			 * 2. The method has throws clause
			 */
		}
	}

}
