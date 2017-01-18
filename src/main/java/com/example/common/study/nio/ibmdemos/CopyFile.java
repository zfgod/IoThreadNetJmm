package com.example.common.study.nio.ibmdemos;// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class CopyFile
{
  static public void main( String args[] ) throws Exception {
    /*if (args.length<2) {
      System.err.println( "Usage: java CopyFile infile outfile" );
      System.exit( 1 );
    }

    String infile = args[0];
    String outfile = args[1];*/

    String infile = "E:\\io\\fromFile.txt";
    String outfile = "E:\\io\\toFile.txt";

    FileInputStream fin = new FileInputStream( infile );
    FileOutputStream fout = new FileOutputStream( outfile );

    FileChannel fcin = fin.getChannel();
    FileChannel fcout = fout.getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(16);
//    ByteBuffer buffer = ByteBuffer.allocate(1024);
    int i = 0 ;
    int j = 0 ;
    while (true) {
      buffer.clear();
      int r = fcin.read( buffer );
      if (r==-1) {
        break;
      }
      i ++ ;
      j += r;
      buffer.flip();//缓冲区对象反转,由读到写
      fcout.write( buffer );
    }
    System.out.println("读取次数："+i);
    System.out.println("读取字节总数："+j);
  }
}
