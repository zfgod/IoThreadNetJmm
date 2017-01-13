package com.example.common.study.io;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * author: zf
 * Date: 2017/1/11  16:14
 * Description: 拥有文件自用权限的流
 * 	允许你来回读写文件，也可以替换文件中的某些部分。FileInputStream和FileOutputStream没有这样的功能。
 */
public class RandomAccessFileTest {
	public static void main(String[] args) {
		try(
				//第二个参数：操作文件的模式
				RandomAccessFile file = new RandomAccessFile(new File("e:\\sql.txt"), "rw")
		){
			file.seek(0);//把文件指针指向该位置、从该位置开始读取或者写入
			file.write("aa".getBytes());//默认从文件指针位置开始追加内容,如果该文件此位置有内容，将被覆盖
			long pointer = file.getFilePointer();//当前指针位置
			System.out.println(pointer);
			byte[] bytes = new byte[1024];
			file.seek(0);
			int read = file.read(bytes);//从指针所处位置开始读取,如果需要文件开始处读取,需重新指定指针位置
			while (read!=-1){
				for (byte aByte : bytes) {
					System.out.print((char)aByte);
				}
				read = file.read(bytes);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
