package com.ll.test.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 这是java 队列的测试(测试数据在test3.txt)
 * 读取108000(行)数据
 * @author Chris
 * add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
　　offer       添加一个元素并返回true       如果队列已满，则返回false
　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
　　peek       返回队列头部的元素             如果队列为空，则返回null
　　put         添加一个元素                      如果队列满，则阻塞
　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
 */
public class QueueTest {
	
   public static void main(String[] args) {
	   long  beginTime=System.currentTimeMillis();
	   LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();//定义一个队列
	   new Producer(linkedBlockingQueue).start();//启动生产者
	   new Consumer(linkedBlockingQueue).start();//起订消费者
	   System.err.println(System.currentTimeMillis()-beginTime);
}
}
