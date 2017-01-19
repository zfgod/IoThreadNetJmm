package com.example.common.study.nio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.Iterator;
/**
 * author: zf
 * Date: 2017/1/19  16:11
 * Description: http://blog.csdn.net/tsyj810883979/article/details/6877216
 */
public class SocketServerTest {
	public static void main(String[] args) {
		new Thread(new EchoServer(1982)).start();
	}
}

class EchoServer implements Runnable {
	//要监听的端口号
	private int port;
	//生成一个信号监视器
	private Selector s;
	//读缓冲区
	private ByteBuffer r_bBuf = ByteBuffer.allocate(1024);

	public EchoServer(int port) {
		this.port = port;
		try {
			s = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			//生成一个ServerScoket通道的实例对象，用于侦听可能发生的IO事件
			ServerSocketChannel ssc = ServerSocketChannel.open();
			//将该通道设置为异步方式
			ssc.configureBlocking(false);
			//绑定到一个指定的端口
			ssc.socket().bind(new InetSocketAddress(port));
			//注册特定类型的事件到信号监视器上
			ssc.register(s, SelectionKey.OP_ACCEPT);
			System.out.println("The server has been launched...");
			while(true) {
				//将会阻塞执行，直到有事件发生
				System.out.println("监听新事件...");
				s.select();
				Iterator<SelectionKey> it = s.selectedKeys().iterator();
				while(it.hasNext()) {
					SelectionKey key = it.next();
					//key定义了四种不同形式的操作
					switch(key.readyOps()) {
						case SelectionKey.OP_ACCEPT :
							dealwithAccept(key);
							break;
						case SelectionKey.OP_CONNECT :
							break;
						case SelectionKey.OP_READ :
							dealwithRead(key);
							break;
						case SelectionKey.OP_WRITE :
							break;
					}
					//处理结束后移除当前事件，以免重复处理
					it.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//处理接收连接的事件
	private void dealwithAccept(SelectionKey key) {
		try {
			System.out.println("deal with new accept...");
			ServerSocketChannel server = (ServerSocketChannel)key.channel();
			SocketChannel sc = server.accept();
			sc.configureBlocking(false);
			//注册读事件
			sc.register(s, SelectionKey.OP_WRITE);
			System.out.println("deal with new accept2...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//处理客户端发来的消息，处理读事件
	private void dealwithRead(SelectionKey key) {
		try {
			SocketChannel sc = (SocketChannel)key.channel();
			System.out.println("读入数据");
			r_bBuf.clear();
			sc.write(r_bBuf);
			r_bBuf.flip();
			System.out.println(r_bBuf.asCharBuffer().toString());
			r_bBuf.clear();
			System.out.println("处理完毕...");
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getCurrentTime() {
		return Calendar.getInstance().toString();
	}
}
