package com.example.common.study.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * author: zf
 * Date: 2017/1/10  17:30
 * Description:io管道通信
 */
public class PipeDemo{

	public static void main(String[] args) throws IOException {
		/**
		 * 尝试使用try with resources写法，将管道输入输出资源放进try()括号内，
		 * 		然后关闭读写内部的finally资源关闭代码，使用自动关闭
		 * 但是出现java.io.IOException: Write end dead
		 * --操作管道时，需要保证读写管道的线程都不退出，否则，会出现write/read end dead的异常。
		 * == 这个怎么去使读写线程不退出呢。。
		 *    try（括号内资源），因为这里是两个资源，但是读写线程军不能关闭才可以使用通道资源，不适用
		 *    所以还是传统的try-catch, 在分别使用读写资源线程的finally里面关闭资源
		 * */
		try{
			final PipedOutputStream out = new PipedOutputStream();
			final PipedInputStream in = new PipedInputStream(out);
			Thread outThread = writeThread(out, "你好");
			Thread inThread = readThread(in);
			outThread.start();
			inThread.start();
//			try {
			   // 这里就算是join,等待向管道输入信息的线程结束才开始从管道读取,就肯定会出现java.io.IOException: Write end dead
			   // 所以join在管道的使用中也不适用
////			outThread.join();
////			inThread.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static Thread readThread(final PipedInputStream in) {
		return new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						int data = in.read();
						while (data!=-1){
							System.out.println(data);
							data = in.read();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					finally {
						if(in!=null){
							try {
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
	}

	private static Thread writeThread(final PipedOutputStream out,String s) {
		return new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						out.write(s.getBytes());
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					finally {
						if(out!=null){
							try {
								out.flush();
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
	}
}
