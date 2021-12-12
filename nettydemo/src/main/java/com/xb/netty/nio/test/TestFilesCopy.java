package com.xb.netty.nio.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @ClassName TestFilesCopy
 * @Description TODO
 * @Author xb
 * @Date 2021/12/12 23:00
 * @Version 1.0
 **/
public class TestFilesCopy {
  public static void main(String[] args) throws IOException {
    String source = "C:\\Users\\xb\\Desktop\\test";
    String target = "C:\\Users\\xb\\Desktop\\test01";

    Files.walk(Paths.get(source)).forEach(path -> {
      try {

        String targetName = path.toString().replace(source, target);

        if (Files.isDirectory(path)) {
          Files.createDirectory(Paths.get(targetName));
        } else if (Files.isRegularFile(path)) {
          Files.copy(path, Paths.get(targetName));
        }
      }catch (Exception e){
        e.printStackTrace();
      }
    });
  }
}
