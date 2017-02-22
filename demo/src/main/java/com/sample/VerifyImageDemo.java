package com.sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by Martin on 2017/2/15.
 */
public class VerifyImageDemo {
    public static void main(String[] args) throws IOException {
        int width = 200;
        int height = 50;
        //获取BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔对象
        Graphics graphics = image.getGraphics();
        //画矩形边框
        graphics.setColor(Color.GREEN);
        graphics.drawRect(0, 0, width, height);
        //设置背景颜色
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(1, 1, width - 2, height - 2);
        Font font = new Font("宋体", Font.BOLD|Font.ITALIC, 18);
        graphics.setFont(font);
        //画干扰线
        Random r = new Random();
        graphics.setColor(Color.GRAY);
        for (int x = 0; x < 20; x++) {
            graphics.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }
        int s = 20;
        //画数字
        graphics.setColor(Color.RED);
        for (int x = 0; x < 4; x++) {
            graphics.drawString(r.nextInt(10)+"", s, 25);
            s += 40;
        }
        OutputStream out = new BufferedOutputStream(new FileOutputStream("E:/javaee/demo/vv.jpg"));
        //输出流打印出去
        ImageIO.write(image, "jpg", out);
        //关闭流
        out.close();

    }
}
