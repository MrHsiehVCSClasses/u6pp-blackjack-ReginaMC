package u6pp;
import java.util.Random;


public class Deck {
	
// instance variables: "defaultDeck" is an empty deck and "pointer" is used 
//	to determine the first card object of the deck
	Card[] defaultDeck = new Card[52];
	private int pointer = 0;
	
// constructor that creates a complete set of playing cards with suits and values
	public Deck() {	
		int counter = 0;
		for(int i = 0; i < 4; i++) {
			for(int x = 0; x < 13; x++) {
				defaultDeck[counter] = new Card(Card.SUITS[i], Card.VALUES[x]);
				counter++;
			}
		}
		
	}
// checks the amount of cards left in the deck	
	public int numLeft() {
		return defaultDeck.length-pointer;
	}
// returns the first card of the deck 	
	public Card deal() {
		int a = pointer;
		pointer++;
		return defaultDeck[a];
	}
// randomizes the order of the card objects in the deck 	
	public void shuffle() {
		pointer = 0;
		for (int i = 0; i < 100; i++) {
			int index = (int)(Math.random()* (52));
			Card temp = defaultDeck[0];
			defaultDeck[0] = defaultDeck[index];
			defaultDeck[index] = temp;
		}
	}
}