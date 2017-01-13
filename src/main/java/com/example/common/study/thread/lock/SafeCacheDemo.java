package com.example.common.study.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;
/**
 * author: zf
 * Date: 2017/1/13  11:07
 * Description:
 */
public class SafeCacheDemo {
	static Map<String, Object> map = new HashMap<String, Object>();//缓存map对象
	static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();//可重入读写锁
	static Lock read = rwl.readLock();
    static Lock write = rwl.writeLock();
	public SafeCacheDemo() {
	}
	//读取key对应的value
	public static final Object get(String key) {
		read.lock();
		try {
			Object o = map.get(key);
			System.out.println("d读取："+key+"==="+o);
			return o;
		} finally {
			read.unlock();
		}
	}

	//设置key对应的value,返回旧的value值
	public static final Object put(String key, Object value) {
		write.lock();
		try {
			Object put = map.put(key, value);
			System.out.println("写入："+key+"："+value);
			return put;
		} finally {
			write.unlock();
		}
	}

	//清空map中所有内容
	public static final void clearAll() {
		write.lock();
		try {
			map.clear();
		} finally {
			write.unlock();
		}
	}

}
