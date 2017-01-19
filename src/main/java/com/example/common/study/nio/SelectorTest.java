package com.example.common.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * author: zf
 * Date: 2017/1/17  18:17
 * Description:
 */
public class SelectorTest {
	public static void main(String[] args) throws IOException {
//创建selector
		Selector selector = Selector.open();
//打开一个远程连接
		InetSocketAddress socketAddress =
				new InetSocketAddress("www.baidu.com", 80);
		SocketChannel sc = SocketChannel.open(socketAddress);
		sc.configureBlocking(false);//设置为非阻塞
//选择键，注册
		SelectionKey key = sc.register(selector, SelectionKey.OP_CONNECT);
		//注册时第一个参数总是当前的这个selector。
		//注册事件  OP_CONNECT，OP_READ，OP_WRITE，OP_ACCEPT
		int num = selector.select();
		Set selectedKeys = selector.selectedKeys();
		Iterator it = selectedKeys.iterator();
		while (it.hasNext()) {
			SelectionKey selectionKey= (SelectionKey)it.next();
			if ((selectionKey.readyOps() & SelectionKey.OP_CONNECT)
					== SelectionKey.OP_CONNECT){
				// Accept the new connection
				ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
				SocketChannel accept = ssc.accept();
				accept.configureBlocking( false );
				// Add the new connection to the selector
				SelectionKey newKey = sc.register( selector, SelectionKey.OP_CONNECT);
				it.remove();
				System.out.println( "Got connection from "+sc );
			}
// ... deal with I/O event ...
		}



	}
}
