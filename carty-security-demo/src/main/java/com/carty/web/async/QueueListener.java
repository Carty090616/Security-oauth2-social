//package com.carty.web.async;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
///**
// * 用于监听消息队列
// * 
// * ContextRefreshedEvent,此事件表示整个spring容器初始化完毕的事件
// * @author Administrator
// *
// */
//@Component
//public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{
//
//	private Logger log = LoggerFactory.getLogger(QueueListener.class);
//
//	@Autowired
//	private MockQueue mockQueue;
//
//	@Autowired
//	private DeferredResultHolder deferredResultHolder;
//
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent arg0) {
//		new Thread(() -> {
//			while(true) {
//				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
//					String orderNumber = mockQueue.getCompleteOrder();
//					log.info("返回订单处理结果："+orderNumber);
//					
//					//根据订单号获取DeferredResult对象，并向其中添加值，此值会最终返回给前端
//					deferredResultHolder.getMap().get(orderNumber).setResult("place oeder success");
//
//					//由于是模拟订单队列,所以需要将CompleteOrder置为空，放置一直监听器循环
//					mockQueue.setCompleteOrder(null);
//				} else {
////					log.info("监听器沉睡");
//					try {
//						Thread.sleep(2000);
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//				}
//			}
//		}).start();
//	}
//
//}
