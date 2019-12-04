package in.chetanmehra.minimum.Players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.GameHelpers.CurrentGameState;
import in.chetanmehra.minimum.listeners.PlayerEventsListener;

public class Player {
    private String TAG = "Player class";
    protected Assests assests;
    protected String name;
    protected Deck myDeck;
    private Card lastCard;
    protected boolean isPlayerReady;
    private boolean isMinimumCalled;
    private boolean isSafed;
    private Sprite minimumButton;
    protected PlayerEventsListener listener;
    protected Card currentRoundCard;
    protected boolean roundwon;
    // protected ArrayList<Card> templongtouchlist = new ArrayList<>();       // Temporary holder of multiple selection.


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

    public String getName() {
        return name;
    }

    public Card removeCard(Card card) {
        return myDeck.removeCard(card);
    }

    public Sprite getMinimumButton() {
        if (minimumButton == null) {
            Texture minimumButtonIcon = assests.manager.get(Assests.callMinimumBtn);
            minimumButton = new Sprite(minimumButtonIcon);
        }
        return minimumButton;
    }

    public void registerListeners(PlayerEventsListener eventsListener) {
        listener = eventsListener;
    }

    public boolean isRoundwon() {
        return roundwon;
    }

    public void setRoundwon(boolean roundwon) {
        this.roundwon = roundwon;
    }

    public void notifyPlayerForHisTurn(CurrentGameState currentGameState) {
        Gdx.app.log("Player class", "Inside notify Player for his turn method");
    }
    
    
/*
    public void addToLongTouchList(Card card) {
        templongtouchlist.add(card);
    }

    public ArrayList<Card> getTemplongtouchlist() {
        return templongtouchlist;
    }

    public void clearLongTouchList() {
        templongtouchlist.clear();
    }
    */
}
