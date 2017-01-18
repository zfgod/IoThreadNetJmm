package com.example.common.study.nio.ibmdemos;// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class UseCharsets
{
  static public void main( String args[] ) throws Exception {
    String inputFile = "e:\\io\\aa.jpg";
    String outputFile = "e:\\io\\bb.jpg";

    RandomAccessFile inf = new RandomAccessFile( inputFile, "rw" );
    RandomAccessFile outf = new RandomAccessFile( outputFile, "rw" );
    File file = new File(inputFile);
    long inputLength = file.length();

    FileChannel inc = inf.getChannel();
    FileChannel outc = outf.getChannel();

    MappedByteBuffer inputData =
      inc.map( FileChannel.MapMode.READ_ONLY, 0, inputLength );

    Charset latin1 = Charset.forName( "ISO-8859-1" );
    CharsetDecoder decoder = latin1.newDecoder();
    CharsetEncoder encoder = latin1.newEncoder();

    CharBuffer cb = decoder.decode( inputData );

    // Process char data here

    ByteBuffer outputData = encoder.encode( cb );

    outc.write( outputData );

    inf.close();
    outf.close();
  }
}
