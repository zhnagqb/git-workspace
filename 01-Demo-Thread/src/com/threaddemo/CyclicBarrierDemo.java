package com.threaddemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		// 3个人聚餐
		final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {

			@Override
			public void run() {

				System.out.println("人员全部到齐了，各自拍照留恋。。。");
				try {
					Thread.sleep((long) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// 线程池
		ExecutorService threadpool = Executors.newCachedThreadPool();
		// 模拟3个用户
		for (int i = 0; i < 3; i++) {
			final int user = i + 1;
			Runnable r = new Runnable() {

				@Override
				public void run() {
					// 模拟每个人来的时间不一样
					try {
						Thread.sleep((long) (Math.random() * 1000));
						System.out.println(user + "到达聚餐点，当前已有" + cb.getNumberWaiting());
						// 阻塞
						cb.await();
						if (user == 1) {
							System.out.println("拍照结束，开始吃饭。。。");
						}
						Thread.sleep((long) (Math.random() * 1000));

						System.out.println(user + "吃完饭了，准备回家。。。。。");

					} catch (InterruptedException | BrokenBarrierException e ) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				 
					}

				}
			};
			threadpool.execute(r);
		}
		threadpool.shutdown();

	}

}
