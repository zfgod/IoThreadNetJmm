package com.example.common.study.thread.hashMapTest;

import java.util.HashMap;

/**
 * author: zf
 * Date: 2017/1/20  11:52
 * Description:
 */
public class HashMapTest {
	public static void main(String[] args) {
		HashMap map = new HashMap();//default: 初始容量initialCapacity = 16,装载因子LOAD_FACTOR = 0.75
		//12条数目的键值存储
		for(int i= 0;i<12;i++){
			map.put(i,i);
		}
		int size = map.size();
		System.out.println(size);
		System.out.println(map.toString());
		//
//		for(int i= 0;i<16;i++){
//			map.put(i,i);
//		}
	}
}
