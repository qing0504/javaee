package com.pattern.state;

/**
 * 具体状态类——重复投票
 * Created by wanchongyang on 2017/9/15.
 */
public class RepeatVoteState implements VoteState {
    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //重复投票，暂时不做处理
        System.out.println("请不要重复投票");
    }
}
