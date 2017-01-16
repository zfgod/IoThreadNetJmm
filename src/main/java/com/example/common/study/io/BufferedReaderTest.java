package com.example.common.study.io;


import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * author: zf
 * Date: 2017/1/16  9:52
 * Description:
 */
public class BufferedReaderTest {
	public static void main(String[] args) {
		String path = "E:\\io\\b.txt";
		String destination ="E:\\io\\boo.txt";
//		readFileWithBufferedReader(path);
		writeTxtToFileWithBufferedWriter(path,destination);
	}

	private static void writeTxtToFileWithBufferedWriter(String path, String destination) {
		try(
			BufferedReader reader =
					new BufferedReader( new FileReader(new File(path)),1024*8);

//			BufferedWriter writer =
//					new BufferedWriter( new FileWriter(new File(destination)),1024*8)
//			//追加文件内容
			BufferedWriter writer =
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destination,true)))
				){
			String s =  "";
			while(StringUtils.isNotBlank(s = reader.readLine())){
//				writer.newLine();//追加文件末尾或者换行
				writer.write(s,0,s.length());
				writer.newLine();
				writer.flush();
			}
			writer.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void readFileWithBufferedReader(String path) {
		try(
				BufferedReader reader =  new BufferedReader( new FileReader(new File(path)),1024*8)
				){
			int j = 0;
			String s1 = reader.readLine();
			while (StringUtils.isNotBlank(s1)){
				System.out.println(s1);
				s1 = reader.readLine();
				j++;
			}
			/* */
//			char[] chars = new char[1024];
//			int i;
//			while ((i =  reader.read(chars))!=-1){
//			    j++;
//				for (char aChar : chars) {
//					System.out.print(aChar);
//				}
//			}
			System.out.println("add--"+j);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
