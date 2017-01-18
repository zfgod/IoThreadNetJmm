package com.example.common.study.nio.ibmdemos;// $Id$

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class MultiPortEcho
{
  private int ports[];
  private ByteBuffer echoBuffer = ByteBuffer.allocate( 1024 );


  static public void main( String args[] ) throws Exception {
   /* if (args.length<=0) {
      System.err.println( "Usage: java MultiPortEcho port [port port ...]" );
      System.exit( 1 );
    }

    int ports[] = new int[args.length];*/
    int ports[] = new int[3];
    for (int i=0; i<3; ++i) {
//      ports[i] = Integer.parseInt( args[i] );
      ports[i] = i+80;
    }

    new MultiPortEcho( ports );
  }
  public MultiPortEcho( int ports[] ) throws IOException {
    this.ports = ports;

    go();
  }

  private void go() throws IOException {
    // Create a new selector
    Selector selector = Selector.open();

    // Open a listener on each port, and register each one
    // with the selector
    for (int i=0; i<ports.length; ++i) {
      ServerSocketChannel ssc = ServerSocketChannel.open();
      ssc.configureBlocking( false );//必须设置通道非阻塞
      ServerSocket ss = ssc.socket();
      InetSocketAddress address = new InetSocketAddress( ports[i] );
      ss.bind( address );
      //在selector上注册channel,并配置所要关注（监听）的事件（可多个）
      //ServerSocketChannel  唯一事件类型：关注创建链接事件
      SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT );
      System.out.println( "Going to listen on "+ports[i] );
    }
    while (true) {
      int num = selector.select();//阻塞状态,
      // 其他线程操作此selector的wakeup()会使一个阻塞中的select()直接返回

      Set selectedKeys = selector.selectedKeys();
      Iterator it = selectedKeys.iterator();

      while (it.hasNext()) {
        SelectionKey key = (SelectionKey)it.next();

        if ((key.readyOps() & SelectionKey.OP_ACCEPT)
          == SelectionKey.OP_ACCEPT) {
          // Accept the new connection
          ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
          SocketChannel sc = ssc.accept();
          sc.configureBlocking( false );

          // Add the new connection to the selector
          SelectionKey newKey = sc.register( selector, SelectionKey.OP_READ );
          it.remove();

          System.out.println( "Got connection from "+sc );
        } else if ((key.readyOps() & SelectionKey.OP_READ)
          == SelectionKey.OP_READ) {
          // Read the data
          SocketChannel sc = (SocketChannel)key.channel();

          // Echo data
          int bytesEchoed = 0;
          while (true) {
            echoBuffer.clear();

            int r = sc.read( echoBuffer );

            if (r<=0) {
              break;
            }

            echoBuffer.flip();

            sc.write( echoBuffer );
            bytesEchoed += r;
          }

          System.out.println( "Echoed "+bytesEchoed+" from "+sc );

          it.remove();
        }

      }

//System.out.println( "going to clear" );
//      selectedKeys.clear();
//System.out.println( "cleared" );
    }
  }


}
