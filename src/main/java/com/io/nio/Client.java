package com.io.nio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author wanchongyang
 * @date 2022/2/12 12:10 AM
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9999);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        // 先向服务端发送数据
        os.write("Hello, Server!\0".getBytes());

        // 读取服务端发来的数据
        int b;
        while ((b = is.read()) != 0) {
            System.out.print((char) b);
        }
        System.out.println();

        socket.close();
    }
}
