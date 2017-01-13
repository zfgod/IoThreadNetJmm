package com.example.common.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: zf
 * Date: 2016/12/28  18:03
 * Description:
 */
public class VolatileTest {

		public volatile static int count = 0;
	    private static AtomicInteger atomicCont = new AtomicInteger(0);

		public static void inc() {
//			//这里延迟1毫秒，使得结果明显
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

			}
			//非原子性操作, 使用volatile修饰的变量 jmm只保证变量的单个读/写 操作
			count++;
		}
/**
 * 使用CAS实现线程安全技术
 * */
		public  static void safeInc() {
			for(;;){
				int i = atomicCont.get();
				boolean b = atomicCont.compareAndSet(i, ++i);
				if(b){
					break;
				}
			}

		}

	public static void main(String[] args) {
		final  VolatileTest test = new VolatileTest();
		//同时启动1000个线程，去进行i++计算，看看实际结果
		List<Thread> threads = new ArrayList<>(250);
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++){
						VolatileTest.inc();
						VolatileTest.safeInc();
					}
				}
			});
			threads.add(thread);
		}
		//运行所有线程
		for (Thread thread : threads) {
			thread.start();
		}
        //等待所有线程执行完成
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//这里每次运行的值都有可能不同,可能为1000
		System.out.println("非原子性操作运行结果:" + test.count);
		System.out.println("原子性操作运行结果:" + test.atomicCont);
		/**
		 * 即使变量被volatile,JMM也只能做到单原子操作的一致性, ++ 非原子性操作在并发时无法保证
		 */
//		非原子性操作运行结果:992459   （值变化）
//		原子性操作运行结果:1000000
	}
}

