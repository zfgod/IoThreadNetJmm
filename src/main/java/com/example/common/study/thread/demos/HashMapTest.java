package com.example.common.study.thread.demos;

import java.util.HashMap;

/**
 * author: zf
 * Date: 2017/1/20  11:52
 * Description:
 */
public class HashMapTest {
	private static HashMap<Integer, Integer> map = new HashMap<>(2,0.75f);
	public static void main(String[] args) {
		map.put(5, 55);

		new Thread("Thread1") {
			public void run() {
				map.put(7, 77);
				System.out.println(map);
			};
		}.start();
		new Thread("Thread2") {
			public void run() {
				map.put(3, 33);
				System.out.println(map);
			};
		}.start();

	}
}
