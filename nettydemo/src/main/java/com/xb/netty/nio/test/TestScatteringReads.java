package com.xb.netty.nio.test;

import com.xb.netty.nio.util.ByteBufferUtil;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName TestScatteringReads
 * @Description TODO
 * @Author xb
 * @Date 2021/12/12 16:19
 * @Version 1.0
 **/
public class TestScatteringReads {
  public static void main(String[] args) {
    try ( FileChannel channel = new RandomAccessFile("resource/data.json","r").getChannel()){
      ByteBuffer buffer1 = ByteBuffer.allocate(3);
      ByteBuffer buffer2 = ByteBuffer.allocate(3);
      ByteBuffer buffer3 = ByteBuffer.allocate(4);
      buffer1.flip();
      buffer2.flip();
      buffer3.flip();
      ByteBufferUtil.debugAll(buffer1);
      ByteBufferUtil.debugAll(buffer2);
      ByteBufferUtil.debugAll(buffer3);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
