package com.example.common.study.thread.lock;

/**
 * author: zf
 * Date: 2017/1/13  10:56
 * Description:
 */
public class ReentrantReadWriteLockTest {

	public static void main(String[] args) {
		String key = "a";
		for(int i=0;i<100;i++){
			final int finalI = i;
			new Thread(){
				public void run(){
					SafeCacheDemo.put(key, finalI);
				}
			}.start();
		}
		for(int i=0;i<100;i++){
			new Thread(){
				public void run(){
					SafeCacheDemo.get(key);
				}
			}.start();
		}

	}


}
