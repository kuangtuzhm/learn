package com.zealot.learn.spi.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zealot.learn.spi.spring.service.ZhmLogService;
import com.zealot.learn.spi.spring.service.ZhmSearchService;
import com.zealot.learn.spi.spring.service.ZhmSimpleService;


@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController{
	
	@Autowired
	private ZhmLogService zhmLogService;
	
	@Autowired
	private ZhmSearchService zhmSearchService;
	
	@Autowired
	private ZhmSimpleService zhmSimpleService;

	@RequestMapping(value = "/list")
	@ResponseBody
	public String toList(HttpServletRequest req, Model model){

		zhmLogService.saveLogs();
		return "111";
	}
	
	@RequestMapping(value = "/search")
	@ResponseBody
	public String search(HttpServletRequest req, Model model){

		zhmSearchService.search("zhm");
		return "222";
	}
	
	@RequestMapping(value = "/simple")
	@ResponseBody
	public String simple(HttpServletRequest req, Model model){

		String name = zhmSimpleService.getName(123);
		return name;
	}
}
