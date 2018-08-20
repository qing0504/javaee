package com.http.netty.live;

import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * @author wanchongyang
 * @date 2018/8/19 下午5:36
 */
public class LiveChannelCache {
    private Channel channel;
    private ScheduledFuture scheduledFuture;

    public LiveChannelCache(Channel channel, ScheduledFuture scheduledFuture) {
        this.channel = channel;
        this.scheduledFuture = scheduledFuture;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ScheduledFuture getScheduledFuture() {
        return scheduledFuture;
    }

    public void setScheduledFuture(ScheduledFuture scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }
}
