package com.socket.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收多个客户端连接
 * Created by Martin on 2017/1/17.
 */
public class MultiServer {
    public static void main(String[] args) throws IOException {
        //1、创建服务端 指定端口
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            //2、接收客服端连接 阻塞式
            Socket socket = server.accept();

            System.out.println("一个客户端建立连接，端口:"+ socket.getPort());
            //3、发送数据
            String msg = "欢迎使用！";
            //输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}
