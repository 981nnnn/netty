package com.xb.netty.nio.test;

import com.xb.netty.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @ClassName TestByteBufferExam
 * @Description TODO
 * @Author xb
 * @Date 2021/12/12 16:41
 * @Version 1.0
 **/
public class TestByteBufferExam {
  public static void main(String[] args) {
    /**
     * 网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
     * 但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
     *
     * * Hello,world\n
     * * I'm zhangsan\n
     * * How are you?\n
     *
     * 变成了下面的两个 byteBuffer (黏包，半包)
     *
     * * Hello,world\nI'm zhangsan\nHo
     * * w are you?\n
     *
     * 现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
     */

    ByteBuffer source = ByteBuffer.allocate(32);
    source.put("hello,world\nI‘m zhangsan\nHo".getBytes());
    split(source);
    source.put("w are you?\n".getBytes());
    split(source);
  }

  private static void split(ByteBuffer buffer){
    buffer.flip();
    for (int i = 0; i < buffer.limit(); i++) {
      if (buffer.get(i)=='\n') {
        int length = i +1-buffer.position();
        // 把这条消息存储到新的ByteBuffer
        ByteBuffer target = ByteBuffer.allocate(length);
        for (int j = 0; j < length; j++) {
          target.put(buffer.get());
        }
        ByteBufferUtil.debugAll(target);
      }
    }
    buffer.compact();
  }
}
