package PJ4;

import java.awt.event.ActionListener;
import java.util.*;

public class VideoPoker {

	private static final int startingBalance = 100;
	private static final int numberOfCards = 5;

	private static final int[] multipliers = { 1, 2, 3, 5, 6, 10, 25, 50, 1000 };
	private static final String[] goodHandTypes = { "One Pair", "Two Pairs", "Three of a Kind", "Straight", "Flush	",
			"Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

	// must use only one deck
	private final Decks oneDeck;

	private List<Card> playerHand;
	private int playerBalance = startingBalance;
	private int playerBet;
	private int playerMult;
	private int fin;
	private String L = "";
	private int repeat = 0;

	/** default constructor, set balance = startingBalance */
	public VideoPoker() {
		this(startingBalance);
	}

	/** given balance */
	public VideoPoker(int balance) {
		this.playerBalance = balance;
		oneDeck = new Decks(1, false);
	}

	/**
	 * This display the payout table based on multipliers and goodHandTypes
	 * arrays
	 */
	private void showPayoutTable() {
		System.out.println("\n\n");
		System.out.println("Payout Table   	      Multiplier   ");
		System.out.println("=======================================");
		int size = multipliers.length;
		for (int i = size - 1; i >= 0; i--) {
			System.out.println(goodHandTypes[i] + "\t|\t" + multipliers[i]);
		}
		System.out.println("\n\n");
	}

	private void checkHands() {
		int handsize = 5;

		boolean Ace = false, Queen = false, King = false, Jack = false, Ten = false;
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

			e.printStackTrace();
		}

		// check suits for flush
		tempHand.addAll(playerHand);
		for (int i = 0; i < 4; i++) {
			if (playerHand.get(0).getSuit() != playerHand.get(i).getSuit()) {

				samesuit = false;

			}

		}
		// Check for a Royal Flush

		for (int j = 0; j < handsize; j++) {
			int rnk = playerHand.get(j).getRank();
			switch (rnk) {
			case 1:
				Ace = true;

				break;

			case 13:
				King = true;

				break;

			case 12:
				Queen = true;

				break;

			case 11:
				Jack = true;

				break;

			case 10:
				Ten = true;

				break;

			default:
				break;
			}

		}

		// checkRoyalStraightFlush
		if (Ace && King && Queen && Jack && Ten && samesuit) {
			RoyalF = true;

		}

		// checkStraight
		switch (playerHand.get(0).getRank()) {
		case 1:
			if ((playerHand.get(1).getRank() == 2) && (playerHand.get(2).getRank() == 3)
					&& (playerHand.get(3).getRank() == 4) && (playerHand.get(4).getRank() == 5)) {
				Straight = true;

				break;
			}

		case 2:
			if ((playerHand.get(1).getRank() == 3) && (playerHand.get(2).getRank() == 4)
					&& (playerHand.get(3).getRank() == 5) && (playerHand.get(4).getRank() == 6)) {
				Straight = true;

				break;
			}

		case 3:
			if ((playerHand.get(1).getRank() == 4) && (playerHand.get(2).getRank() == 5)
					&& (playerHand.get(3).getRank() == 6) && (playerHand.get(4).getRank() == 7)) {
				Straight = true;

				break;
			}

		case 4:
			if ((playerHand.get(1).getRank() == 5) && (playerHand.get(2).getRank() == 6)
					&& (playerHand.get(3).getRank() == 7) && (playerHand.get(4).getRank() == 8)) {
				Straight = true;

				break;
			}

		case 5:
			if ((playerHand.get(1).getRank() == 6) && (playerHand.get(2).getRank() == 7)
					&& (playerHand.get(3).getRank() == 8) && (playerHand.get(4).getRank() == 9)) {
				Straight = true;

				break;
			}

		case 6:
			if ((playerHand.get(1).getRank() == 7) && (playerHand.get(2).getRank() == 8)
					&& (playerHand.get(3).getRank() == 9) && (playerHand.get(4).getRank() == 10)) {
				Straight = true;

				break;
			}

		case 7:
			if ((playerHand.get(1).getRank() == 8) && (playerHand.get(2).getRank() == 9)
					&& (playerHand.get(3).getRank() == 10) && (playerHand.get(4).getRank() == 11)) {
				Straight = true;

				break;
			}

		case 8:
			if ((playerHand.get(1).getRank() == 9) && (playerHand.get(2).getRank() == 10)
					&& (playerHand.get(3).getRank() == 11) && (playerHand.get(4).getRank() == 12)) {
				Straight = true;

				break;
			}
		case 9:
			if ((playerHand.get(1).getRank() == 10) && (playerHand.get(2).getRank() == 11)
					&& (playerHand.get(3).getRank() == 12) && (playerHand.get(4).getRank() == 13)) {
				Straight = true;

				break;
			}
		case 10:
			if ((playerHand.get(1).getRank() == 11) && (playerHand.get(2).getRank() == 12)
					&& (playerHand.get(3).getRank() == 13) && (playerHand.get(4).getRank() == 1)) {
				Straight = true;

				break;
			}
		default:
			break;

		}

		// check 4 of a Kind
		if ((playerHand.get(0).getRank() == playerHand.get(1).getRank())
				&& (playerHand.get(1).getRank() == playerHand.get(2).getRank())
				&& (playerHand.get(2).getRank() == playerHand.get(3).getRank())) {
			fourofAK = true;

		}

		if ((playerHand.get(1).getRank() == playerHand.get(2).getRank())
				&& (playerHand.get(2).getRank() == playerHand.get(3).getRank())
				&& (playerHand.get(3).getRank() == playerHand.get(4).getRank())) {
			fourofAK = true;
		}

		// checkFullHouse
		if (playerHand.get(0).getRank() == playerHand.get(1).getRank()) {
			if (playerHand.get(2).getRank() == playerHand.get(3).getRank()
					&& playerHand.get(3).getRank() == playerHand.get(4).getRank()) {
				fullHouse = true;

			}
		}

		if (playerHand.get(3).getRank() == playerHand.get(4).getRank()) {
			if (playerHand.get(0).getRank() == playerHand.get(1).getRank()
					&& playerHand.get(1).getRank() == playerHand.get(2).getRank()) {
				fullHouse = true;

			}
		}

		// check3ofaKind
		if (playerHand.get(2).getRank() == playerHand.get(3).getRank()
				&& playerHand.get(3).getRank() == playerHand.get(4).getRank()) {
			threeoak = true;

		}

		if (playerHand.get(0).getRank() == playerHand.get(1).getRank()
				&& playerHand.get(1).getRank() == playerHand.get(2).getRank()) {
			threeoak = true;

		}

		if (playerHand.get(1).getRank() == playerHand.get(2).getRank()
				&& playerHand.get(2).getRank() == playerHand.get(3).getRank()) {
			threeoak = true;

		}

		/// check how many pairs()

		int pair = 0;
		for (int i = 0; i < 4; i++) {
			if ((playerHand.get(i).getRank() == playerHand.get(i + 1).getRank()) && !threeoak && !fourofAK) {
				pair++;

			}
		}

		if (pair == 1) {
			onepair = true;

		}

		if (pair == 2) {
			twopair = true;

		}

		// set hand type
		if (onepair == true) {
			fin = 0;
		}

		if (twopair == true) {
			fin = 1;
		}

		if (threeoak == true) {
			fin = 2;
		}

		if (Straight == true) {
			fin = 3;
		}

		if (samesuit == true) {
			fin = 4;
		}

		if (fullHouse == true) {
			fin = 5;
		}

		if (fourofAK == true) {
			fin = 6;
		}

		if (Straight && samesuit) {
			fin = 7;
		}

		if (RoyalF == true) {
			fin = 8;
		}

		if (fin <= 8) {

			System.out.println(goodHandTypes[fin] + "!");
			playerMult = multipliers[fin];
		} else {
			System.out.println("Sorry you lost");
			playerMult = 0;
			L = "L";
		}

	}

