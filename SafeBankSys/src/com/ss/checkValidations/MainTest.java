package com.ss.checkValidations;

public class MainTest {

	public static void main(String a[]) {
	
	EmailValidator ev = new EmailValidator();
	System.out.println(ev.validate("shrikantkakani1990@gmail.com"));
	
	PasswordValidator pv = new PasswordValidator();
	System.out.println(pv.validate("ShrikantKakani.7"));
	
	}
	
}
