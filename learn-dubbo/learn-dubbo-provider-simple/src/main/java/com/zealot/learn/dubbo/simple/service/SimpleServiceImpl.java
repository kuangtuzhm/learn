package com.zealot.learn.dubbo.simple.service;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

@Service(
		interfaceClass = SimpleService.class,
        version = "${demo.service.version}"
)
@Component
public class SimpleServiceImpl implements SimpleService {

	@Override
	public String getName(String id) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "这个是一个简单的dubbo测试..........";
	}

}
