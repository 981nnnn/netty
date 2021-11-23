package com.xb.netty.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName MappedByteBufferTest
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 11:26
 * @Version 1.0
 **/
public class MappedByteBufferTest {
  public static void main(String[] args) throws IOException {
    final RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\file01.txt", "rw");

    final FileChannel channel = randomAccessFile.getChannel();

    final MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

    mappedByteBuffer.put(0, (byte) 'h');
    mappedByteBuffer.put(1, (byte) 'Y');
    mappedByteBuffer.put(3, (byte) '9');
    randomAccessFile.close();
    System.out.println("修改成功");

  }
}
