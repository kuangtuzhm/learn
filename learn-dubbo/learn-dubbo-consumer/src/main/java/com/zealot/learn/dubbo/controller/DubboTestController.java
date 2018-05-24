package com.zealot.learn.dubbo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zealot.learn.dubbo.service.StudentService;
import com.zealot.learn.dubbo.service.TeacherService;

@Controller
@RequestMapping(value = "/dubbo")
public class DubboTestController {
	
	private final static Logger logger = LoggerFactory.getLogger(DubboTestController.class);	

	@Reference(registry = "${dubbo.registries.my1.id}",
			version = "${demo.service.version}")
	private StudentService studentService;
	
	@Reference(registry = "${dubbo.registries.my-registry.id}",
			version = "${demo.service.version}")
	private TeacherService teacherService;
	
	@RequestMapping(value = "/stu")
	@ResponseBody
	public String stu(HttpServletRequest req, Model model){

		String name = studentService.getStudentName("zhm");
		logger.info(name);
		return name;
	}
	
	@RequestMapping(value = "/tea")
	@ResponseBody
	public String tea(HttpServletRequest req, Model model){

		String name = teacherService.getTeacherName("zhm");
		logger.info(name);
		return name;
	}
}
