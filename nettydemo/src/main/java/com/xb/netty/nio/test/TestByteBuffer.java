package com.xb.netty.nio.test;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.experimental.var;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName TestByteBuffer
 * @Description TODO
 * @Author xb
 * @Date 2021/12/9 21:57
 * @Version 1.0
 **/
public class TestByteBuffer {
  public static void main(String[] args) {

    //FileChannel
    // 1.输入输出流 2.RannomAccesFile
    try{
      System.out.println("12312321");
      FileChannel fileChannel= new FileInputStream("data.txt").getChannel();
        // 准备一个缓冲区
      ByteBuffer  byteBuffer = ByteBuffer.allocate(10);
      fileChannel.read(byteBuffer);
      byteBuffer.flip(); // 切换至读模式

      while (byteBuffer.hasRemaining()){ // 是否还有剩余数据
        byte b = byteBuffer.get();
        System.out.println((char)b);
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}
