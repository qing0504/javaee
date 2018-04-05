package com.socket.chat;

import com.common.utils.FileUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室服务端
 * Created by Martin on 2017/1/17.
 */
public class Server {
    private List<MyChannel> all = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(9999);

        while (true) {
            Socket client = server.accept();
            MyChannel channel = new MyChannel(client);
            all.add(channel);
            new Thread(channel).start();
        }
    }

    /**
     * 一个客户端一条通道
     */
    private class MyChannel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;

        public MyChannel(Socket client) {
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                isRunning = false;
                FileUtils.close(dis, dos);
            }
        }


        /**
         * 读取数据
         */
        private String reveive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                isRunning = false;
                FileUtils.close(dis);
                all.remove(this);
            }

            return msg;
        }

        /**
         * 发送数据
         */
        public void send(String msg) {
            if (null == msg || msg.equals("")) {
                return;
            }

            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                isRunning = false;
                FileUtils.close(dos);
                all.remove(this);
            }

        }

        /**
         * 发送给其他客户端
         */
        public void sendOthers() {
            String msg = this.reveive();
            //遍历容器
            for (MyChannel channel : all) {
                if (channel == this) {
                    continue;
                }

                //发送给其他客服端
                channel.send(msg);
            }
        }

        @Override
        public void run() {
            while (isRunning) {
                //send(reveive()); // 自我发送、获取
                sendOthers();
            }
        }
    }

}
