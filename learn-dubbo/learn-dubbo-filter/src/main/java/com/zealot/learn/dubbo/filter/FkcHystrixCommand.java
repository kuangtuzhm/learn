package com.zealot.learn.dubbo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcResult;
import com.netflix.hystrix.HystrixCommand;
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
 * 2018年3月22日    TangYuping         Create the class
 * http://www.jimilab.com/
*/

/**
 * @FileName HystrixCommand.java
 * @Description:  熔断
 *
 * @Date 2018年3月22日 下午2:47:36
 * @author TangYuping
 * @version 1.0
 */
public class FkcHystrixCommand extends HystrixCommand<Result> {
	private static final Logger log = LoggerFactory.getLogger(FkcHystrixCommand.class);
	private Invoker       invoker;
	private Invocation       invocation;
	
	public FkcHystrixCommand(Setter setter, Invoker invoker, Invocation invocation) {
		// TODO 重新构造方法供外部调用，参数不可为空
		super(setter);
		this.invoker = invoker;
		this.invocation = invocation;
	}

	@Override
	protected Result run() throws Exception {
		// TODO 发送请求获取熔断情况
		log.info("发送请求查询熔断情况");
		return invoker.invoke(invocation);
	}
	
	@Override
	public Result getFallback(){
		// TODO 熔断时直接终止链接
		//getFailedExecutionException().getMessage();
		//log.error("调用方法名称："+invocation.getMethodName() + " 服务器熔断了, 请重新请求");
		//throw new RuntimeException(100 +"------服务器熔断了,请联系管理人员");
		RpcResult rs = new RpcResult();
		rs.setValue(null);
		log.error("调用方法名称："+invocation.getMethodName() + " 服务器熔断了, 请重新请求");
		return rs;
		
	}
	
}
