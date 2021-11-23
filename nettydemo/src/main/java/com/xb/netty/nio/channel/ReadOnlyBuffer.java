package com.xb.netty.nio.channel;

import java.nio.ByteBuffer;

/**
 * @ClassName ReadOnlyBuffer
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 11:19
 * @Version 1.0
 **/
public class ReadOnlyBuffer {
  public static void main(String[] args) {
    final ByteBuffer buffer = ByteBuffer.allocate(64);

    for (int i = 0; i < 64; i++) {
      buffer.put((byte) i);
    }

    buffer.flip();

    final ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
    System.out.println(readOnlyBuffer.getClass());

    while (readOnlyBuffer.hasRemaining()) {
      System.out.println(readOnlyBuffer.get());
    }

    readOnlyBuffer.put((byte) 100);

  }
}
