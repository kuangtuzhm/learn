package com.zealot.learn.dubbo.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.zealot.learn.dubbo.service.TeacherService;

@Service(
		interfaceClass = TeacherService.class,
        version = "${demo.service.version}",
        protocol = "${dubbo.protocols.my-protocol.id}",
        registry = "${dubbo.registries.my-registry.id}"
)
@Component
public class TeacherServiceImpl implements TeacherService {

	@Override
	public String getTeacherName(String id) {
		
		return "这个老师名字叫老王..........";
	}

}
