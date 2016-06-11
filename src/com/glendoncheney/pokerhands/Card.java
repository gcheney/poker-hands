package com.glendoncheney.pokerhands;

/**
 * The Card class represents a card in the poker game 
 * @author glen
 *
 */
public class Card {
	private int rank, suit;
	
	private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
	private static String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", 
		"8", "9", "10", "Jack", "Queen", "King" };
	
	private static String[] s = { "h", "s", "d", "c" };
	private static String[] r = { "1", "2", "3", "4", "5", "6", "7", 
		"8", "9", "10", "11", "12", "13" };
	
	/**
	 * Initializes a new Card with the provided 
	 * rank and suit
	 * @param suit The suit of the card
	 * @param rank The rank of the card
	 */
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public static String rankAsString(int rank) {
		return ranks[rank];
	}
	
	public String asString()  {  
		return ranks[rank] + " of " + suits[suit];  
	}  
	
	public int getRank() {
		return rank;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public String getImageValue() {
		return "/images/" +s[suit] + r[rank] + ".png";
	}

}
