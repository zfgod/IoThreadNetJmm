package com.example.common.study.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * author: zf
 * Date: 2017/1/11  15:27
 * Description:
 */
public class OutputStreamWriteTest {
	public static void main(String[] args) {
		//构造函数第二个参数:追加用append == true,默认false == overwrite
		try(OutputStream outputStream = new FileOutputStream("E:\\sql.txt",true)){
			String s = "嗨 ——hello!";
			byte[] bytes = s.getBytes();
			writeWithBytes(bytes, outputStream);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void writeWithBytes(byte[] bytes,OutputStream outputStream) throws IOException {
		outputStream.write(bytes);
		outputStream.flush();//所有写入到FileOutputStream的数据全部写入到目标媒介中。
	}

}
