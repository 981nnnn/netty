package com.xb.netty.nio.test;

import com.xb.netty.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @ClassName TestByteBufferRead
 * @Description TODO
 * @Author xb
 * @Date 2021/12/12 15:13
 * @Version 1.0
 **/
public class TestByteBufferRead {
  public static void main(String[] args) {
    ByteBuffer buffer = ByteBuffer.allocate(16);
    buffer.put(new byte[]{0x61,0x62,0x63,0x64});
    buffer.flip();
//    System.out.println(buffer.get(new byte[3]));
//    ByteBufferUtil.debugAll(buffer);
//    // rewind 从头开始读，position=0
//    buffer.rewind();
//    ByteBufferUtil.debugAll(buffer);

    // mark & reset
    // mark 做一个标记，记录position 位置，reset 是将 position 重置到mark 的位置

//    System.out.println((char)buffer.get());
//    System.out.println((char)buffer.get());
//    buffer.mark();// 加标记，索引为2的位置。
//
//    System.out.println((char)buffer.get());
//    System.out.println((char)buffer.get());
//    buffer.reset();// 重置，mark的标记的位置（第二个位置）
//    System.out.println((char)buffer.get());
//    System.out.println((char)buffer.get());

    System.out.println((char) buffer.get(3));
    ByteBufferUtil.debugAll(buffer);
  }
}
