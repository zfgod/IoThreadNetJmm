package com.example.common.study.nio;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
/**
 * author: zf
 * Date: 2017/1/19  16:11
 * Description:
 */
public class SocketClientTest {
	public static void main(String[] args) {
		new MiniClient("localhost", 1982);
	}
}
class MiniClient {
	private SocketChannel sc;
	private ByteBuffer w_bBuf;
	public MiniClient(String host, int port) {
		try {
			InetSocketAddress remote = new InetSocketAddress(host, port);
			sc = SocketChannel.open();
			sc.connect(remote);
			if(sc.finishConnect()) {
				System.out.println("已经与服务器成功建立连接...");
			}
			while(true) {
				if(!sc.isConnected()) {
					System.out.println("已经与服务器失去了连接...");
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("e:\\io\\boo.txt")));
				String str = br.readLine();
				System.out.println("读入一行数据，开始发送...");
				w_bBuf = ByteBuffer.wrap(str.getBytes());
				w_bBuf.flip();
				//向缓冲区中写入数据
				sc.write(w_bBuf);
				System.out.println("数据发送成功...");
				w_bBuf.clear();
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
