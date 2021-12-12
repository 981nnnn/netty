package com.xb.netty.nio.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @ClassName TestFileChannelTransferTo
 * @Description TODO
 * @Author xb
 * @Date 2021/12/12 21:37
 * @Version 1.0
 **/
public class TestFileChannelTransferTo {
  public static void main(String[] args) {
    try {
      FileChannel from = new FileInputStream("D:\\file01.txt").getChannel();
      FileChannel to = new FileOutputStream("D:\\file02.txt").getChannel();
      // 效率高，底层会利用操作系统的零拷贝进行优化
      from. transferTo(0,from.size(),to);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
