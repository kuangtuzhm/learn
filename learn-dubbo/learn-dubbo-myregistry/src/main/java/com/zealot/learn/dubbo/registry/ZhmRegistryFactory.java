package com.zealot.learn.dubbo.registry;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

public class ZhmRegistryFactory implements RegistryFactory {

	@Override
	public Registry getRegistry(URL url) {
		System.out.println("进入注册中心ZhmRegistryFactory。。。。。。。。。。。url="+url);
		ZhmRegistry r = new ZhmRegistry(url);
		return r;
	}

}
