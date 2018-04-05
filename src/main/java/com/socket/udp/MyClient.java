package com.socket.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 客户端
 *         1、创建客户端+端口
 *         2、准备数据
 *         3、打包
 *         4、发送有
 *         5、释放资源
 * Created by Martin on 2017/1/5.
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        //1、创建客户端+端口
        DatagramSocket client = new DatagramSocket(9998);
        //2、准备数据
        String msg = "UDP编程，非面向连接，不安全，效率高";
        byte[] data = msg.getBytes();

        //数据类型
        //double num = 88.16;
        //data = doubleToByteArray(num);
        //3、打包
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 9999));
        //4、发送数据
        client.send(packet);
        //5、释放资源
        client.close();
    }

    /**
     * 字节数组 数据源+Data 输出流
     * @param num
     * @return
     */
    public static byte[] doubleToByteArray(double num) throws IOException {
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeDouble(num);
        dos.flush();

        //获取数据
        data = bos.toByteArray();
        dos.close();

        return data;
    }
}
