package in.chetanmehra.minimum.Players;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.listeners.PlayerEventsListener;

public class Player {
    protected Assests assests;
    protected String name;
    protected Deck myDeck;
    private Card lastCard;
    protected boolean isPlayerReady;
    private boolean isMinimumCalled;
    private boolean isSafed;
    private Sprite minimumButton;
    protected ArrayList<PlayerEventsListener> listeners;
    protected Card currentRoundCard;


    public Player(String name, Assests assests) {
        this.assests = assests;
        this.name = name;
        myDeck = new Deck(assests);
        isPlayerReady = true;
        isMinimumCalled = false;
        isSafed = false;
    }

    public Deck getMyDeck() {
        return myDeck;
    }

    public Card getCardByIndex(int index) {
        return myDeck.getCardByIndex(index);
    }


    public void addToHand(Card card) {
        currentRoundCard = card;
        myDeck.add(card);
    }

    public void addEventListeners(PlayerEventsListener playerEventsListener) {
        listeners.add(playerEventsListener);
    }

    public String getName() {
        return name;
    }

    public Card removeCard(Card card) {
        return myDeck.removeCard(card);
    }
}
