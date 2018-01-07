package com.carty.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 用于在线程1与线程2之间传递DeferredResult对象
 * @author Administrator
 *
 */
@Component
public class DeferredResultHolder {

	/**
     * map的key表示订单号，value表示处理结果
     */
	private Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}

}
