package com.zealot.learn.dubbo.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);

	@Reference(version="${demo.service.version}")
	private SimpleService simpleService;
	
	
	@RequestMapping(value = "/filter")
	@ResponseBody
	public String stu(HttpServletRequest req, Model model){

		String name = "";
		
		  for (int i = 0; i < 100; i++) {
		   final int index = i;
		   fixedThreadPool.execute(new Runnable() {
		    public void run() {

		    	String s = simpleService.getName("zhm");
				 logger.info("i="+index+","+s);
		   
		    }
		   });
		  }
		return name;
	}
}
