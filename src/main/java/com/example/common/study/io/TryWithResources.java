package com.example.common.study.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * author: zf
 * Date: 2017/1/11  14:28
 * Description: java exception handle:Try-with-resources in Java 7
 * 				自动关闭资源
 */
public class TryWithResources {
	public static void main(String[] args){
		test();
	}

	private static void test(){
//		当执行线程退出try语句块的时候，InputStream变量会被关闭。
		try(InputStream inputStream = new FileInputStream(new File("E:\\sql.txt"))){
			int data = inputStream.read();//0-255
			while(data!=-1){
//				将返回的字节数据转换为char类型
				System.out.print((char) data);
				data = inputStream.read();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
