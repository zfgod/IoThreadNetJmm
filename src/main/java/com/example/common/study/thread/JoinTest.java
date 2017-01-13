package com.example.common.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * author: zf
 * Date: 2017/1/9  17:05
 * Description:
 */
public class JoinTest {
	public static void main(String[] args) throws InterruptedException {
		Thread pre = Thread.currentThread();
		for (int i =0;i <=9 ;i++){
			//每个线程拥有前一个线程的引用,需要等待前一个线程终止，才能从等待中返回
			Thread thread = new Thread(new Domino(pre),String.valueOf(i));
			thread.start();
			pre = thread;
		}
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName()+" TERMINATE.");
	}
	static class Domino implements Runnable{
		private Thread thread;

		Domino(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void run() {
			/*  */
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" TERMINATE.");
		}
	}
}
