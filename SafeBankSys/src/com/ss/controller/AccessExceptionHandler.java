package com.ss.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ss.exception.AccessException;

@ControllerAdvice
@EnableWebMvc
public class AccessExceptionHandler {
	
	@ExceptionHandler(AccessException.class)
	public ModelAndView handleUserAccessException(AccessException ex) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("auth/accessissue");
		modelAndView.addObject("ex_name", ex.getClass().getName());
		modelAndView.addObject("ex_message", ex.getMessage());
		//logger.error(ex.getMessage(), ex);
		return modelAndView;
	}

}
