package com.xb.netty.nio.channel;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @ClassName NIOByteBufferPutGet
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 11:15
 * @Version 1.0
 **/
public class NIOByteBufferPutGet {
  public static void main(String[] args) {
    final ByteBuffer byteBuffer = ByteBuffer.allocate(64);

    byteBuffer.putInt(100);
    byteBuffer.putLong(9);
    byteBuffer.putChar('啥');
    byteBuffer.putShort((short) 3);

    byteBuffer.flip();

    System.out.println(byteBuffer.getInt());
    System.out.println(byteBuffer.getLong());
    System.out.println(byteBuffer.getChar());
    System.out.println(byteBuffer.getShort());
  }
}
