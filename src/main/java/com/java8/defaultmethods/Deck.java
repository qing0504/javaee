package com.java8.defaultmethods;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author wanchongyang
 * @date 2018/4/18 下午4:48
 */
public interface Deck {

    List<Card> getCards();
    Deck deckFactory();
    int size();
    void addCard(Card card);
    void addCards(List<Card> cards);
    void addDeck(Deck deck);
    void shuffle();
    void sort();
    void sort(Comparator<Card> c);
    String deckToString();

    Map<Integer, Deck> deal(int players, int numberOfCards)
            throws IllegalArgumentException;

}
