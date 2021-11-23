package com.xb.netty.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @ClassName ScatteringAndGatheringTest
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 11:33
 * @Version 1.0
 **/
public class ScatteringAndGatheringTest {

  public static void main(String[] args) throws IOException {
    final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

    final InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

    serverSocketChannel.socket().bind(inetSocketAddress);

    final ByteBuffer[] byteBuffers = new ByteBuffer[2];

    byteBuffers[0] = ByteBuffer.allocate(5);
    byteBuffers[1] = ByteBuffer.allocate(4);

    final SocketChannel socketChannel = serverSocketChannel.accept();

    int messageLength = 8;

    while (true){
      int byteRead = 0;
      while (byteRead<messageLength){
        socketChannel.read(byteBuffers);
        byteRead+=1;
        System.out.println("byteRead="+byteRead);
        Arrays.asList(byteBuffers).stream().map(buffer -> "position = " + buffer.position() + ", limit = " + buffer.limit()).forEach(System.out::println);

      }
      //将所有的 buffer 进行 flip
      Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());
      //将数据读出显示到客户端
      long byteWirte = 0;
      while (byteWirte < messageLength) {
        long l = socketChannel.write(byteBuffers);//
        byteWirte += l;
      }
      //将所有的buffer进行clear
      Arrays.asList(byteBuffers).forEach(buffer -> {
        buffer.clear();
      });
      System.out.println("byteRead = " + byteRead + ", byteWrite = " + byteWirte + ", messagelength = " + messageLength);
    }
  }
}
