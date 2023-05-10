package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	
Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@ExceptionHandler(Exception.class)
	public String ProcessException(Exception ex) {
		ex.printStackTrace();
		logger.info(ex.getMessage());
		return "/error/error500";
	}
}
