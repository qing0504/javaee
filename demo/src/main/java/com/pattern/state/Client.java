package com.pattern.state;

/**
 * 考虑一个在线投票系统的应用，要实现控制同一个用户只能投一票，如果一个用户反复投票，而且投票次数超过5次，
 * 则判定为恶意刷票，要取消该用户投票的资格，当然同时也要取消他所投的票；如果一个用户的投票次数超过8次，
 * 将进入黑名单，禁止再登录和使用系统。
 *
 * 要使用状态模式实现，首先需要把投票过程的各种状态定义出来，
 * 根据以上描述大致分为四种状态：正常投票、反复投票、恶意刷票、进入黑名单。然后创建一个投票管理对象（相当于Context）。
 *
 * Created by wanchongyang on 2017/9/15.
 */
public class Client {
    public static void main(String[] args) {
        VoteManager vm = new VoteManager();
        for (int i = 0; i < 9; i++) {
            vm.vote("u1", "A");
        }
    }
}
