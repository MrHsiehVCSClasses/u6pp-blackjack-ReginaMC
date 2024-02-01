package u6pp;

class Main {
  public static void main(String[] args) {  
    System.out.println("You have just ran the main function.\n" +
    "Uncomment the lines in Main.java to test out your blackjack/yahtzee games.\n");

    // uncomment to play blackjack
    //Blackjack blackjack = new Blackjack();
    //blackjack.play();
    
    // uncomment to play yahtzee
    //Yahtzee yahtzee = new Yahtzee();
    //yahtzee.play();
    Deck a = new Deck();

    Blackjack b = new Blackjack();
//    for(int x=0; x<52; x++) {
//    	System.out.println(b.xx[x]);
//    
     	b.play();
  
    
  }
}