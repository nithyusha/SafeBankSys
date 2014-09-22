package com.ss.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionInterceptor {
	
	@AfterThrowing(pointcut = "within(com.ss.controller..*)", throwing = "t")
	public void toRuntimeException(Throwable t) 
			throws AccessException {
		if (t instanceof AccessException) {
			//logger.error(t.getMessage(), t);
			throw (AccessException) t;
		}
		
	}


}
