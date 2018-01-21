//package com.carty.web.async;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * 用于模拟消息队列
// * @author Administrator
// *
// */
//@Component
//public class MockQueue {
//
//	private Logger log = LoggerFactory.getLogger(MockQueue.class);
//	
//	//代表下单的消息
//	private String placeOrder;
//	//代表订单完成的消息
//	private String completeOrder;
//
//	public String getPlaceOrder() {
//		return placeOrder;
//	}
//
//	public void setPlaceOrder(String placeOrder) throws Exception {
//		new Thread(() -> {
//			log.info("接到下单请求："+placeOrder);
//			try {
//				Thread.sleep(10000);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			this.completeOrder = placeOrder;
//			log.info("下单请求处理完毕："+placeOrder);
//		}).start();
//	}
//
//	public String getCompleteOrder() {
//		return completeOrder;
//	}
//
//	public void setCompleteOrder(String completeOrder) {
//		this.completeOrder = completeOrder;
//	}
//
//}
