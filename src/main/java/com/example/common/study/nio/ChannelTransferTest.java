package com.example.common.study.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author: zf
 * Date: 2017/1/17  14:53
 * Description:
 */
public class ChannelTransferTest {
	public static void main(String[] args) throws Exception {

//源通道及源文文件
		RandomAccessFile fromFile = new RandomAccessFile("E:\\io\\fromFile.txt","rw");
		FileChannel  channel = fromFile.getChannel();
//		中间操作源通道会影响写入其他通道的数据,。。。。
//目标通道及目标文件
		RandomAccessFile toFile = new RandomAccessFile("E:\\io\\toFile.txt", "rw");
		FileChannel  toChannel = toFile.getChannel();
//目标通道从源通道获取数据并写入文件
		long size = channel.size();
		toChannel.transferFrom(channel,0,size);
	}
}
