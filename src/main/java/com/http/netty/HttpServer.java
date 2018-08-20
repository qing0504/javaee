package com.http.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 1、Channel，一个客户端与服务器通信的通道
 *
 * 2、ChannelHandler，业务逻辑处理器，分为ChannelInboundHandler和ChannelOutboundHandler
 *
 *     ChannelInboundHandler，输入数据处理器
 *     ChannelOutboundHandler，输出业务处理器
 *
 * 通常情况下，业务逻辑都是存在于ChannelHandler之中
 *
 * 3、ChannelPipeline，用于存放ChannelHandler的容器
 *
 * 4、ChannelContext，通信管道的上下文
 *
 * @author wanchongyang
 * @date 2018/8/19 下午4:34
 */
public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println(
                    "Usage: " + HttpServer.class.getSimpleName() +
                            " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new HttpServer(port).start();
    }

    public void start() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        System.out.println("initChannel ch:" + ch);
                        ch.pipeline()
                                // HttpRequestDecoder，用于解码request
                                .addLast("decoder", new HttpRequestDecoder())
                                // HttpResponseEncoder，用于编码response
                                .addLast("encoder", new HttpResponseEncoder())
                                // aggregator，消息聚合器。HttpObjectAggregator(512 * 1024)的参数含义是消息合并的数据大小
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                .addLast("handler", new HttpHandler());
                    }
                })
                // determining the number of connections queued
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);

        b.bind(port).sync();
    }
}
