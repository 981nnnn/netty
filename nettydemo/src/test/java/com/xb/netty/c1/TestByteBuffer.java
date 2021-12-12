package com.xb.netty.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName TestByteBuffer
 * @Description TODO
 * @Author xb
 * @Date 2021/12/9 22:48
 * @Version 1.0
 **/
@Slf4j
public class TestByteBuffer {
  public static void main(String[] args) {
    try {
      FileChannel channel = new FileInputStream("nettydemo/src/main/resources/data.json").getChannel();

      ByteBuffer buffer = ByteBuffer.allocate(10);

      while (true){

        int read = channel.read(buffer);
        if(read==-1){ // 通道中没有数据
          break;
        }

        buffer.flip();// 切换读写模式

        while (buffer.hasRemaining()) {
          byte b = buffer.get();
          log.debug("实际字节{}",(char) b);
        }
        buffer.clear();// 切换为写模式
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
