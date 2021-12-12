package com.xb.netty.nio.test;

import java.nio.ByteBuffer;

/**
 * @ClassName TestByteBufferAllocate
 * @Description TODO
 * @Author xb
 * @Date 2021/12/11 23:20
 * @Version 1.0
 **/
public class TestByteBufferAllocate {
  public static void main(String[] args) {
    System.out.println(ByteBuffer.allocate(16).getClass());
    System.out.println(ByteBuffer.allocateDirect(16).getClass());
    /**
     * class java.nio.HeapByteBuffer  --java 堆内存，读写效率较低。受到GC 的影响，GC会移动地址
     * class java.nio.DirectByteBuffer  -- 直接内存，读写效率高（少一次拷贝）。不会受到GC 影响 。 分配的效率低。
     */
  }
}
