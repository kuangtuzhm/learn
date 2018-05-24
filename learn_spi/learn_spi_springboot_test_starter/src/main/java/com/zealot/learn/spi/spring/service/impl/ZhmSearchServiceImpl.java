package com.zealot.learn.spi.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zealot.learn.spi.spring.service.ZhmSearchService;

public class ZhmSearchServiceImpl implements ZhmSearchService {

	private final static Logger logger = LoggerFactory.getLogger(ZhmSearchServiceImpl.class);	
	
	@Override
	public String search(String key) {
		logger.info("zhmSearchServiceImpl.........................");
		return null;
	}

}
