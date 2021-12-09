package com.xb.netty.nio.groupchat;

import com.sun.scenario.effect.impl.prism.PrImage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @ClassName GroupChatClient
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 22:30
 * @Version 1.0
 **/
public class GroupChatClient {
  private final String HOST="127.0.0.1";
  private final int PORT=6667;
  private Selector selector;
  private SocketChannel socketChannel;
  private String userName;

  public GroupChatClient() throws IOException {
    this.selector = Selector.open();
    // 连接服务器
    this.socketChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));
    // 设置非阻塞
    socketChannel.configureBlocking(false);
    // 将channel 注册进selector
    socketChannel.register(selector, SelectionKey.OP_READ);
    // 得到userName
    this.userName = socketChannel.getLocalAddress().toString().substring(1);
    System.out.println(this.userName+"is Ok..");
  }

  // 向服务器发送消息
  public void sendInfo(String info){
    info = userName+"说："+info;
    try {
      socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    }catch (IOException e){
      e.printStackTrace();
    }
  }

  public void readInfo(){
    try{
      int readChannels = selector.select();
      if(readChannels>0){ // 有可以用的通道
        final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
          final SelectionKey key = iterator.next();
          if (key.isReadable()) {
            // 得到相关通道
           SocketChannel sc= (SocketChannel) key.channel();
           // 得到一个Buffer
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            sc.read(buffer);
            final String msg = new String(buffer.array());
            System.out.println(msg.trim());
          }
        }
        iterator.remove();// 删除当前的selectionKey，防止重复操作
      }else {
        System.out.println("没有可以用的通道...");
      }
    }catch (Exception e){

    }
  }

  public static void main(String[] args) throws IOException {
    final GroupChatClient chatClient = new GroupChatClient();
    //
    new Thread(){
      @Override
      public void run(){
        while (true){
          chatClient.readInfo();
          try {
            Thread.currentThread().sleep(3000);
          }catch (Exception e){
            e.printStackTrace();
          }
        }
      }
    }.start();

    //发送数据给服务器端
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String s = scanner.nextLine();
      chatClient.sendInfo(s);
    }

  }
}
