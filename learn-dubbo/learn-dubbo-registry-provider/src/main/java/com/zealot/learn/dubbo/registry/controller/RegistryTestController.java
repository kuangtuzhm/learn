package com.zealot.learn.dubbo.registry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/registry")
public class RegistryTestController {
	
	private final static Logger logger = LoggerFactory.getLogger(RegistryTestController.class);	
	
	private static Map<String,String> map= new HashMap<>();

	
	@RequestMapping(value = "/conn")
	@ResponseBody
	public String conn(HttpServletRequest req, Model model){

		String r = "这是一个自定义注册中心进入链接。。。。。。。。。。。。。";
		logger.info(r);
		return r;
	}
	
	@RequestMapping(value = "/reg")
	@ResponseBody
	public String reg(HttpServletRequest req, Model model){

		String r = "自定义注册中心注册服务。。。。。。。。。。。。。";
		String interfaceClass = req.getParameter("interface");
		logger.info(r);
		return r;
	}
	
	@RequestMapping(value = "/scr")
	@ResponseBody
	public String scr(HttpServletRequest req, Model model){

		String r = "自定义注册中心订阅服务。。。。。。。。。。。。。";
		String interfaceClass = req.getParameter("interface");
		logger.info(r);
		return r;
	}
}
