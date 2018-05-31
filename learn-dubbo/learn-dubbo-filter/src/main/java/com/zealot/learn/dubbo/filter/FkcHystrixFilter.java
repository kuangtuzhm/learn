/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2018.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording, 
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 * 
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2018年3月23日    TangYuping         Create the class
 * http://www.jimilab.com/
*/

package com.zealot.learn.dubbo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.HystrixCommand.Setter;

/**
 * @FileName FkcHystrixFilter.java
 * @Description: 
 *
 * @Date 2018年3月23日 上午11:21:51
 * @author TangYuping
 * @version 1.0
 */
@Activate(group = Constants.CONSUMER)
public class FkcHystrixFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(FkcHystrixFilter.class);
	
	public HyStrixProperties hyStrixProperties;
	
	public void setHyStrixProperties(HyStrixProperties hyStrixProperties) {
		this.hyStrixProperties = hyStrixProperties;
	}

	@Override
	public Result invoke(Invoker invoker, Invocation invocation) throws RpcException {
		//进入Filter 后会把初始化的HyStrixProperties对象重新生成一次，所以用getBean的方式获取
		//HyStrixProperties hp = SpringContextHolder.getBean(HyStrixProperties.class);
	        Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(invoker.getInterface().getName()));//被调用服务
	        setter.andCommandKey(HystrixCommandKey.Factory.asKey(String.format("%s_%d", invocation.getMethodName(),
                    invocation.getArguments() == null ? 0 : invocation.getArguments().length)));//被调用服务的一个被调用方法
	        setter.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
	        		 .withExecutionTimeoutEnabled(true) 
	        		.withExecutionTimeoutInMilliseconds(hyStrixProperties.getTimeoutInMillions()) //超时时间，默认1秒
	        		.withCircuitBreakerRequestVolumeThreshold(hyStrixProperties.getRequestVolumeThreshold())  // 默认20，10秒钟内至少20次请求，熔断器才发挥起作用
	        		.withCircuitBreakerErrorThresholdPercentage(hyStrixProperties.getErrorThresholdPercentage()) // 当出错率超过20%后熔断器启动.
	        		.withCircuitBreakerSleepWindowInMilliseconds(hyStrixProperties.getSleepWindowInMilliseconds()) // 熔断器中断请求3秒后会关闭重试,如果请求仍然失败,继续打开熔断器3秒
	        		);
	        setter.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
					.withCoreSize(hyStrixProperties.getCoreSize())                       //线程池核心线程数
					.withMaxQueueSize(hyStrixProperties.getMaxQueueSize())                   //线程池队列最大值
					.withQueueSizeRejectionThreshold(hyStrixProperties.getQueueSizeRejectionThreshold()) //当前队列大小，即实际队列大小由这个参数决定
					);
		FkcHystrixCommand command = new FkcHystrixCommand(setter, invoker, invocation);
        return command.execute();
	}

}
