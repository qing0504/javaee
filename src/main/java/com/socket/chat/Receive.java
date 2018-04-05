package com.socket.chat;

import com.common.utils.FileUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Martin on 2017/1/17.
 */
public class Receive implements Runnable {
    private DataInputStream dis;
    private boolean isRunning = true;

    public Receive() {

    }

    public Receive(Socket socket) {
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            isRunning = false;
            FileUtils.close(dis);
        }
    }


    @Override
    public void run() {
        while (isRunning) {
            System.out.println(receive());
        }

    }

    /**
     * 接收数据
     */
    private String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            isRunning = false;
            FileUtils.close(dis);
        }

        return msg;
    }
}
