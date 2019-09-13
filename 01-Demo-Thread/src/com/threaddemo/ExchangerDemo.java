package com.threaddemo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {

	public ExchangerDemo() {

	}

	public static void main(String[] args) {
		// 交换器，交换String数据类型数据
		Exchanger<String> ec = new Exchanger<String>();
		// 线程池
		ExecutorService threadpool = Executors.newCachedThreadPool();
		// 张三团伙
		threadpool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					String returnStr = ec.exchange("小乔");
					System.out.println("绑架者用小乔交换回：" + returnStr);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		// 大乔
		threadpool.execute(new Runnable() {

			@Override
			public void run() {
				try {

					//Exchange  同步工具  
					
					Thread.sleep((long) (Math.random()*1000));
					Thread.sleep((long) (Math.random()*1000));
					String returnStr = ec.exchange("100万");
					System.out.println("大乔用100万换回：" + returnStr);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		threadpool.shutdown();

	}

}
