package com.http.netty.live;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wanchongyang
 * @date 2018/8/19 下午5:36
 */
public class LiveHandler extends SimpleChannelInboundHandler<LiveMessage> {

    private static Map<Integer, LiveChannelCache> channelCache = new HashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LiveMessage msg) {
        Channel channel = ctx.channel();
        final int hashCode = channel.hashCode();
        System.out.println("channel hashCode:" + hashCode + " msg:" + msg + " cache:" + channelCache.size());

        if (!channelCache.containsKey(hashCode)) {
            System.out.println("channelCache.containsKey(hashCode), put key:" + hashCode);
            channel.closeFuture().addListener(future -> {
                System.out.println("channel close, remove key:" + hashCode);
                channelCache.remove(hashCode);
            });
            ScheduledFuture scheduledFuture = ctx.executor().schedule(
                    () -> {
                        System.out.println("schedule runs, close channel:" + hashCode);
                        channel.close();
                    }, 10, TimeUnit.SECONDS);
            channelCache.put(hashCode, new LiveChannelCache(channel, scheduledFuture));
        }

        switch (msg.getType()) {
            case LiveMessage.TYPE_HEART: {
                LiveChannelCache cache = channelCache.get(hashCode);
                ScheduledFuture scheduledFuture = ctx.executor().schedule(
                        () -> channel.close(), 5, TimeUnit.SECONDS);
                cache.getScheduledFuture().cancel(true);
                cache.setScheduledFuture(scheduledFuture);
                ctx.channel().writeAndFlush(msg);
                break;
            }
            case LiveMessage.TYPE_MESSAGE: {
                channelCache.entrySet().stream().forEach(entry -> {
                    Channel otherChannel = entry.getValue().getChannel();
                    otherChannel.writeAndFlush(msg);
                });
                break;
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("exceptionCaught");
        if (null != cause) {
            cause.printStackTrace();
        }
        if (null != ctx) {
            ctx.close();
        }
    }
}