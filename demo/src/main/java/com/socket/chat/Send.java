package com.socket.chat;

import com.common.utils.FileUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 发送数据线程
 * Created by Martin on 2017/1/17.
 */
public class Send implements Runnable {
    //控制台输入流
    private BufferedReader console;
    //管道输出流
    private DataOutputStream dos;
    //控制线程开关
    private boolean isRunning = true;

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket socket) {
        this();
        try {
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            isRunning = false;
            FileUtils.close(dos, console);
        }

    }

    /**
     * 从控制台接收数据
     * @return
     */
    private String getMsgFromConsole() {
        String msg = "";
        try {
            msg = console.readLine();
        } catch (IOException e) {

        }

        return msg;
    }

    @Override
    public void run() {
        //线程体
        while (isRunning) {
            send();
        }
    }

    /**
     * 发送数据
     */
    private void send() {
        String msg = getMsgFromConsole();
        if (null != msg && !msg.equals("")) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                isRunning = false;
                FileUtils.close(dos, console);
            }
        }
    }
}
