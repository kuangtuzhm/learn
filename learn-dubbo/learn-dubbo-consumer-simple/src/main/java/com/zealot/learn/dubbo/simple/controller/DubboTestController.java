package com.zealot.learn.dubbo.simple.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zealot.learn.dubbo.simple.service.SimpleService;

@Controller
@RequestMapping(value = "/dubbo")
public class DubboTestController {
	
	private final static Logger logger = LoggerFactory.getLogger(DubboTestController.class);	

	@Reference(version="${demo.service.version}")
	private SimpleService simpleService;
	
	
	@RequestMapping(value = "/sim")
	@ResponseBody
	public String stu(HttpServletRequest req, Model model){

		String name = simpleService.getName("zhm");
		logger.info(name);
		return name;
	}
}
