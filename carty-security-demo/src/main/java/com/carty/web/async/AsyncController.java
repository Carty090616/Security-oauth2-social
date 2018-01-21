//package com.carty.web.async;
//
//import java.util.concurrent.Callable;
//
//import org.apache.commons.lang.RandomStringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.async.DeferredResult;
//
//@RestController
//public class AsyncController {
//
//	private Logger logger = LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//	private MockQueue mockQueue;
//	
//	@Autowired
//	private DeferredResultHolder deferredResultHolder;
//	
//	/**
//     * Callable
//     * @return
//     */
//	@RequestMapping("/callable")
//	public Callable<String> callable(){
//		logger.info("主线程开始");
//		Callable<String> result = new Callable<String>() {
//
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始");
//				Thread.sleep(10000);
//				logger.info("副线程返回");
//				return "success";
//			}
//		};
//		logger.info("主线程返回");
//		return result;
//	}
//	
//	/**
//     * DeferredResult
//     * @return
//     * @throws Exception 
//     */
//	@RequestMapping("/deferredResult")
//	public DeferredResult<String> deferredResult() throws Exception{
//		logger.info("主线程开始");
//
//		//随机生成订单号
//		String orderNumber = RandomStringUtils.randomNumeric(8);
//		//将订单号放入消息队列中
//		mockQueue.setPlaceOrder(orderNumber);
//		//new一个DeferredResult对象用于线程一与线程二之间通信
//		DeferredResult<String> result = new DeferredResult<>();
//		deferredResultHolder.getMap().put(orderNumber, result);
//		logger.info("主线程返回");
//		return result;
//	}
//
//
//}
