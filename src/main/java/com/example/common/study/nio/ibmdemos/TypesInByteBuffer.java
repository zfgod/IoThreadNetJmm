package com.example.common.study.nio.ibmdemos;// $Id$

import java.nio.*;

public class TypesInByteBuffer
{
  static public void main( String args[] ) throws Exception {
    ByteBuffer buffer = ByteBuffer.allocate( 64 );

    buffer.putInt( 30 );//4个字节
    System.out.println("当前位置："+buffer.position());
    buffer.putLong(7000000000000L);//8个字节
    System.out.println("当前位置："+buffer.position());
    buffer.putDouble( Math.PI );//8个字节
    System.out.println("当前位置："+buffer.position());
    buffer.flip();//反转 由写模式换到读模式,limit = lastPosition = 4+8+8 ,position回到0,
    System.out.println("当前位置："+buffer.position());
    System.out.println( buffer.getInt() );
    System.out.println("当前位置："+buffer.position());
    System.out.println( buffer.getLong() );
    System.out.println("当前位置："+buffer.position());
    System.out.println( buffer.getDouble() );
    System.out.println("当前位置："+buffer.position());
  }
}
