package com.xb.netty.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOFileChannel03
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 11:00
 * @Version 1.0
 **/
public class NIOFileChannel03 {

  private static FileOutputStream fileOutputStream;

  public static void main(String[] args) throws IOException {
    File file = new File("d:\\file01.txt");
    final FileInputStream fileInputStream = new FileInputStream(file);
    final FileChannel fileChannel01 = fileInputStream.getChannel();
    fileOutputStream = new FileOutputStream("d:\\file02.txt");
    final FileChannel fileChannel02 = fileOutputStream.getChannel();

    final ByteBuffer byteBuffer = ByteBuffer.allocate(512);
    while (true){
      byteBuffer.clear();// 清空buffer
      final int read = fileChannel01.read(byteBuffer);
      System.out.println("read=="+read);
      if(read==-1){
        break;
      }

      byteBuffer.flip();
      fileChannel02.write(byteBuffer);
    }
    fileInputStream.close();
    fileOutputStream.close();
  }
}
