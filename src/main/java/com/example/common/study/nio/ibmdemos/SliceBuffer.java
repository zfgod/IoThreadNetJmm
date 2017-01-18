package com.example.common.study.nio.ibmdemos;// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class SliceBuffer
{
  static public void main( String args[] ) throws Exception {
    ByteBuffer buffer = ByteBuffer.allocate( 10 );//0-9
//初始化缓冲区内容
    for (int i=0; i<buffer.capacity(); ++i) {
      buffer.put( (byte)i );
    }
// 从位置3开始,取到位置6
    buffer.position( 3 );
    buffer.limit( 7 );

    ByteBuffer slice = buffer.slice();
//对分片缓冲区内容重新赋值
    for (int i=0; i<slice.capacity(); ++i) {
      byte b = slice.get( i );
      b *= 11;
      slice.put( i, b );
    }
//重设位置,读取原缓冲区所有内容
    buffer.position( 0 );
    buffer.limit( buffer.capacity() );
//分片修改内容同样修改原缓冲区内容
    while (buffer.remaining()>0) {
      System.out.println( buffer.get() );
    }
  }
}
