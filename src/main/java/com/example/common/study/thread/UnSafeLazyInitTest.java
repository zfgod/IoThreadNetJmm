package com.example.common.study.thread;


/**
 * author: zf
 * Date: 2016/12/29  14:34
 * Description:
 */
public class UnSafeLazyInitTest {
	private static UnSafeLazyInitTest instance;

	public static UnSafeLazyInitTest  getInstance(){

		if(instance == null){
			instance = new UnSafeLazyInitTest();
		}
		return instance;
	}
}
