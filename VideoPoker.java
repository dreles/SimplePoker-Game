package PJ4;
import java.awt.event.ActionListener;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. One Pair: one pair of the same card
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the video poker game class.
 * It uses Decks and Card objects to implement video poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */



public class VideoPoker {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,10,25,50,1000};
    private static final String[] goodHandTypes={ 
	  "One Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private final Decks oneDeck;

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance = startingBalance;
    private int playerBet;
    private int playerMult; 
    private int fin;
    private String L = ""; 
    private int repeat = 0;
   

    /** default constructor, set balance = startingBalance */
    public VideoPoker()
    {
	this(startingBalance);
    }

    /** constructor, set given balance */
    public VideoPoker(int balance)
    {
	this.playerBalance= balance;
        oneDeck = new Decks(1, false);
    }

    /** This display the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands()
    {
    	int handsize = 5; 
    	
    	boolean Ace = false, Queen = false , King = false, Jack = false, Ten = false; 
    	boolean samesuit = true;
    	boolean RoyalF = false; 
    	boolean Straight = false; 
    	boolean fourofAK = false; 
    	boolean fullHouse = false;
    	boolean threeoak = false; 
    	boolean onepair = false; 
    	boolean twopair = false; 
    	int fin = 10; 
    	
    	List<Card> tempHand = new ArrayList<Card>(); 
    	
    	try {
			arrangeCards();
		} catch (PlayingCardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
    	 
    	// System.out.println(playerHand);
    	
    	
    		 //check suits for flush(Works) 
    		tempHand.addAll(playerHand);
    		for(int i = 0; i < 4; i++){
    		if(playerHand.get(0).getSuit() != playerHand.get(i).getSuit())
    		{
    			
    			samesuit = false; 
    			
    			
    		}
    		
    		 
    		
    		
    	}
    		
    	//Double check Royal(WOrks)
    		for(int j = 0; j < handsize; j++){
    			int rnk = playerHand.get(j).getRank();  
    			switch(rnk){
    			case 1: Ace = true; 
    			
    			break;
    			
    			case 13: King = true;
    			
    			break;
    			
    			case 12: Queen = true;
    			
    			break;
    			
    			case 11: Jack = true; 
    			
    			break;
    			
    			case 10: Ten = true;
    			
    			break;
    			
    			default: break; 
    			}
    			
    		}
    		
    		//checkRoyalStraightFlush(Works) 
    		if(Ace && King && Queen && Jack && Ten && samesuit){
    			RoyalF = true; 
    			
    		}
    		
    		//checkStraight(Works??)
    		switch(playerHand.get(0).getRank()){
    		case 1:
    			if((playerHand.get(1).getRank() == 2) && (playerHand.get(2).getRank() == 3) && (playerHand.get(3).getRank() == 4) && (playerHand.get(4).getRank() == 5)){
    				Straight = true;
    				
    				break; 
    			}
    			
    		case 2:
    			if((playerHand.get(1).getRank() == 3) && (playerHand.get(2).getRank() == 4) && (playerHand.get(3).getRank() == 5) && (playerHand.get(4).getRank() == 6)){
    				Straight = true;
    				
    				break;
    			}
    			
    		case 3:
    			if((playerHand.get(1).getRank() == 4) && (playerHand.get(2).getRank() == 5) && (playerHand.get(3).getRank() == 6) && (playerHand.get(4).getRank() == 7)){
    				Straight = true;
    				
    				break;
    			}
    			
    		case 4:
    			if((playerHand.get(1).getRank() == 5) && (playerHand.get(2).getRank() == 6) && (playerHand.get(3).getRank() == 7) && (playerHand.get(4).getRank() == 8)){
				Straight = true;
				
				break;
			}
    			
    		case 5:
    			if((playerHand.get(1).getRank() == 6) && (playerHand.get(2).getRank() == 7) && (playerHand.get(3).getRank() == 8) && (playerHand.get(4).getRank() == 9)){
    				Straight = true;
    				
    				break;
    			}
    			
    		case 6: 
    			if((playerHand.get(1).getRank() == 7) && (playerHand.get(2).getRank() == 8) && (playerHand.get(3).getRank() == 9) && (playerHand.get(4).getRank() == 10)){
    				Straight = true;
    				
    				break;
    			}
    			
    		case 7: 
    			if((playerHand.get(1).getRank() == 8) && (playerHand.get(2).getRank() == 9) && (playerHand.get(3).getRank() == 10) && (playerHand.get(4).getRank() == 11)){
    				Straight = true;
    				
    				break;
    			}
    			
    		case 8:
    			if((playerHand.get(1).getRank() == 9) && (playerHand.get(2).getRank() == 10) && (playerHand.get(3).getRank() == 11) && (playerHand.get(4).getRank() == 12)){
    				Straight = true;
    				
    				break;
    			}
    		case 9:
    			if((playerHand.get(1).getRank() == 10) && (playerHand.get(2).getRank() == 11) && (playerHand.get(3).getRank() == 12) && (playerHand.get(4).getRank() == 13)){
    				Straight = true;
    				
    				break;
    			}
    		case 10:
    			if((playerHand.get(1).getRank() == 11) && (playerHand.get(2).getRank() == 12) && (playerHand.get(3).getRank() == 13) && (playerHand.get(4).getRank() == 1)){
    				Straight = true;
    				
    				break;
    			}
    		default: break; 	
    		
    		}
    		
    		//check4ofaKnd(Works)
    		if((playerHand.get(0).getRank() == playerHand.get(1).getRank()) && ( playerHand.get(1).getRank() == playerHand.get(2).getRank()) && (playerHand.get(2).getRank() == playerHand.get(3).getRank())){
    			fourofAK = true; 
    			
    		}
    		
    		if((playerHand.get(1).getRank() == playerHand.get(2).getRank()) && ( playerHand.get(2).getRank() == playerHand.get(3).getRank()) && (playerHand.get(3).getRank() == playerHand.get(4).getRank())){
    			fourofAK = true; 
    		}
    		
    		//checkFullHouse(Works)
    		if(playerHand.get(0).getRank() == playerHand.get(1).getRank()){
    			if(playerHand.get(2).getRank() == playerHand.get(3).getRank() && playerHand.get(3).getRank() == playerHand.get(4).getRank()){
    				fullHouse = true; 
    				
    			}
    		}
    		
    		if(playerHand.get(3).getRank() == playerHand.get(4).getRank()){
    			if(playerHand.get(0).getRank() == playerHand.get(1).getRank() && playerHand.get(1).getRank() == playerHand.get(2).getRank()){
    				fullHouse = true; 
    				
    			}
    		}
    		
    	//check3ofaKind(Works)
    		if(playerHand.get(2).getRank() == playerHand.get(3).getRank() && playerHand.get(3).getRank() == playerHand.get(4).getRank()){
    			threeoak = true; 
    			
    		}
    		

    		if(playerHand.get(0).getRank() == playerHand.get(1).getRank() && playerHand.get(1).getRank() == playerHand.get(2).getRank()){
    			threeoak = true; 
    			
    		}


    		if(playerHand.get(1).getRank() == playerHand.get(2).getRank() && playerHand.get(2).getRank() == playerHand.get(3).getRank()){
				threeoak = true; 
				
    		}
    		
    		
    	///check how many pairs(Works) 
    		
    		int pair = 0; 
    		for(int i = 0; i < 4; i++){
    			if((playerHand.get(i).getRank() == playerHand.get(i+1).getRank()) && !threeoak && !fourofAK ){
    				pair++; 
    				
    			}
    		}
    		
    		if(pair == 1){
    			onepair = true;
    			
    		}
    		
    		if(pair == 2){
    			twopair = true; 
    			
    		}
    		
    		
    	//set hand type	
    	if(onepair == true){
    		fin = 0; 
    	}
    	
    	if(twopair == true){
    		fin = 1;
    	}
    	
    	if(threeoak == true){
    		fin = 2;
    	}
    	
    	if(Straight == true){
    		fin = 3;
    	}
    	
    	if(samesuit == true){
    		fin = 4;
    	}
    	
    	if(fullHouse == true){
    		fin = 5;
    	}
    	
    	if(fourofAK == true){
    		fin = 6;
    	}
    	
    	if(Straight && samesuit){
    		fin = 7;
    	}
   
    	if(RoyalF == true){
    		fin = 8; 
	   }
    
    	
    	if(fin <= 8){
    		
    			System.out.println(goodHandTypes[fin] + "!");
    			playerMult = multipliers[fin];
    	}
    	else{
    		System.out.println("Sorry you lost");
    		playerMult = 0; 
    		L = "L"; 
    	}
    		
    	
    		 
   
    	
        // implement this method!
    }

    /*************************************************
     *   add new private methods here ....
     * @throws PlayingCardException 
     *
     *************************************************/
    private void arrangeCards() throws PlayingCardException{
    	
    	for(int i = 0 ; i < 4; i++ ){
    			if(playerHand.get(i).getRank() > playerHand.get(i+1).getRank()){
    				Card tempCard = new Card(playerHand.get(i).getSuit(), playerHand.get(i).getRank());
    				
    				playerHand.remove(i);
    				
    			 playerHand.add(i+1, tempCard);
    			 
    			 i = -1;
    			
    			 
    			}
    		}
    }
    


    public void play() 
    {
    /** The main algorithm for single player poker game 
     *
     * Steps:
     * 		showPayoutTable()
     *		
     * 		++	
     * 		show balance, get bet 
     *		verify bet value, update balance
     *		reset deck, shuffle deck, 
     *		deal cards and display cards
     *		ask for positions of cards to replace 
     *          get positions in one input line
     *		update cards
     *		check hands, display proper messages
     *		update balance if there is a payout
     *		if balance = O:
     *			end of program 
     *		else
     *			ask if the player wants to play a new game
     *			if the answer is "no" : end of program
     *			else : showPayoutTable() if user wants to see it
     *			goto ++
     */
    	if(repeat < 1){
    	showPayoutTable();
    	}
    	
    	System.out.println("Balance: " + playerBalance);
    	
    	 Scanner sc = new Scanner(System.in);
    	 
    	 System.out.println("Enter Bet: ");
    	 
    	  playerBet = sc.nextInt(); 
    	  
    	  if(playerBet > playerBalance){
    		  System.out.println("Bet is too large");
    		  play();
    	  }
    	  
    	  System.out.println("Bet is " + playerBet);
    	  
    	  oneDeck.reset();
    	  
    	  oneDeck.shuffle();
    	  
    	  try {
			playerHand = (oneDeck.deal(5));
			
			 
		} catch (PlayingCardException e) {
			
			e.printStackTrace();
		} 
    	   System.out.println(playerHand);
    	   
    	   System.out.println("Enter positions of cards to replace: ");
    	   
    	   sc = new Scanner(System.in); 
    	   
    	   String in = sc.nextLine();
    	   
    	   sc = new Scanner(in); 
    	   
    	   
    	   int dec = 1 ;
    	   while (sc.hasNextInt()){
    		     
    	         int rep = (sc.nextInt() - dec);
    	         
    	         playerHand.remove(rep);
    	         
    	         dec++; 
    	         
    	       
    	         }  
    	  
    	   List <Card> newHand = null; 
    	   while(playerHand.size() < 5){
    		   
    		   try {
    			   oneDeck.shuffle();
				newHand = oneDeck.deal(1);
				playerHand.add(newHand.get(0));
				
				
			} catch (PlayingCardException e) {
				
				e.printStackTrace();
			} 
    	   }
    	   
    	   System.out.println(playerHand);
    	   checkHands();
    	        
    	      
    	         
    	 if(fin <= 8){
    		 playerBalance = ((playerBet * playerMult) + playerBalance); 
    	 }
    	 if(L.equals("L")){
    		 
    		 playerBalance = (playerBalance - playerBet); 
    	 }
    	  if(playerBalance == 0){
    		  System.out.println("Game Over");
    		  System.exit(0); 
    	  }
    	 System.out.println("Your Balance:" + playerBalance + " one more game (y or n)?");
    	 
    	 sc = new Scanner(System.in); 
    	 String quit = sc.nextLine();
    	 
    	 if(quit.equals("y")){
    		 repeat++;
    		 System.out.println("Want to see payout table (y or n)");
    		 String table = sc.nextLine();
    		 if(table.equals("y")){
    			 showPayoutTable(); 
    		 }
    		 play(); 
    	 }
    	 else{
    		 System.out.println("Bye!");
    		 System.exit(0);
    	 }
    	 

        // implement this method!

    }

    /*************************************************
     *   Do not modify methods below
    /*************************************************

    /** testCheckHands() is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 

    public void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(3,1));
		playerHand.add(new Card(3,10));
		playerHand.add(new Card(3,12));
		playerHand.add(new Card(3,11));
		playerHand.add(new Card(3,13));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(3,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(1,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(3,5));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(4,8));
		playerHand.add(new Card(1,8));
		playerHand.add(new Card(4,12));
		playerHand.add(new Card(2,8));
		playerHand.add(new Card(3,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(4,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(2,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(2,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set One Pair
		playerHand.set(0, new Card(2,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set One Pair
		playerHand.set(2, new Card(4,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set no Pair
		playerHand.set(2, new Card(4,6));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
      	
    }

    /* Quick testCheckHands() */
    	
    	
    public static void main(String args[]) 
    {
	VideoPoker pokergame = new VideoPoker();
	pokergame.testCheckHands(); 
	
	
	
    	
    }
   
    	
}
