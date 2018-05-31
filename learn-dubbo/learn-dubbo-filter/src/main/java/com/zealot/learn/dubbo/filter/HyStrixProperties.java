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

package com.zealot.learn.dubbo.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @FileName HyStrixProperties.java
 * @Description: 
 *
 * @Date 2018年3月22日 下午4:17:00
 * @author TangYuping
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "fkc.hystrix")
public class HyStrixProperties {
	//CommandPropertiesDefault
	private int timeoutInMillions;  //超时时间 30000  默认1秒
	private int requestVolumeThreshold;  //默认 20 ， 超时时间内最少20次请求，熔断器才发挥起作用
	private int errorThresholdPercentage; // 20  出错率达到20%后熔断器启用
	private int sleepWindowInMilliseconds; //3000  熔断器中断请求3秒后会关闭重试,如果请求仍然失败,继续打开熔断器3秒
	
	//ThreadPoolPropertiesDefault
	private int coreSize; //50 线程池核心线程数  
	private int maxQueueSize; //150 线程池最大队列值 
	private int queueSizeRejectionThreshold; //200 实际队列大小 

	public int getTimeoutInMillions() {
		return timeoutInMillions;
	}

	public void setTimeoutInMillions(int timeoutInMillions) {
		this.timeoutInMillions = timeoutInMillions;
	}

	public int getRequestVolumeThreshold() {
		return requestVolumeThreshold;
	}

	public void setRequestVolumeThreshold(int requestVolumeThreshold) {
		this.requestVolumeThreshold = requestVolumeThreshold;
	}

	public int getErrorThresholdPercentage() {
		return errorThresholdPercentage;
	}

	public void setErrorThresholdPercentage(int errorThresholdPercentage) {
		this.errorThresholdPercentage = errorThresholdPercentage;
	}

	public int getSleepWindowInMilliseconds() {
		return sleepWindowInMilliseconds;
	}

	public void setSleepWindowInMilliseconds(int sleepWindowInMilliseconds) {
		this.sleepWindowInMilliseconds = sleepWindowInMilliseconds;
	}

	public int getCoreSize() {
		return coreSize;
	}

	public void setCoreSize(int coreSize) {
		this.coreSize = coreSize;
	}

	public int getMaxQueueSize() {
		return maxQueueSize;
	}

	public void setMaxQueueSize(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
	}

	public int getQueueSizeRejectionThreshold() {
		return queueSizeRejectionThreshold;
	}

	public void setQueueSizeRejectionThreshold(int queueSizeRejectionThreshold) {
		this.queueSizeRejectionThreshold = queueSizeRejectionThreshold;
	}

	@Override
	public String toString() {
		return "HyStrixProperties [timeoutInMillions=" + timeoutInMillions + ", requestVolumeThreshold=" + requestVolumeThreshold
				+ ", errorThresholdPercentage=" + errorThresholdPercentage + ", sleepWindowInMilliseconds=" + sleepWindowInMilliseconds
				+ ", coreSize=" + coreSize + ", maxQueueSize=" + maxQueueSize + ", queueSizeRejectionThreshold="
				+ queueSizeRejectionThreshold + "]";
	}
	
	
}
