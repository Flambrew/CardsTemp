/**
 * Defines a Deck of standard format playing cards.
 * Contains methods for shuffling, sorting, drawing, and distributing playing cards.
 * @author Andrew Matherne
 */
public class Deck {
	private Card[] deck;
	private int limit;
	
	/**
	 * Returns the index of the top card in the deck
	 * @return the index of the top card in the deck
	 */
	public int getTopCard() { return limit - 1; }
	
	/**
	 * Returns the deck contents
	 * @return the deck contents
	 */
	public Card[] deck() { return deck; }
	
	public String toString() {
		String out = "";
		if (limit == 52) {
			out += "Clubs\t\tDiamonds\tHearts\t\tSpades\n";
			out += "--------------------------------------------------------\n";
			
			Card[][] suits = new Card[4][13];
			int[] suit = new int[4];
			for (Card card : deck) suits[card.getSuitInt()-1][suit[card.getSuitInt()-1]++] = card;
			
			for (int i = 0; i < 13; ++i) {
				for (int j = 0; j < 4; ++j) {
					out += suits[j][i].getRankString() + "\t\t";
				}
				out = out.substring(0, out.length() - 2) + "\n";
			}
		} else {
			out += "Deck\n--------\n";
			
			for (int i = 0; i < limit; ++i) {
				out += i + 1 + ". " + deck[i].toString() + "\n";
			}
		}
		return out;
	}
	
	public boolean equals(Deck other) {
		for (int i = 0; i < limit; ++i) if (!deck[i].equals(other.deck[i])) return false; 
		return true;
	}

	/**
	 * Creates an empty deck with a specified length and top card position
	 * @param len
	 * @param top
	 */
	public Deck(int len, int limit) { deck = new Card[len]; this.limit = limit; }
	/**
	 * Creates a standard sorted deck of cards
	 */
	public Deck() { this(52, 52); for (int i = 0; i < 52; ++i) deck[i] = new Card(i / 13 + 1, i % 13 + 1); }
	/**
	 * Creates a standard deck of cards with an optional initial shuffle
	 * @param shuffled
	 */
	public Deck(boolean sorted) { this(); if (!sorted) shuffle(); }
	/**
	 * Creates a deck identical in properties (and with duplicated cards) to the one passed in
	 * @param other
	 */
	public Deck(Deck other) {
		this(other.deck.length, other.limit);
		for (int i = 0; i < limit; ++i) deck[i] = new Card(other.deck[i]);
	}
	
	/**
	 * Shuffles the deck using the Fisher-Yates shuffling method
	 */
	public void shuffle() {	
		for (int i = 0; i < limit; ++i) 
			swap(deck, i, i + (int)(Math.random() * (limit - i))); 
	}
	
	/**
	 * Returns the top card on the deck
	 * @return the top card on the deck
	 */
	public Card draw() { return deck[--limit]; }
	
	/**
	 * Returns a random card from within the deck
	 * Under the hood, it is moved to the index right past the top, so as to maintain cohesive drawing history
	 * @return a random card from within deck
	 */
	public Card pick() { 
		int rand = (int)(Math.random() * limit);
		Card out = deck[rand];
		for (int i = rand; i < limit - 1; ++i) 
			deck[i] = deck[i + 1];
		return (deck[--limit] = out);
	}
	
	/**
	 * Returns an array of decks representing each dealt hand
	 * @param hands
	 * @param cardCount
	 * @return
	 */
	public Deck[] deal(int hands, int cardCount) {
		if (hands * cardCount > limit) return null;
		Deck[] out = new Deck[hands];
		for (int i = 0; i < hands; ++i) {
			out[i] = new Deck(cardCount, cardCount);
		}
		for (int i = 0; i < cardCount; ++i) {
			for (int j = 0; j < hands; ++j) 
				out[j].deck[i] = draw();
		}
		return out;
	}
	
	/**
	 * Sorts the deck using selection sort
	 */
	public void selectionSort() {
		for (int i = 0, mindex = 0; i < deck.length; mindex = ++i) {
			for (int j = i + 1; j < deck.length; ++j) 
				if (deck[j].compareTo(deck[mindex]) < 0) mindex = j;
			swap(deck, i, mindex);
		}
	}
	
	/**
	 * Sorts the deck using mergesort
	 */
	public void mergesort() { mergesort(0, deck.length); }
	private void mergesort(int begindex, int endex) {
		int len = endex - begindex, midpoint = begindex + len / 2;
		if (len <= 1) {	return;	}
		
		mergesort(begindex, midpoint);
		mergesort(midpoint, endex);
		
		Card[] tempOut = new Card[len];
		int i = begindex, j = midpoint, n = 0;;
		while (i < midpoint && j < endex) {
			if (deck[i].compareTo(deck[j]) < 0) {
				tempOut[n++] = deck[i++];
			} else {
				tempOut[n++] = deck[j++];
			}
		}
		
		while (i < midpoint) {
			tempOut[n++] = deck[i++];
		}
		
		while (j < endex) {
			tempOut[n++] = deck[j++];
		}
		
		for (i = 0; i < len; ++i) {
			deck[begindex + i] = tempOut[i];
		}
	}
	
	/**
	 * Swaps two cards in a given deck
	 * @param deck 
	 * @param i
	 * @param j
	 */
	public static void swap(Card[] deck, int i, int j) {
		Card temp = deck[i];
		deck[i] = deck[j];
		deck[j] = temp;
	}
	
	// not part of the submission - was just playing with the code this morning
	public static Deck stack(Deck a, Deck b) {
		Deck out = new Deck(a.limit + b.limit, a.limit + b.limit);
		for (int i = out.getTopCard(); i >= 0; --i) {
			if (i >= a.limit) {
				out.deck[i] = b.draw();
			} else {
				out.deck[i] = a.draw();
			}
		}
		return out;
	}
}