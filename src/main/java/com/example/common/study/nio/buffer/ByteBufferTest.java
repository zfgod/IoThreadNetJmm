package com.example.common.study.nio.buffer;

import java.nio.ByteBuffer;

/**
 * author: zf
 * Date: 2017/1/16  14:38
 * Description:
 * java.nio.Buffer直接已知子类：
 	ByteBuffer, CharBuffer, DoubleBuffer, FloatBuffer, IntBuffer, LongBuffer, ShortBuffer
 */
public class ByteBufferTest {
	public static void main(String[] args) {
/*
 * 构造一个byteBuffer
 * 	字节缓冲区可以通过 allocation 方法创建，此方法为该缓冲区的内容分配空间，
 * 	或通过 wrapping 方法将现有的 byte 数组包装到缓冲区中来创建。原数组和缓冲区对象两者互相影响
 */
// allocate(int capacity) 分配一个新的字节缓冲区。（非直接缓冲区）
//		新缓冲区的位置将为零，其界限将为其容量，其标记是不确定的。它将具有一个底层实现数组，且其 数组偏移量将为零。
//		capacity: 单位字节
		ByteBuffer byteBuffer = ByteBuffer.allocate(64);//维护一个64字节的数组（0-63）内存空间
//		在索引0的位置将 4 个包含给定 int 值的字节按照当前的字节顺序写入到此缓冲区的当前位置，然后将该位置增加 4
		byteBuffer.putInt(0,3);//0,1,2,3 这4个字节
//		在当前操作的索引位置放入char类型的值 'c'：将两个包含给定 char 值的字节按照当前的字节顺序写入到此缓冲区的给定索引处。
		byteBuffer.putChar(4,'c');
//      从索引0位置开始顺序读取4个字节内容,组成返回int值
		int b = byteBuffer.getInt(0);
//      从索引4位置开始顺序读取2个字节内容,组成返回char值
		char c = byteBuffer.getChar(4);
		System.out.println(b);
		System.out.println(c);
	}
}
