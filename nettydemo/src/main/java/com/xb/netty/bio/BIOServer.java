package com.xb.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName BIOServer
 * @Description TODO
 * @Author xb
 * @Date 2021/11/22 23:03
 * @Version 1.0
 **/
public class BIOServer {
  public static void main(String[] args) throws IOException {

    ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

    ServerSocket serverSocket = new ServerSocket(6666);

    System.out.println("启动了");

    while (true) {
      System.out.println("线程信息id = " + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName());
      System.out.println("等待连接...");
      final Socket socket = serverSocket.accept();
      System.out.println("连接到一个客户端");
      newCachedThreadPool.execute(new Runnable() {
        @Override
        public void run() {
          // 可以和客户端通讯
          handler(socket);
        }
      });
    }
  }


  // 编写一个handler 方法，客户端通讯
  public static void handler(Socket socket) {
    System.out.println("线程信息id=" + Thread.currentThread().getId() + "，名字=" + Thread.currentThread().getName());

    byte[] bytes = new byte[1024];

    try {
      // 通过socket 获取输入流
      InputStream inputStream = socket.getInputStream();

      while (true) {
        System.out.println("线程信息id = " + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName());
        System.out.println("read.....");
        int read = inputStream.read(bytes);
        if (read != -1) {
          System.out.println(new String(bytes, 0, read));
        } else {
          break;
        }

      }

    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      System.out.println("关闭和client的连接");
      try {
        socket.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }


  }
}
