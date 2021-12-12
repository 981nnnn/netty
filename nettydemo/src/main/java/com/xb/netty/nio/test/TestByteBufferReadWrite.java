package com.xb.netty.nio.test;

import com.xb.netty.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @ClassName TestByteBufferReadWrite
 * @Description TODO
 * @Author xb
 * @Date 2021/12/11 14:42
 * @Version 1.0
 **/
public class TestByteBufferReadWrite {
  public static void main(String[] args) {
    ByteBuffer buffer = ByteBuffer.allocate(10);

    buffer.put((byte) 0x61); //'a'
    ByteBufferUtil.debugAll(buffer);
    buffer.put(new byte[]{0x62,0x63,0x63});
    ByteBufferUtil.debugAll(buffer);

    buffer.flip();
    ByteBufferUtil.debugAll(buffer);

    System.out.println(buffer.get());

    ByteBufferUtil.debugAll(buffer);

    buffer.compact();

    ByteBufferUtil.debugAll(buffer);
  }
}

