package in.chetanmehra.minimum.CardElements;

import java.util.ArrayList;
import java.util.Collections;

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

    public int count() {
        return deck.size();
    }


}
