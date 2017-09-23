package PJ4;

import java.util.*;



class PlayingCardException extends Exception {

    /* Constructor to create a PlayingCardException object */
    PlayingCardException (){
		super ();
    }

    PlayingCardException ( String reason ){
		super ( reason );
    }
}



//=================================================================================

class Card {
	
    /* constant suits and ranks */
    static final String[] Suit = {"Joker","Clubs", "Diamonds", "Hearts", "Spades" };
    static final String[] Rank = {"","A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    /* Data field of a card: rank and suit */
    private int cardRank;  /* values: 1-13 (see Rank[] above) */
    private int cardSuit;  /* values: 0-4  (see Suit[] above) */

    /* Constructor to create a card */
    /* throw PlayingCardException if rank or suit is invalid */
    public Card(int suit, int rank) throws PlayingCardException { 

        // suit =0 is joker, rank must be 1 or 2
	if (suit==0) {
	    if ((rank <1) || (rank >2))
	       throw new PlayingCardException("Invalid rank for Joker:"+rank);
	    cardRank=rank;
	    cardSuit=0;
        } else {

	    if ((rank < 1) || (rank > 13))
		throw new PlayingCardException("Invalid rank:"+rank);
	    else
        	cardRank = rank;

	    if ((suit < 1) || (suit > 4))
		throw new PlayingCardException("Invalid suit:"+suit);
	    else
        	cardSuit = suit;
   	}
    }

    /* Accessor and toString */
    /* You may impelemnt equals(), but it will not be used */
    public int getRank() { return cardRank; }
    public int getSuit() { return cardSuit; }
    public String toString() { 
	if (cardSuit == 0) return Suit[cardSuit]+" #"+cardRank;
        else return Rank[cardRank] + " " + Suit[cardSuit]; 
    }

    
    /* Few quick tests here */
    public static void main(String args[])
    {
	try {
	    Card c1 = new Card(4,1);    // A Spades
	    System.out.println(c1);
	    c1 = new Card(1,10);	// 10 Clubs
	    System.out.println(c1);
	    c1 = new Card(0,2);		// Joker #2
	    System.out.println(c1);
	    c1 = new Card(5,10);        // generate exception here
	}
	catch (PlayingCardException e)
	{
	    System.out.println("PlayingCardException: "+e.getMessage());
	}
    }
}



//=================================================================================


class Decks {

    /* this is used to keep track of original n*52 or n*54 cards */
    private List<Card> originalDecks = new ArrayList<Card>(); ;   

    /* this starts with copying cards from originalDecks */
    /* it is used to play the card game                  */
    /* see reset(): resets gameDecks to originalDecks    */
    private List<Card> gameDecks = new ArrayList<Card>();

