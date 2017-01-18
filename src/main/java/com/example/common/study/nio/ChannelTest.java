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
		readToBufferFromChannel();
		writeToChannelFromBuffer();
	}

	private static void writeToChannelFromBuffer() {
		//初始化一个int数组
		byte[] bytes = new byte[24];
		int length = bytes.length;
		String s = "fadsefadefaefafadsefadefaefafadsefadefaefafadsefadefaefa";
		for(int i =0; i<length ; i++){
			bytes[i] = (byte) s.charAt(i);
		}
		//把int数组装饰为一个int缓冲区对象
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		RandomAccessFile file = null;
		FileChannel channel = null ;
		try {
			file = new RandomAccessFile("E:\\io\\bo1.txt","rw");
			channel = file.getChannel();
			//从缓冲区读到通道中，并写入文件
			int write = channel.write(byteBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(file!=null){
					file.close();
				}
				if(channel!=null){
					channel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

	private static void readToBufferFromChannel() {
		RandomAccessFile aFile = null;
		FileChannel inChannel = null;
		try {
//文件内容粗存入channel中
			aFile = new RandomAccessFile("E:\\io\\boo.txt", "rw");
			inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(48);//构造48字节数组的缓冲区对象
//从channel中读内容存入缓冲区对象
			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {
				System.out.println("Read返回值： " + bytesRead);
				buf.flip();//反转缓冲去对象,切换为读取模式
				System.out.println("此次从通道读入缓冲区大小："+buf.limit());
				while(buf.hasRemaining()){
					System.out.print((char) buf.get());//一个个字节读取并转换为字符内容,对于中文的转换不支持
				}
				buf.clear();//清空缓冲区对象内每个字节的标记,position=0,limit=48,内容尚在,告知可以从0位置再次写入
				bytesRead = inChannel.read(buf);//继续读取
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(aFile!=null){
					aFile.close();
				}
				if(inChannel!=null){
					inChannel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
