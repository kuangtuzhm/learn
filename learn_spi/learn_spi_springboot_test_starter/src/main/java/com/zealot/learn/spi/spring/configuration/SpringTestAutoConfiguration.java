package com.zealot.learn.spi.spring.configuration;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zealot.learn.spi.spring.service.ZhmLogService;
import com.zealot.learn.spi.spring.service.ZhmSearchService;
import com.zealot.learn.spi.spring.service.ZhmSimpleService;
import com.zealot.learn.spi.spring.service.impl.ZhmSearchServiceImpl;

//@Configuration
@ConditionalOnBean(annotation = EnableZhmLogServiceConfig.class)
public class SpringTestAutoConfiguration {

	@Bean
	public ZhmLogService springTestService()
	{
		ZhmLogService service = new ZhmLogService();
		return service;
	}
	
	@Bean
	public ZhmSearchService zhmSearchService()
	{
		ZhmSearchService service = new ZhmSearchServiceImpl();
		return service;
	}
	
	@Bean
	public ZhmSimpleService zhmSimpleService()
	{
		ServiceLoader<ZhmSimpleService> s = ServiceLoader.load(ZhmSimpleService.class);    
        Iterator<ZhmSimpleService> searchs = s.iterator();    
        ZhmSimpleService service = null;
        while (searchs.hasNext()) {    
        	service = searchs.next(); 
        }   
        return service;
	}
}
