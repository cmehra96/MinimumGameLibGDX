package in.chetanmehra.minimum.CardElements;

import com.badlogic.gdx.graphics.g2d.Sprite;

import in.chetanmehra.minimum.GameHelpers.Assests;

public class Card extends Sprite implements Comparable {
    private Rank cardValue;
    private Suit suit;
    private Assests assests;

    public Card(Rank cardValue, Suit suit, Assests assests) {
        this.cardValue = cardValue;
        this.suit = suit;
        this.assests = assests;
        setSize(70.0f, 100.0f);
    }

    public void setCardToShowFront() {
        setRegion(assests.manager.get(Assests.cards.get(suit.getIndex() * 13 + cardValue.getIndex())));
    }

    public void setCardToShowBack() {
        setRegion(assests.manager.get(Assests.cardBackImage));
    }

    public Rank getCardValue() {
        return cardValue;
    }

    public void setCardValue(Rank cardValue) {
        this.cardValue = cardValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Assests getAssests() {
        return assests;
    }

    public void setAssests(Assests assests) {
        this.assests = assests;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return suit.getValue() * 13 + cardValue.getRank();
    }

    @Override
    public int compareTo(Object o) {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    public int cardRank() {
        return cardValue.getRank();
    }
}
