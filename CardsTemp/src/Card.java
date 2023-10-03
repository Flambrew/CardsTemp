/**
 * Defines a playing card in a standard deck format
 * This class has the necessary methods to check comparisons/equality between instances of itself.
 * @author Andrew Matherne
 */
public class Card implements Comparable<Card> {
	public final int 
			CLUBS = 1, 
			DIAMONDS = 2, 
			HEARTS = 3, 
			SPADES = 4,
			
			ACE = 1, 
			TWO = 2, 
			THREE = 3, 
			FOUR = 4, 
			FIVE = 5, 
			SIX = 6, 
			SEVEN = 7, 
			EIGHT = 8, 
			NINE = 9, 
			TEN = 10, 
			JACK = 11, 
			QUEEN = 12,
			KING = 13;
	
	public final String SUITMAP[] = { 
			"Club", 
			"Diamond", 
			"Heart", 
			"Spade" 
	};
	
	public final String RANKMAP[] = { 
			"Ace", 
			"Two", 
			"Three", 
			"Four", 
			"Five", 
			"Six", 
			"Seven", 
			"Eight", 
			"Nine", 
			"Ten", 
			"Jack", 
			"Queen", 
			"King" 
	};
	
	private int suit, rank;
	
	/**
	 * Returns the suit as an integer
	 * @return suit
	 */
	public int getSuitInt() { return suit; }
	/**
	 * Returns the rank as an integer
	 * @return rank
	 */
	public int getRankInt() { return rank; }
	/**
	 * Returns the suit as a String
	 * @return suit
	 */
	public String getSuitString() { return SUITMAP[suit - 1]; }
	/**
	 * Returns the rank as a String
	 * @return rank
	 */
	public String getRankString() { return RANKMAP[rank - 1]; }
	
	public String toString() { return String.format("%s of %ss", getRankString(), getSuitString()); }
	public int compareTo(Card card) { return (suit - card.suit) * 13 + rank - card.rank; }
	public boolean equals(Object other) { return other instanceof Card && compareTo((Card) other) == 0; }
	
	/**
	 * Creates a default card (Ace of Clubs)
	 */
	public Card() { this(1, 1); }
	/**
	 * Creates a card with a specified suit and rank
	 * @param suit
	 * @param rank
	 */
	public Card(int suit, int rank) { this.suit = suit; this.rank = rank; }
	/**
	 * Creates a card with identical properties to the one passed in
	 * @param other
	 */
	public Card(Card other) { suit = other.suit; rank = other.rank; }
	/**
	 * Creates a card with a specified suit and rank
	 * @param suit
	 * @param rank
	 */
	public Card(int suit, String rank) { this(suit + "", rank); }
	/**
	 * Creates a card with a specified suit and rank
	 * @param suit
	 * @param rank
	 */
	public Card(String suit, int rank) { this(suit, rank + ""); }
	/**
	 * Creates a card with a specified suit and rank
	 * @param suit
	 * @param rank
	 */
	public Card(String suit, String rank) {
		if (suit.matches("CLUBS?|[Cc]lubs?|1")) this.suit = CLUBS;
		else if (suit.matches("DIAMONDS?|[Dd]iamonds?|2")) this.suit = DIAMONDS;
		else if (suit.matches("HEARTS?|[Hh]earts?|3")) this.suit = HEARTS;
		else if (suit.matches("SPADES?|[Ss]pades?|4")) this.suit = SPADES;
		else throw new IllegalArgumentException("Invalid Suit");
		if (rank.matches("1[0-3]|[1-9]")) this.rank = Integer.parseInt(rank);
		else if (rank.matches("ACE|[Aa]ce")) this.rank = ACE;
		else if (rank.matches("TWO|[Tt]wo")) this.rank = TWO;
		else if (rank.matches("THREE|[Tt]hree")) this.rank = THREE;
		else if (rank.matches("FOUR|[Ff]our")) this.rank = FOUR;
		else if (rank.matches("FIVE|[Ff]ive")) this.rank = FIVE;
		else if (rank.matches("SIX|[Ss]ix")) this.rank = SIX;
		else if (rank.matches("SEVEN|[Ss]even")) this.rank = SEVEN;
		else if (rank.matches("EIGHT|[Ee]ight")) this.rank = EIGHT;
		else if (rank.matches("NINE|[Nn]ine")) this.rank = NINE;
		else if (rank.matches("TEN|[Tt]en")) this.rank = TEN;
		else if (rank.matches("JACK|[Jj]ack")) this.rank = JACK;
		else if (rank.matches("QUEEN|[Qq]ueen")) this.rank = QUEEN;
		else if (rank.matches("KING|[Kk]ing")) this.rank = KING;
		else throw new IllegalArgumentException("Invalid Rank");
	}
}