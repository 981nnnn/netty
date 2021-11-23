package com.xb.netty.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOFileChannel04
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 11:10
 * @Version 1.0
 **/
public class NIOFileChannel04 {

  public static void main(String[] args) throws IOException {
    final FileInputStream fileInputStream = new FileInputStream("d:\\export.xls");

    final FileOutputStream fileOutputStream = new FileOutputStream("d:\\export1.xls");

    final FileChannel inputStreamChannel = fileInputStream.getChannel();

    final FileChannel outputStreamChannel = fileOutputStream.getChannel();

    outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

    inputStreamChannel.close();
    outputStreamChannel.close();
    fileInputStream.close();
    fileOutputStream.close();
  }
}
