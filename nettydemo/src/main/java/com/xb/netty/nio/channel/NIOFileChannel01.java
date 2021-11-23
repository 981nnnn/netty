package com.xb.netty.nio.channel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOFileChannel01
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 10:32
 * @Version 1.0
 **/
public class NIOFileChannel01{
  public static void main(String[] args) throws IOException {
    String str = "hello world";
    // 创建一个输出流
    final FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
    //通过 fileOutputStream 获取对应的 FileChannel
    //这个 fileChannel 真实类型是 FileChannelImpl
    final FileChannel fileChannel = fileOutputStream.getChannel();
    //创建一个缓冲区 ByteBuffer
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    //将 str 放入 byteBuffer
    byteBuffer.put(str.getBytes());
    //对 byteBuffer 进行 flip
    byteBuffer.flip();
    //将 byteBuffer 数据写入到 fileChannel
    fileChannel.write(byteBuffer);

    fileOutputStream.close();

  }
}
