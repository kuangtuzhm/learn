package com.zealot.learn.spi.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZhmLogService {

	private final static Logger logger = LoggerFactory.getLogger(ZhmLogService.class);	
	
	public void saveLogs() {
		
		logger.info("SpringTestService.saveLogs............................");
	}
}
