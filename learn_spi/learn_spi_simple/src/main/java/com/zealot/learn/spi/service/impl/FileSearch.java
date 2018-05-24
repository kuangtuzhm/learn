package com.zealot.learn.spi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zealot.learn.spi.service.Search;

public class FileSearch implements Search {

	private final static Logger logger = LoggerFactory.getLogger(FileSearch.class);	
	
	@Override
	public List<String> search(String keyword) {
		logger.info("FileSearch...............");
		return null;
	}

}
