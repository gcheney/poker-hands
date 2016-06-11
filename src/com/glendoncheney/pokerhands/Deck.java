package com.glendoncheney.pokerhands;

import java.util.*;

/**
 * The Deck class represents a full deck of cards
 * in the poker game. 
 * @author Glendon Cheney
 *
 */
public class Deck {
	private ArrayList<Card> cards;
	private final int NUM_CARDS_IN_DECK = 52;
	private final int NUM_SUITS = 4;
	private final int NUM_RANKS = 13;
	
	/**
	 * Initializes a new deck of 52 cards
	 */
	public Deck() {
		cards = new ArrayList<Card>();
		int index1, index2;
		Random randomGenerator = new Random();
		
		for (int i = 0; i < NUM_SUITS; i++) {
			for (int j = 0; j < NUM_RANKS; j++) {
				cards.add(new Card(i, j));
			}
		}
		
		for (int i = 0; i < NUM_CARDS_IN_DECK; i++) {
			index1 = randomGenerator.nextInt(cards.size() - 1);
			index2 = randomGenerator.nextInt(cards.size() - 1);
			
			Card temp = cards.get(index2);
			cards.set(index2 , cards.get(index1));  
			cards.set(index1, temp);
		}
	}
	
	/**
	 * Draws the top card from the deck
	 * @return The top card of the deck
	 */
	public Card drawFromDeck() {
		return cards.remove(0);
	}
	
	/**
	 * Returns the total amount of cards in the deck
	 * @return the total amount of cards in the deck
	 */
	public int getTotalCards() {
		return cards.size();
	}
}
