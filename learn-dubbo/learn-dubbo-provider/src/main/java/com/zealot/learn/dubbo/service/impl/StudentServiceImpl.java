package com.zealot.learn.dubbo.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.zealot.learn.dubbo.service.StudentService;

@Service(
		interfaceClass = StudentService.class,
        version = "${demo.service.version}",
        protocol = "${dubbo.protocols.my-protocol.id}",
        registry = {"${dubbo.registries.my1.id}","${dubbo.registries.my-registry.id}"}
)
@Component
public class StudentServiceImpl implements StudentService {

	@Override
	public String getStudentName(String id) {

		return "这个学生名字叫狗蛋。。。。。。。。。。。";
	}

}
