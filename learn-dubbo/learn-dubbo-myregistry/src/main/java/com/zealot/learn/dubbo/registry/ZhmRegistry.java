package com.zealot.learn.dubbo.registry;

import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.NotifyListener;
import com.alibaba.dubbo.registry.Registry;
import com.zealot.learn.dubbo.util.HttpUtil;

public class ZhmRegistry implements Registry {
	
	private String registryUrl;
	
	public ZhmRegistry(URL url)
	{
		registryUrl = "http://"+url.getHost()+":"+url.getPort();
		HttpUtil.doPost(registryUrl+"/registry/conn", url.getParameters());
	}

	@Override
	public URL getUrl() {
		System.out.println("ZhmRegistry.getUrl.............");
		return null;
	}

	@Override
	public boolean isAvailable() {
		System.out.println("ZhmRegistry.isAvailable.............");
		return false;
	}

	@Override
	public void destroy() {
		System.out.println("ZhmRegistry.destroy.............");

	}

	@Override
	public void register(URL url) {
		System.out.println("ZhmRegistry.register.............url="+url);
		Map<String, String> params = url.getParameters();
		HttpUtil.doPost(registryUrl+"/registry/reg", params);
	}

	@Override
	public void unregister(URL url) {
		System.out.println("ZhmRegistry.unregister.............url="+url);
	}

	@Override
	public void subscribe(URL url, NotifyListener listener) {
		System.out.println("ZhmRegistry.subscribe.............url="+url);
		Map<String, String> params = url.getParameters();
		HttpUtil.doPost(registryUrl+"/registry/scr", params);
	}

	@Override
	public void unsubscribe(URL url, NotifyListener listener) {
		System.out.println("ZhmRegistry.unsubscribe.............url="+url);
	}

	@Override
	public List<URL> lookup(URL url) {
		System.out.println("ZhmRegistry.lookup.............url="+url);
		return null;
	}

}
