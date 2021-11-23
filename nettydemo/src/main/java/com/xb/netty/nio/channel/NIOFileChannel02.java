package com.xb.netty.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOFileChannel02
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 10:37
 * @Version 1.0
 **/
public class NIOFileChannel02 {
  public static void main(String[] args) throws IOException {
    final File file = new File("d:\\file01.txt");

    final FileInputStream fileInputStream = new FileInputStream(file);

    final FileChannel fileChannel = fileInputStream.getChannel();

    final ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

    final int read = fileChannel.read(byteBuffer);

    System.out.println(new String(byteBuffer.array()));

    fileInputStream.close();

    fileChannel.close();
  }
}
