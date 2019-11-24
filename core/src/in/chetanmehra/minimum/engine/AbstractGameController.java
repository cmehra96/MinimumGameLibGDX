package in.chetanmehra.minimum.engine;

import com.badlogic.gdx.graphics.Camera;

import java.util.ArrayList;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.Players.Player;

public abstract class AbstractGameController {
    protected Assests assests;
    protected Camera camera;
    protected Deck dealtDeck;
    protected Deck discardedDeck;
    protected boolean isGameEnded = false;
    protected boolean isShuffled = false;
    protected Player mainPlayer;
    protected ArrayList<Player> players;

    public AbstractGameController(Camera camera, Assests assests) {
        this.camera = camera;
        this.assests = assests;
        this.dealtDeck = new Deck(assests);
        dealtDeck.allocateDeck();
        this.discardedDeck = new Deck(assests);
    }

    public abstract void mainPlayerCardTapped(int cardindex, boolean longPressed);

    public abstract void topCardOfDealtDeckTapped(boolean longPressed);

    public abstract void topCardOfDiscardedDeckTapped(boolean longPressed);

    public Assests getAssests() {
        return assests;
    }

    public void setAssests(Assests assests) {
        this.assests = assests;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Deck getDealtDeck() {
        return dealtDeck;
    }

    public void setDealtDeck(Deck dealtDeck) {
        this.dealtDeck = dealtDeck;
    }

    public Deck getDiscardedDeck() {
        return discardedDeck;
    }

    public void setDiscardedDeck(Deck discardedDeck) {
        this.discardedDeck = discardedDeck;
    }

    public boolean isGameEnded() {
        return isGameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        isGameEnded = gameEnded;
    }

    public Player getMainPlayer() {
        return mainPlayer;
    }

    public void setPlayer(Player mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public abstract void processGameRender();

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void refillDealtDeck() {

        if (dealtDeck.count() == 0) {
            Card topCard = discardedDeck.removeTopCard();
            while (discardedDeck.count() > 0) {
                dealtDeck.add(discardedDeck.removeTopCard());
            }
            dealtDeck.shuffle();
            discardedDeck.add(topCard);
        }

    }
}
