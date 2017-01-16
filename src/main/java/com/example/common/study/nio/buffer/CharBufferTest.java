package com.example.common.study.nio.buffer;

import java.nio.CharBuffer;

/**
 * author: zf
 * Date: 2017/1/16  15:42
 * Description:
 */
public class CharBufferTest
{
	public static void main(String[] args) {
		//分配新的字符缓冲区。 数组大小为10个字符,固定
//		CharBuffer charBuffer = CharBuffer.allocate(10);
//		String s = "测试5字符";
//		putStrToCharBuffer(charBuffer,s);

		otherMethodToShow();
	}

	private static void otherMethodToShow() {
		char[] chars = new char[24];//24个字符的数组
		CharBuffer charBuffer = CharBuffer.wrap(chars);//将字符数组包装到缓冲区中,缓冲区修改将导致数组修改，反之亦然
//对该charBuffer实例进行初始化
		String s = "测试5字符测试5字符测试5字符测试5字符测试5字符测试5字符测试5字符测试5字符";
		String s2 = "新内容在分片中获取";
		for (int i=0; i<charBuffer.capacity(); ++i) {
			charBuffer.put(s.charAt(i));
		}
		//修改buffer的position（起点）和limit（终点）
		charBuffer.position( 3 );
		charBuffer.limit( 7 );
//对缓冲区进行分片
		CharBuffer slice = charBuffer.slice();//分片内容从3到6,3是起始位置,6是给到limit=7,下标0为其实，所以下标6为莫位置
//对分片的数据进行操作
		for (int i=0; i<slice.capacity(); ++i) {
			slice.put( i, s2.charAt(i) );//重新赋值
		}
//重新定位并输出结果,反转或者指定位置
//		charBuffer.flip();//这里直接反转后,limit会是之前模式下最后的position位置,需要重新设定limit
		charBuffer.position( 0 );
		charBuffer.limit( charBuffer.capacity() );
		while (charBuffer.remaining()>0) {
			System.out.print(charBuffer.get() );
//			测试5 新内容在 5字符测试5字符测试5字符测试5字
		}

	}


	private static void putStrToCharBuffer(CharBuffer charBuffer,String s) {
		int limit1 = charBuffer.limit();
		System.out.println("构造完成的的limit"+limit1);

		System.out.println("最开始位置"+charBuffer.position());
//写入数据
		//put
		int length = s.length();
		charBuffer.put(s);
		System.out.println("写入数据后位置"+charBuffer.position());


//将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
		charBuffer.flip();
		int limit = charBuffer.limit();
		System.out.println("转换为读模式的limit"+limit);
		System.out.println("转换为读模式的最初位置"+charBuffer.position());

//读取数据
		char[] dst = new char[length];
		charBuffer.get(dst,0, length);
		System.out.println("读取数据后的位置"+charBuffer.position());
		for (char c : dst) {
			System.out.print(c);
		}
	}
}
