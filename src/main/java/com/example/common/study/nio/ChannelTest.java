package com.example.common.study.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author: zf
 * Date: 2017/1/16  11:44
 * Description:
 * 既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
	 通道可以异步地读写。
 	通道中的数据总是要先读到一个Buffer缓冲区，或者总是要从一个Buffer缓冲区中写入。

 */
public class ChannelTest {
	public static void main(String[] args) {
		RandomAccessFile aFile = null;
		FileChannel inChannel = null;
		try {
			aFile = new RandomAccessFile("E:\\io\\boo.txt", "rw");
			inChannel = aFile.getChannel();
//			capacity = 48
			ByteBuffer buf = ByteBuffer.allocate(48);
			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {
				System.out.println("Read " + (char)bytesRead);
				buf.flip();
				while(buf.hasRemaining()){
					System.out.print((char) buf.get());
				}
				buf.clear();
				bytesRead = inChannel.read(buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(aFile!=null){
				try {
					aFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
