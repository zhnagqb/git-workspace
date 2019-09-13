package com.threaddemo;

import java.util.concurrent.CountDownLatch;
//倒计时计数器，其他2个线程执行完之后 它才开始执行
public class CountDownLatchDemo {

	public static void main(String[] args) {
		// 等待2个任务执行完
		final CountDownLatch latch = new CountDownLatch(2);
		// 任务1
		// 内部类里进行执行任务
		new Thread() {
			public void run() {
				try {
					System.out.println("任务1正在执行任务。。");
					Thread.sleep((long) (Math.random() * 1000));
					System.out.println("任务1正在执行完毕。。");

					latch.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};

		}.start();
		new Thread() {
			public void run() {
				try {
					System.out.println("任务2正在执行任务。。");
					Thread.sleep((long) (Math.random() * 1000));
					System.out.println("任务2正在执行完毕。。");

					latch.countDown(); // 计数器减1
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};

		}.start();
		// 主线程
		System.out.println("等待其他2个任务执行完毕，主线程才开始执行任务" + Thread.currentThread().getName());

		try {
			latch.await();
			System.out.println("其他2个任务执行完毕，主线程才开始执行任务" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
