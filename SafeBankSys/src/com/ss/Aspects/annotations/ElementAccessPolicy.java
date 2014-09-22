package com.ss.Aspects.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ElementAccessPolicy {
	
	//int userId = 0;
	String[] role();
	int deptId();
}
