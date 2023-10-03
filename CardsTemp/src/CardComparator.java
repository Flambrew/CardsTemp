import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
	public int compare(Card a, Card b) {
		return a.compareTo(b);
	}
}