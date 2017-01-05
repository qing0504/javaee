package com.socket.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务端
 *         1、创建服务端+端口
 *         2、准备接收容器
 *         3、封装成包
 *         4、接收数据
 *         5、分析数据
 *         6、释放资源
 * Created by Martin on 2017/1/5.
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        //1、创建服务端+端口
        DatagramSocket server = new DatagramSocket(9999);
        //2、准备接收容器
        byte[] container = new byte[1024];
        //3、封装成包
        DatagramPacket packet = new DatagramPacket(container, container.length);
        //4、接收数据
        server.receive(packet);
        //5、分析数据
        byte[] data = packet.getData();
        int len = packet.getLength();
        System.out.println("接收数据内容为：" + new String(data, 0, len));

        //数据类型
        //double num = byteArrayToDouble(packet.getData());
        //System.out.println("接收数据类型数据内容为：" + num);
        //6、释放资源
        server.close();
    }

    /**
     * 字节数组 Data+输入流
     * @param data
     * @return
     */
    public static double byteArrayToDouble(byte[] data) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        double num = dis.readDouble();
        dis.close();

        return num;
    }
}
