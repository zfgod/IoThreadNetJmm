package com.example.common.study.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * author: zf
 * Date: 2017/1/19  9:36
 * Description:
 */
public class CharsetTest {
	public static void main(String[] args) throws Exception {
		rwWithCharset();

	}

	private static void rwWithCharset() throws IOException {
		//get 两种方式, with code type or default
		defaultCharsetToUse();
		someOneCharsetToUse();
	}

	private static void someOneCharsetToUse() throws IOException {
		Charset charset = Charset.forName("ISO-8859-1");
		System.out.println( charset.name());//ISO-8859-1
		CharsetDecoder charsetDecoder1 = charset.newDecoder();//编码器
		CharsetEncoder charsetEncoder1 = charset.newEncoder();//解码器
		String s1 = "测试ISO-8859-1的charset处理过程2345。。";
		byte[] bytes1 = s1.getBytes();
		ByteBuffer byteBuffer1 = ByteBuffer.wrap(bytes1);
		//编码器编码
		CharBuffer decode1 = charsetDecoder1.decode(byteBuffer1);
		//解码器解码
		ByteBuffer encode1 = charsetEncoder1.encode(decode1);
		RandomAccessFile file1 = new RandomAccessFile(new File("e:\\io\\charset2.txt"),"rw");
		FileChannel channel1 = file1.getChannel();
		channel1.write(encode1);
	}

	private static void defaultCharsetToUse() throws IOException {
		Charset defaultCharset = Charset.defaultCharset();// utf-8

		System.out.println( defaultCharset.name());//UTF-8
		//use
		CharsetDecoder charsetDecoder = defaultCharset.newDecoder();//编码器
		CharsetEncoder charsetEncoder = defaultCharset.newEncoder();//解码器

		String s = "测试utf-8的charset处理过程。。";
		byte[] bytes = s.getBytes();
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		//编码器编码
		CharBuffer decode = charsetDecoder.decode(byteBuffer);
		//解码器解码
		ByteBuffer encode = charsetEncoder.encode(decode);
		RandomAccessFile file = new RandomAccessFile(new File("e:\\io\\charset.txt"),"rw");
		FileChannel channel = file.getChannel();
		channel.write(encode);
	}
}