    /* number of decks in this object */
    private int numberDecks;
    private boolean withJokers;


   
    public Decks(boolean withJokers)
    {
    	  
    	if(!withJokers){
    		
  
    //ADD cards (rank)2-A into deck
        for(int i = 1; i <= 4; i++){
    //for (suit) add all rank cards
        for(int j = 1; j <= 13; j++){
        	try{
        	Card c1 = new Card(i,j);
        	originalDecks.add(c1); 
        	}
        	catch (PlayingCardException e)
        	{
        	    System.out.println("PlayingCardException: "+e.getMessage());
        	}
        	
        	
        }
       
        }
        
        gameDecks.addAll(originalDecks);
    	// implement this method!
    }
    	else{

    	    //ADD cards (rank)2-A into deck
    	        for(int i = 0; i <= 4; i++){
    	        	
    	        	if(i == 0){
    	        		try{
    	        		Card c1 = new Card(0,1);
    	        		Card c2 = new Card(0,2); 
    	        		originalDecks.add(c1);
    	        		originalDecks.add(c2); 
    	        		i++;
    	        		}
    	        		catch(PlayingCardException e){
    	        			System.out.println("PlayingCardException: "+e.getMessage());
    	        		}
    	        	}
    	    //for (suit) add all rank cards
    	        for(int j = 1; j <= 13; j++){
    	        	try{
    	        	Card c1 = new Card(i,j);
    	        	originalDecks.add(c1); 
    	        	}
    	        	catch (PlayingCardException e)
    	        	{
    	        	    System.out.println("PlayingCardException: "+e.getMessage());
    	        	}
    	        	
    	        	
    	        }
    	        
    	        }
    	        gameDecks.addAll(originalDecks);	
    	}
    	
    	}
    
  
    public Decks(int n, boolean withJokers)
    {
    	
    	 numberDecks = 0; 
    	if(!withJokers){
    		while(numberDecks < n){
    		  
    	    //ADD cards (rank)2-A into deck
    	        for(int i = 1; i <= 4; i++){
    	    //for (suit) add all rank cards
    	        for(int j = 1; j <= 13; j++){
    	        	try{
    	        	Card c1 = new Card(i,j);
    	        	originalDecks.add(c1); 
    	        	}
    	        	catch (PlayingCardException e)
    	        	{
    	        	    System.out.println("PlayingCardException: "+e.getMessage());
    	        	}
    	        	
    	        	
    	        }
    	       
    	        }
    	        
    	        
    	    numberDecks++; 	// implement this method!
    	    }
    		gameDecks.addAll(originalDecks);
    	}
    	    	else{
    	    		while(numberDecks < n){

    	    	    //ADD cards (rank)2-A into deck
    	    	        for(int i = 0; i <= 4; i++){
    	    	        	
    	    	        	if(i == 0){
    	    	        		try{
    	    	        		Card c1 = new Card(0,1);
    	    	        		Card c2 = new Card(0,2); 
    	    	        	
    	    	        		originalDecks.add(c1);
    	    	        		originalDecks.add(c2); 
    	    	        		
    	    	        		i++;
    	    	        		}
    	    	        		catch(PlayingCardException e){
    	    	        			System.out.println("PlayingCardException: "+e.getMessage());
    	    	        		}
    	    	        	}
    	    	    //for (suit) add all rank cards
    	    	        for(int j = 1; j <= 13; j++){
    	    	        	try{
    	    	        	Card c1 = new Card(i,j);
    	    	        	originalDecks.add(c1); 
    	    	        	}
    	    	        	catch (PlayingCardException e)
    	    	        	{
    	    	        	    System.out.println("PlayingCardException: "+e.getMessage());
    	    	        	}
    	    	        	
    	    	        	
    	    	        }
    	    	        
    	    	        }
    	    		numberDecks++;
    	    		}
    	    		gameDecks.addAll(originalDecks);
    	    	}
        // implement this method!
    }


   
    public void shuffle()
    {
    	Collections.shuffle(gameDecks);
        // implement this method!
    	
    }

   
    public List<Card> deal(int numberCards) throws PlayingCardException
    {
        // implement this method!
    	List<Card> temp = new ArrayList();
    	for(int i = 0; i < numberCards; i++){
    		if(gameDecks.isEmpty()){
    			throw new PlayingCardException("Not Enough Cards to deal"); 
    		}
    		temp.add(gameDecks.get(i));
    		
    	}
for(int i = 0; i < numberCards; i++){
    		if(gameDecks.size() <= i){
    			gameDecks.clear();
    		}
    		else{
    		gameDecks.remove(i);
    		}
    	}
    	
    	
    	

    		
    		
    			
    		        
    

    	return temp;
    }

    
    public void reset()
    {
        gameDecks.clear();
        gameDecks.addAll(originalDecks);

    }

   
    public int getNumberDecks()
    {
	return numberDecks;
    }

    public boolean getWithJokers()
    {
	return withJokers;
    }

    
    public int remainSize()
    {
	return gameDecks.size();
    }

    
    public String toString()
    {
	return ""+gameDecks;
    }


  

}
