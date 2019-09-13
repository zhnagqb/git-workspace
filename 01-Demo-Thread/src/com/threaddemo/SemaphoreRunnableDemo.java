package com.threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 需求1：
 * 加入現在有20個人去售票厅买票，但是窗口只有2个，那么同时能够买票的只有
 * 2个人，当这2个人中任意一个买好票离开之后，等待的18个人中又会有一个人可以占用窗口买票
 * 
 * 拆解： 20个人就是20个线程，2个窗口就是资源
 * 
 * 实际含义是：怎么控制同一时间并发数是2
 * Semaphore 信号量（控制并发线程数）
 * @author Administrator
 *
 */
public class SemaphoreRunnableDemo {

	/**
	 * 	执行任务类  ，获取信号量和释放信号量
	 * @author Administrator
	 *
	 */
	class SemaphoreRunnable implements Runnable {

		private Semaphore semaphore;  // 信号量
		private int user;	//记录第几个用户	

		public SemaphoreRunnable(Semaphore semaphore, int user) {
			this.semaphore = semaphore;
			this.user = user;
		}

		@Override
		public void run() {
			try {
			// 获取信号量许可
				semaphore.acquire();
				System.out.println("用户【" + user + "】进入窗口，准备买票");
				Thread.sleep((long) (Math.random() * 1000));
				System.out.println("用户【" + user + "】进入窗口，准备离开");
				Thread.sleep((long) (Math.random() * 1000));
				System.out.println("用户【" + user + "】进入窗口，离开售票窗口");

				semaphore.release();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void execute() {
		//定义窗口个数
		final Semaphore semaphore = new Semaphore(2);
		//线程池
		ExecutorService threadpool = Executors.newCachedThreadPool();
		//模拟20个用户
		for(int i=0;i<20;i++){
			// 去买票
			threadpool.execute(new SemaphoreRunnable(semaphore,i));
		}
		threadpool.shutdown();
	}

	public static void main(String[] args) {
		SemaphoreRunnableDemo df =new SemaphoreRunnableDemo();
		df.execute();
		
		
	}
}
