package com.socket.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Martin on 2017/1/17.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //1、创建客户端 必须指定服务器、端口 面向连接
        Socket client = new Socket("localhost", 8888);
        //2、接收数据
        //BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //String msg = br.readLine();

        DataInputStream dis = new DataInputStream(client.getInputStream());
        String msg = dis.readUTF();

        System.out.println("服务端响应，接收数据：" + msg);
    }
}
