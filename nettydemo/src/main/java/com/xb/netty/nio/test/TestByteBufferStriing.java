package com.xb.netty.nio.test;

import com.xb.netty.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName TestByteBufferStriing
 * @Description TODO
 * @Author xb
 * @Date 2021/12/12 16:01
 * @Version 1.0
 **/
public class TestByteBufferStriing {
  public static void main(String[] args) {
    // 1.字符串转为ByteBuffer
    ByteBuffer  buffer = ByteBuffer.allocate(16);
    buffer.put("hello".getBytes());
    ByteBufferUtil.debugAll(buffer);

    // 2. Charset
    ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
    ByteBufferUtil.debugAll(buffer2);

    // 3. wrap
    ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
    ByteBufferUtil.debugAll(buffer3);

    // -----字节转为字符串 ---
    String str = StandardCharsets.UTF_8.decode(buffer2).toString();
    System.out.println(str);
    ByteBufferUtil.debugAll(buffer2);
  }


}
