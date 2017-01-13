package com.example.common.study.io;


import java.io.*;

/**
 * author: zf
 * Date: 2017/1/11  14:54
 * Description:
 */
public class InputStreamReadTest {
	public static void main(String[] args){
		//当执行线程退出try语句块的时候，InputStream变量会被关闭。
		try(InputStream inputStream = new FileInputStream(new File("E:\\sql.txt"));
			InputStreamReader  inputStreamReader  = new InputStreamReader(inputStream,"UTF-8");
			BufferedReader br = new BufferedReader(inputStreamReader)
		){
			//inputStream读取
//			readWithOneByte(inputStream);
//			readWithBytesArray(inputStream);
			//inputStreamReader ,br读取,需注释上面的inputStream读取
			String s ;
			s = br.readLine();
			while(s!=null){
				System.out.println(s);
				s = br.readLine();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void readWithOneByte(InputStream inputStream) throws IOException {
		int data = inputStream.read();//0-255
		while(data!=-1){
		//将返回的字节数据转换为char类型
			System.out.print((char) data);
			data = inputStream.read();
		}
	}

	private static void readWithBytesArray(InputStream inputStream) throws IOException {
		byte[] data = new byte[1024];
		int read = inputStream.read(data);//一次性读取字节数组更快速
		while(read!=-1){
			for (byte b : data) {
				//将返回的字节数据转换为char类型
				System.out.print((char)b);
			}
			read = inputStream.read(data);
		}
	}

}
