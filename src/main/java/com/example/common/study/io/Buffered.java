package com.example.common.study.io;

import java.io.*;

/**
 * author: zf
 * Date: 2017/1/11  19:00
 * Description:
 */
public class Buffered {
	public static void main(String[] args) {
//		String path = "E:\\io\\big.sql";
		String path = "E:\\io\\b.txt";
//		useBufferedRead(path);
//        String destination = "E:\\io\\big2.sql";
        String destination = "E:\\io\\b2.txt";
		useBufferedWrite(path,destination);
	}

	private static void useBufferedWrite(String path, String destination) {
		try(
				InputStream inputStream = new BufferedInputStream(new FileInputStream(path),8*1024);
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destination),8*1024)
				){
			long l1 = System.currentTimeMillis();
			byte[] bytes = new byte[8*1024];
			int read;
			while((read = inputStream.read(bytes)) != -1){
//				outputStream.write(bytes);//这么写，文件末尾时会写入空内容
				outputStream.write(bytes,0,read);
			}
			outputStream.flush();
			long l2= System.currentTimeMillis();
			System.out.println(l2-l1);
			System.out.println((l2-l1)/1000);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void useBufferedRead(String path) {
		try(InputStream inputStream = new BufferedInputStream(new FileInputStream(path),8*1024)){
			long l1 = System.currentTimeMillis();
			byte[] bytes = new byte[24*1024];
			int available = inputStream.available();
			System.out.println("总共："+available);
			int read;
			while((read = inputStream.read(bytes)) != -1){
				available = inputStream.available();
				System.out.println("剩："+available);
			}
			long l2= System.currentTimeMillis();
			System.out.println(l2-l1);
			System.out.println((l2-l1)/1000);
		}catch (Exception e){
			e.printStackTrace();
		}
	}


}
