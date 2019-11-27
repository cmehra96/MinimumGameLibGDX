package in.chetanmehra.minimum.CardElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import in.chetanmehra.minimum.GameHelpers.Assests;

public class Deck {

    private Assests assets;
    private ArrayList<Card> deck;

    public Deck(Assests assests) {
        this.assets = assests;
        deck = new ArrayList<>();
    }

    public Deck(ArrayList<Card> deck, Assests assests) {
        this.assets = assests;
        this.deck = deck;
    }

    public Deck(Deck otherDeck) {
        for (Card card : otherDeck.getDeck()) {
            add(card);
        }
    }

    /**
     * Method to add card in the deck
     *
     * @param card
     */

    public void add(Card card) {
        if (deck.contains(card) == false)      //To stop same card adding twice while long touch event
            deck.add(card);
    }

    public void allocateDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                add(new Card(rank, suit, assets));
            }
        }
        // shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);

    }

    public int getSize() {
        return deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * @param index
     * @return Element at index value
     */

    public Card getCardByIndex(int index) {
        if (index < 0 || index >= this.deck.size()) {
            return null;
        }
        return (Card) this.deck.get(index);
    }

    /**
     * Method to remove a top card from deck
     *
     * @return Removed card from the deck.
     */
    public Card deal() {
        int cardcount = count();
        if (cardcount == 0)
            return null;
        Card dealcards = deck.get(cardcount - 1);
        deck.remove(cardcount - 1);
        return dealcards;


    }

    /**
     * Method to get Top card of deck
     *
     * @return top card of deck
     */
    public Card getTopCard() {
        if (count() == 0)
            return null;
        return deck.get(count() - 1);
    }

    /**
     * Method to remove top card from deck
     *
     * @return top card of deck
     */
    public Card removeTopCard() {
        Card topCard = getTopCard();
        if (topCard == null) {
            return null;
        }
        deck.remove(count() - 1);
        return topCard;
    }

    public int count() {
        return deck.size();
    }


    public Card removeCard(Card card) {
        int index = deck.indexOf(card);
        return deck.remove(index);


    }

    /**
     * Method to sort deck based on suit
     * in descending order.
     * Order will be
     * Diamond- Highest
     * Hearts
     * Spades
     * Club- Lowest
     */
    public void sortBySuitsDesc() {
        Collections.sort(deck, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return rhs.compareTo(lhs);
            }
        });
    }


    /**
     * compare Cards by their rank, order same as sortBySuit method
     */
    public void sortByRankDesc() {
        Collections.sort(deck, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return rhs.cardRank() - lhs.cardRank();
            }
        });
    }

    public void sortByRankAsc() {
        Collections.sort(deck, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return lhs.cardRank() - rhs.cardRank();
            }
        });
    }

    public void clear() {
        deck.clear();
    }
}
