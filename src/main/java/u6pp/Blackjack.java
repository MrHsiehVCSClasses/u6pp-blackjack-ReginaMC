package u6pp;
import java.util.Scanner;

public class Blackjack {
// instance variables for the user's name, deck of cards, player's hands, 
// and counters to help navigate the hands
	String user;
	Deck xx;
	static Card[] UHand = new Card[10];
	static Card[] DHand = new Card[10];
	static int co = 0;
	static int userCounter = 0;
	static int dealCounter = 0;
	
	
// constructor to create a new deck 
	public Blackjack() {
		xx = new Deck();
	}
	
// play function: uses other methods and scanner to play a game of blackjack
	public void play() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Blackjack! What is your name?");
		user = sc.nextLine();
		System.out.println("Hello " + user + ", Let's play cards");
		
		String yesNo = "yes";
		int end = 0;
		
//while loop to repeat the game
		while (yesNo.equals("yes") || yesNo.equals("y")) {
			xx.shuffle();
			for (int i = 0; i < 2; i++) {
				UHand[i]=  xx.deal();
				userCounter++;
				DHand[i]=  xx.deal();
				dealCounter++;
			}
			System.out.println("Your Hand: " + UHand[0] + ", " + UHand[1]);
			System.out.println("Dealer's Hand: " + DHand[0] + ", " + DHand[1]);
			
			
	// while loop that runs through turns (hit or stay) for the user and dealer
	// checks hands for blackjack or bust
			if(!(isBlackjack(UHand) || isBlackjack(DHand))) {
					
				while(!(isBust(UHand) || isBust(DHand)) && (end == 0)) {

					System.out.println("Would you like to (h)it or (s)tay?");
					String response = sc.nextLine();
					response.toLowerCase();
					// while loop to check for correct input from the user 	
					while (!(response.equals("h")) && !(response.equals("s"))
						&& !(response.equals("hit")) && !(response.equals("stay"))) {
						System.out.println("Invaldid, try again");
						System.out.println("Would you like to (h)it or (s)tay?");
						response = sc.nextLine();
						response.toLowerCase();
					}
					
					
				// if statement for when the player wants to add another card to the user's hand		
					if (response.equals("h") || response.equals("hit")) {
						UHand[userCounter]= xx.deal();
						userCounter++;
						System.out.println("Your hand: ");
						for (int i = 0; i < userCounter; i++) {
							System.out.println(UHand[i]);
						}
					}
						
					else if (response.equals("s") || response.equals("stay")) {
						while(dealerKeepHitting(DHand)) {
							DHand[dealCounter]= xx.deal();
							dealCounter++;
							System.out.println("Dealer's hand: ");
							for (int i = 0; i < dealCounter; i++) {
								System.out.println(DHand[i]);
							}
								
						}
						end = 1;
					}
				}
				
				System.out.println(determineResults(UHand, DHand));
				
				if (isBust(UHand)) {
					System.out.println(user + " lost");
				}
				else if (isBust(DHand)) {
					System.out.println(user + " won");
				}
			}
			else if (isBlackjack(UHand)) {
				System.out.println(determineResults(UHand, DHand));
			}
			else if (isBlackjack(DHand)) {
				System.out.println(determineResults(DHand, UHand));
			}
			
			System.out.println("Would you like to play again? (y)es (n)o");
			yesNo = sc.nextLine().toLowerCase();
		}
		System.out.println("Thankyou for playing");
		sc.close();
		
	}
// calculates the total points from the cards in a hand
	public static int calcPoints(Card[] hand){
		int points = 0;
		if (hand[0] == UHand[0]) {
			co = userCounter;
		}
		else {
			co = dealCounter;
		}
		for(int i = 0; i < (hand.length - (hand.length - co)); i++) {
			if (hand[i].getValue().equals("Jack") || 
					hand[i].getValue().equals("Queen") ||
					hand[i].getValue().equals("Ace") ||
					hand[i].getValue().equals("King")){
				points += 10;
			}
			else if (hand[i].getValue().equals("Ace")) {
				points += 11;
			}
			else if (hand[i].getValue().equals("2")) {
				points += 2;
			}
			else if (hand[i].getValue().equals("3")) {
				points += 3;
			}
			else if (hand[i].getValue().equals("4")) {
				points += 4;
			}
			else if (hand[i].getValue().equals("5")) {
				points += 5;
			}
			else if (hand[i].getValue().equals("6")) {
				points += 6;
			}
			else if (hand[i].getValue().equals("7")) {
				points += 7;
			}
			else if (hand[i].getValue().equals("8")) {
				points += 8;
			}
			else if (hand[i].getValue().equals("9")) {
				points += 9;
			}
			else if (hand[i].getValue().equals("10")) {
				points += 10;
			}
		}
		return points;
	}

// checks which hand has the higher number without busting
	public static String determineResults(Card[] userHand, Card[] dealerHand) {
		if (calcPoints(userHand) == calcPoints(dealerHand)) {
			return "User Pushes";
		}
		else if (calcPoints(userHand) > calcPoints(dealerHand) && (calcPoints(userHand) <= 21)) {
			return "User Wins";
		}
		else {
			return "User lost";
		}
	}
	
// checks if the hand busted
	public static boolean isBust(Card[] hand) {
		if (calcPoints(hand) > 21) {
			return true;
		}
		else {
			return false;
		}
	}
	
//checks if the hand is a blackjack
	public static boolean isBlackjack(Card[] hand) {
		if (hand[0] == UHand[0]) {
			co = userCounter;
		}
		else {
			co = dealCounter;
		}
		if ((calcPoints(hand) == 21) && (hand.length - (hand.length - co) == 2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
//determines whether the dealer should receive more cards
	public static boolean dealerKeepHitting(Card[] hand) {
		if (calcPoints(hand) < 17) {
			return true;
		}
		else {
			return false;
		}
	}
}