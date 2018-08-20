package com.http.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @author wanchongyang
 * @date 2018/8/19 下午5:42
 */
public class LongConnTest {
    String host = "localhost";
    int port = 8888;

    public void testLongConn() throws Exception {
        System.out.println("start");
        final Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                try {
                    byte[] input = new byte[64];
                    int readByte = socket.getInputStream().read(input);
                    System.out.println("readByte " + readByte);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        int code;
        while (true) {
            code = scanner.nextInt();
            System.out.println("input code:" + code);
            if (code == 0) {
                break;
            } else if (code == 1) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                byteBuffer.put((byte) 1);
                byteBuffer.putInt(0);
                socket.getOutputStream().write(byteBuffer.array());
                System.out.println("write heart finish!");
            } else if (code == 2) {
                byte[] content = ("hello, I'm" + hashCode()).getBytes();
                ByteBuffer byteBuffer = ByteBuffer.allocate(content.length + 5);
                byteBuffer.put((byte) 2);
                byteBuffer.putInt(content.length);
                byteBuffer.put(content);
                socket.getOutputStream().write(byteBuffer.array());
                System.out.println("write content finish!");
            }
        }
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        new LongConnTest().testLongConn();
    }

}
