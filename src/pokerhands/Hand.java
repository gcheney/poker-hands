package pokerhands;

public class Hand {
	private Card[] cards;
	private int[] value;
	
	public Hand(Deck deck) {
		value = new int[6];
		cards = new Card[5];
		boolean flush = true;
		boolean straight = false;
		int topStraightValue = 0;
		
		for (int i = 0; i < 5; i++) {
			cards[i] = deck.drawFromDeck();
		}

		int[] ranks = new int[14];
		
		//set all of the ranks array equal to zero
		for (int i=0; i<=13; i++) {
			ranks[i] = 0; 
		}
		
		//Fill ranks array with number of cards for each rank
		for (int i=0; i <= 4; i++) {
			ranks[cards[i].getRank()]++;
		}
		
		int sameCards = 1, sameCards2 = 1;			//there will be at least one card of any rank
		int largeGroupRank = 0, smallGroupRank = 0; //record rank of the group with 2 or more sameCard
		
		//Find if there are any pairs, three of a kinds, fur of a kind etc.
		for (int i = 13; i >= 1; i--) { 
			if (ranks[i] > sameCards) {
				if (sameCards != 1) {   
					sameCards2 = sameCards;
					smallGroupRank = i;
				}
				sameCards = ranks[i];
				largeGroupRank = i;
			}
			else if (ranks[i] > sameCards2) {
				sameCards2 = ranks[i];
				smallGroupRank = i;
			}
		}
		
		//Check to see if there was a flush (all cards are same suit)
		for (int i=0; i<4; i++) {
			if (cards[i].getSuit() != cards[i + 1].getSuit()) {
				flush = false;
			}
		}
		
		//Check for a Straight
		for (int i=1; i<=9; i++) {
			if (ranks[i] == 1 && ranks[i+1] == 1 
					&& ranks[i+2]==1 && ranks[i+3]==1 
					&& ranks[i+4]==1) 
			{
				straight = true;
				topStraightValue = i + 4;
				break;
			}
		}
		
		//Check for straight with ace high
		if (ranks[10] == 1 && ranks[11] == 1 
				&& ranks[12]==1 && ranks[13]==1 
				&& ranks[1]==1)
		{
			straight = true;
			topStraightValue = 14;//Aces High
		}
		
		//Order the cards by rank, excluding pairs, threes and fours of  a kind
		int[] orderedRanks = new int[5];
		int index = 0;
		
		//Check for aces first
		if(ranks[1] == 1){
			orderedRanks[index]=14;//record an ace as 14 instead of one, as its the highest card 
			index++;//increment position  
		}
		//check all other cards, store by highest value first (decreasing/countdown loop)
		for (int i = 13; i >= 2; i--) {
			if (ranks[i]==1) {
				orderedRanks[index] = i;
				index++;
			}
		}
		
		//Start Hand Evaluation
		//No Pairs
		if (sameCards ==1) {
			value[0] = 1;//this is the lowest type of hand, so it gets the lowest value 
			value[1] = orderedRanks[0];//then the next highest card,  etc..
			value[2] = orderedRanks[1];
			value[3] = orderedRanks[2];
			value[4] = orderedRanks[3];
			value[5] = orderedRanks[4];
		}
		
		//one Pair
		if (sameCards == 2 && sameCards2 == 1) { 
			value[0] = 2;				//pair ranked higher than a single card
			value[1] = largeGroupRank;	//store the rank of the pair
			value[2] = orderedRanks[0];
			value[3] = orderedRanks[1];
			value[4] = orderedRanks[2]; //pairs being stored together and not counted in ordeedRanks
		}
		
		 //Two pairs
		if (sameCards == 2 && sameCards2 == 2) { 
			value[0] = 3;
			value[1] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank; //rank of greater pair
			/*Could also be written as:
			 * if(largeGroupRank > smallGroupRank)
			 * 		value[1] = largeGroupRank;
			 * else
			 * 		value[1] = smallGroupRank;
			 */
			value[2] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank; //rank of smaller pair 
		    value[3] = orderedRanks[0]; //extra card since pairs are not counted
		}
		
		//Three of a Kind
		if (sameCards == 3 && sameCards2 != 2) { 
			value[0] = 4;
			value[1] = largeGroupRank;
			value[2] = orderedRanks[0];
			value[3] = orderedRanks[2];
		}
		
		//Straight
		if (straight) {
			value[0] = 5;
			value[1] = topStraightValue;
		}
		
		//Flush 
		if (flush) {
			value[0] = 6;
			value[1] = orderedRanks[0];//winner of tie determined by rank of cards
			value[2] = orderedRanks[1];
			value[3] = orderedRanks[2];
			value[4] = orderedRanks[3];
			value[5] = orderedRanks[4];
		}
		
		//Full House
		if (sameCards == 3 && sameCards == 2) {
			value[0] = 7;
			value[1] = largeGroupRank;
			value[2] = smallGroupRank;
		}
		
		//Four of a Kind
		if (sameCards == 4) {
			value[0] = 8;
			value[1] = largeGroupRank;
			value[2] = orderedRanks[0];//Extra card
		}
		
		//Straight Flush
		if (straight && flush) {
			value[0] = 9;
			value[1] = topStraightValue;
		}
	}
	
	public Card[] getCards() {
		return cards;
	}
	
	public int compareTo(Hand that) {		
		for (int i=0; i<6; i++) {
			if (this.value[i] > that.value[i]) {
				return 1;
			}
			else if (this.value[i] != that.value[i]) {
				return  -1;
			}
		}
		
		return 0; //if hands are equal returns 0
	}
	
	public String display(){
		String s;
		switch(value[0]) {
			case 1:
				s = "High Card";
				break;
			case 2:
				s = "Pair of " + Card.rankAsString(value[1]) + "\'s";  
				break;
			case 3:
				s = "Pair of " + Card.rankAsString(value[1]) + "\'s and pair of " 
						+ Card.rankAsString(value[2]) + "\'s";
				break;
			case 4:
				s = "Three of a kind with " + Card.rankAsString(value[1]) + "\'s"; 
				break;
			case 5:
				s = Card.rankAsString(value[1]) + "\'s high straight";
				break;
			case 6:
				s = "Flush";
				break;
			case 7:
				s = "Full House " + Card.rankAsString(value[1]) + "\'s" 
						+ " over " + Card.rankAsString(value[2]) + "\'s";
				break;
			case 8:
				s = "Four of a kind with" + Card.rankAsString(value[1]) + "\'s"; 
				break;
			case 9:
				if(value[1] == 14)
					s = "Royal Flush. " + Card.rankAsString(value[1]) + "\'s high";
				else
					s = "Straight Flush. " + Card.rankAsString(value[1]) + "\'s high";
				break;
			default:
				s = "Error in Hand.display: value[0] contains an invalid value.";  
		}
		
		return s;
	}
	
	public void displayAll() {
		for (int i = 0; i < 5; i++) {
			System.out.println(cards[i].asString());
		}
	}
	
	public void testRandomization() {
		Deck deck = new Deck();
		System.out.println(deck.getTotalCards());	
		Card C;
		while (deck.getTotalCards() != 0) {
			C = deck.drawFromDeck();
			System.out.println(C.asString());
		}
	}
	
}//End Hand Class
