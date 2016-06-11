package pokerhands;

import java.util.*;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck() {
		cards = new ArrayList<Card>();
		int index1, index2;
		Random randomGenerator = new Random();
		Card temp;
		
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j <= 12; j++) {
				cards.add(new Card(i, j));
			}
		}
		
		for (int i = 0; i < 52; i++) {
			index1 = randomGenerator.nextInt(cards.size() - 1);
			index2 = randomGenerator.nextInt(cards.size() - 1);
			
			temp = cards.get(index2);
			cards.set(index2 , cards.get(index1));  
			cards.set(index1, temp);
		}
	}
	
	public Card drawFromDeck() {
		return cards.remove(0);
	}
	
	public int getTotalCards() {
		return cards.size();
	}
}
