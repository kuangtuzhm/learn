package com.zealot.learn.spi.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zealot.learn.spi.spring.service.ZhmSimpleService;

public class ZhmSimpleServiceImpl implements ZhmSimpleService {

	private final static Logger logger = LoggerFactory.getLogger(ZhmSimpleServiceImpl.class);	
	
	@Override
	public String getName(int id) {
		logger.info("ZhmSimpleServiceImpl.........................");
		return "id="+id+",name=zzzzzzzzzzzzz.........";
	}

}
