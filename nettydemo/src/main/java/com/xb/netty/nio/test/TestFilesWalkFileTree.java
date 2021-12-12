package com.xb.netty.nio.test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TestFilesWalkFileTree
 * @Description TODO
 * @Author xb
 * @Date 2021/12/12 22:09
 * @Version 1.0
 **/
public class TestFilesWalkFileTree {
  public static void main(String[] args) throws IOException {
    walkJarTree();
  }

  private static void walkFileTree() throws IOException {
    AtomicInteger dirCount = new AtomicInteger();
    AtomicInteger fileCount = new AtomicInteger();
    Files.walkFileTree(Paths.get("E:\\software\\jdk\\jdk1.8.0_152"),new SimpleFileVisitor<Path>(){

      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("====>"+dir);
        dirCount.incrementAndGet();
        return super.preVisitDirectory(dir, attrs);
      }

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file);
        fileCount.incrementAndGet();
        return super.visitFile(file, attrs);
      }
    });
    System.out.println("dir count"+dirCount);
    System.out.println("file count"+fileCount);
  }

  private static void walkJarTree() throws IOException {
    AtomicInteger jarCount = new AtomicInteger();
    Files.walkFileTree(Paths.get("E:\\software\\jdk\\jdk1.8.0_152"),new SimpleFileVisitor<Path>(){
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().endsWith(".jar")) {
          System.out.println(file);
          jarCount.incrementAndGet();
        }
        return super.visitFile(file, attrs);
      }
    });
    System.out.println(jarCount);
  }

}
