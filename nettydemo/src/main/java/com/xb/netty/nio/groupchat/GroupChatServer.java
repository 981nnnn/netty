package com.xb.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @ClassName GroupChatServer
 * @Description TODO
 * @Author xb
 * @Date 2021/11/23 14:21
 * @Version 1.0
 **/
public class GroupChatServer {
  private static final int PORT = 6667;
  private Selector selector;
  private ServerSocketChannel listenChannel;

  public GroupChatServer() {
    try {
      // 得到选择器
      selector = Selector.open();
      // 得到chanel
      listenChannel = ServerSocketChannel.open();
      // 给channel 绑定端口port
      listenChannel.socket().bind(new InetSocketAddress(PORT));
      // 设置channel 非阻塞
      listenChannel.configureBlocking(false);
      // 将channel 注册进 selector
      listenChannel.register(selector, SelectionKey.OP_ACCEPT);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void listen() {

    try {
      //循环处理
      while (true) {
        final int count = selector.select();
        if (count > 0) {
          final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
          while (iterator.hasNext()) {
            final SelectionKey key = iterator.next();
            if (key.isAcceptable()) {
              final SocketChannel sc = listenChannel.accept();
              sc.configureBlocking(false);
              sc.register(selector, SelectionKey.OP_READ);
              System.out.println(sc.getRemoteAddress() + " 上线 ");
            }
            if (key.isReadable()) {

            }
            iterator.remove();
          }
        } else {
          System.out.println("等待....");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 发生异常处理
    }


  }

  public void readData(SelectionKey key) {
    SocketChannel channel = null;
    try {
      // 得到channel
      channel = (SocketChannel) key.channel();
      // 创建buffer
      final ByteBuffer buffer = ByteBuffer.allocate(1024);
      final int count;
      // 根据count 的值做处理
      count = channel.read(buffer);

      if (count > 0) {
          // 把缓冲区的数据转成字符串
        final String msg = new String(buffer.array());
        System.out.println("form 客户端"+msg);
        //向其他的客户端转发消息（去掉信息）,专门写一个方法来处理

      }
    } catch (IOException e) {
      try {
        System.out.println(channel.getRemoteAddress() + "离线了..");
        //取消注册
        key.cancel();
        //关闭通道
        channel.close();
      } catch (IOException e2) {
        e2.printStackTrace();
      }
    }
  }

  private void sendInfoToOtherClients(String msg,SocketChannel channel) throws IOException {
    System.out.println("服务器转发消息中");
    for (SelectionKey key : selector.keys()) {
      final SelectableChannel targetChannel = key.channel();

     if( targetChannel instanceof SocketChannel && targetChannel !=channel){
        SocketChannel dest = (SocketChannel) targetChannel;

       final ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

       dest.write(buffer);
     }
    }
  }

  public static void main(String[] args) {
    final GroupChatServer groupChatServer = new GroupChatServer();
    groupChatServer.listen();
  }

}
