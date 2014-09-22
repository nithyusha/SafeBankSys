package com.ss.Aspects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ss.Aspects.annotations.AccessPolicies;
import com.ss.Aspects.annotations.ElementAccessPolicy;
import com.ss.exception.AccessException;
import com.ss.exception.ApplicationException;
import com.ss.service.DeptsManager;
import com.ss.service.UserManager;

@Aspect
@Component
public class AccessAspect 
{
	@Autowired
	DeptsManager deptsManager;
	
	@Autowired
	UserManager userManager;
	
	
	@Around("within(com.ss.controller..*) && @annotation(checks)")
	public Object chkAuthorization(ProceedingJoinPoint pjp, AccessPolicies checks) throws Throwable{
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = userManager.getIdByname(userName);
		boolean hasAccess = false;
		for(ElementAccessPolicy policy : checks.value())
		{
			for(String role : policy.role())
			{
				hasAccess = deptsManager.checkRole(id, policy.deptId(), role);
				if(hasAccess)
					 break;
			}
			if(hasAccess)
				 break;
			
		
		}
		if(!hasAccess)
		{
			throw new AccessException();
		}
		Object retVal = pjp.proceed();
		return retVal;
		
	}

}
