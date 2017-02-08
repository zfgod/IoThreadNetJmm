package com.example.common.study.thread.demos;

/**
 * author: zf
 * Date: 2017/2/8  16:47
 * Description:
 */
public class Single {

	private Single(){}

	private static volatile  Single instance;

	public static Single getInstance(){
		if(null == instance){
			synchronized (Single.class)
			{
				if(null == instance){
					instance = new Single();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		for(int i= 0; i<=100;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					Single instance = getInstance();
					System.out.println(instance.hashCode());
				}
			}).start();
		}
	}
}
