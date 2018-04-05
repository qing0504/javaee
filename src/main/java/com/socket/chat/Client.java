package com.socket.chat;

import java.io.IOException;
import java.net.Socket;

/**
 * 聊天客服端
 * 创建客户端：发送数据+接收数据
 * 写出数据：输出流
 * 读取数据：输入流
 *
 * 输入流和输出流在同一线程内应该独立处理，彼此独立
 * Created by Martin on 2017/1/17.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);

        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
}
