package com.sp.avalon.advice;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.aop.AfterReturningAdvice;

public class AfterAdviceMock implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object arg3) throws Throwable {

		System.out.println("I run after the method is executed and returnValue is the actual return value from that method.");
		if(returnValue instanceof java.util.List) //ie if the returnValue is a List
		{
			List returnList=(List) returnValue;//cast the object to List
			returnList.clear(); //and clear it. Now client receives empty List!!!!
		}
	}

}
