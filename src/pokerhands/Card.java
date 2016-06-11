package pokerhands;

public class Card {
	private int rank, suit;
	
	private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
	private static String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", 
		"8", "9", "10", "Jack", "Queen", "King" };
	
	private static String[] s = { "h", "s", "d", "c" };
	private static String[] r = { "1", "2", "3", "4", "5", "6", "7", 
		"8", "9", "10", "11", "12", "13" };
	
	public static String rankAsString(int rank) {
		return ranks[rank];
	}
	
	Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
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
		return "images/" +s[suit] + r[rank] + ".png";
	}

}
