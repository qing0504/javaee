package com.defaultmethods;

import java.util.Comparator;

/**
 * @author wanchongyang
 * @date 2018/4/18 下午5:00
 */
public class SortByRankThenSuit implements Comparator<Card> {
    @Override
    public int compare(Card firstCard, Card secondCard) {
        int compVal =
                firstCard.getRank().value() - secondCard.getRank().value();
        if (compVal != 0) {
            return compVal;
        } else {
            return firstCard.getSuit().value() - secondCard.getSuit().value();
        }
    }

}