	// arrange the cards in proper order to be checked//

	private void arrangeCards() throws PlayingCardException {

		for (int i = 0; i < 4; i++) {
			if (playerHand.get(i).getRank() > playerHand.get(i + 1).getRank()) {
				Card tempCard = new Card(playerHand.get(i).getSuit(), playerHand.get(i).getRank());

				playerHand.remove(i);

				playerHand.add(i + 1, tempCard);

				i = -1;

			}
		}
	}

	public void play() {
//if first time running game show payout table 
		if (repeat < 1) {
			showPayoutTable();
		}
//shoe players balance 
		System.out.println("Balance: " + playerBalance);

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Bet: ");

		playerBet = sc.nextInt();
		
//make sure the players bet is within their monetary range before proceeding 
		if (playerBet > playerBalance) {
			System.out.println("Bet is too large");
			play();
		}

		System.out.println("Bet is " + playerBet);
//start with a fresh shuffled deck of cards 
		oneDeck.reset();

		oneDeck.shuffle();
//deal cards to player 
		try {
			playerHand = (oneDeck.deal(5));

		} catch (PlayingCardException e) {

			e.printStackTrace();
		}
	//show players cards
		System.out.println(playerHand);

		System.out.println("Enter positions of cards to replace: ");

		sc = new Scanner(System.in);

		String in = sc.nextLine();

		sc = new Scanner(in);
//remove unwanted cards from player's hand. 
		int dec = 1;
		while (sc.hasNextInt()) {

			int rep = (sc.nextInt() - dec);

			playerHand.remove(rep);

			dec++;

		}
//create new hand from cards left in the players hand
		List<Card> newHand = null;
		while (playerHand.size() < 5) {

			try {
				oneDeck.shuffle();
				newHand = oneDeck.deal(1);
				playerHand.add(newHand.get(0));

			} catch (PlayingCardException e) {

				e.printStackTrace();
			}
		}
//show new hand and check if player won or lost  
		System.out.println(playerHand);
		checkHands();
//check if player wants to play again or has enough money to continue playing
		if (fin <= 8) {
			playerBalance = ((playerBet * playerMult) + playerBalance);
		}
		if (L.equals("L")) {

			playerBalance = (playerBalance - playerBet);
		}
		if (playerBalance == 0) {
			System.out.println("Game Over");
			System.exit(0);
		}
		System.out.println("Your Balance:" + playerBalance + " one more game (y or n)?");

		sc = new Scanner(System.in);
		String quit = sc.nextLine();

		if (quit.equals("y")) {
			repeat++;
			System.out.println("Want to see payout table (y or n)");
			String table = sc.nextLine();
			if (table.equals("y")) {
				showPayoutTable();
			}
			play();
		} else {
			System.out.println("Bye!");
			System.exit(0);
		}

	}

}
