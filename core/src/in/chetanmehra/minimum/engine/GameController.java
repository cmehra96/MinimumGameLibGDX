package in.chetanmehra.minimum.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

import java.util.ArrayList;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.Players.AIPlayer;
import in.chetanmehra.minimum.Players.Player;

public class GameController extends AbstractGameController {
    private final String TAG = "GameController";
    private final int NUMBER_OF_CPUPLAYERS = 5;
    private Card touchedCard = null;

    public GameController(Camera camera, Assests assests) {
        super(camera, assests);

    }

    @Override
    public void processGameRender() {
        if (!isShuffled) {
            initialiseGame();

        }
    }

    private void initialiseGame() {
        initialisePlayers();
        distributeCards();
    }


    private void initialisePlayers() {
        players = new ArrayList<>();
        players.add(new Player("You", assests));
        mainPlayer = players.get(0);
        for (int i = 1; i <= NUMBER_OF_CPUPLAYERS; i++) {
            players.add(new AIPlayer(assests));
        }

    }

    private void distributeCards() {
        dealtDeck.shuffle();
        isShuffled = true;

        discardedDeck.add(dealtDeck.deal());
        for (int i = 0; i < players.size(); i++) {
            if (i == 0) {
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
            } else {
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
                players.get(i).addToHand(dealtDeck.deal());
            }
        }
    }

    @Override
    public void mainPlayerCardTapped(int cardindex, boolean longPressed) {
        if (!longPressed) {

            touchedCard = mainPlayer.getCardByIndex(cardindex);
            Gdx.app.log("Game Controller", "Single Card Touched " + touchedCard.getSuit() + " " + touchedCard.getCardValue());
        }


    }

    @Override
    public void topCardOfDealtDeckTapped(boolean longPressed) {
        if (!longPressed) {
            if (touchedCard != null) {
                singleCardDealtDeckSwap(mainPlayer, touchedCard);
            }
        }

    }


    @Override
    public void topCardOfDiscardedDeckTapped(boolean longPressed) {

    }

    private void singleCardDealtDeckSwap(Player player, Card touchedCard) {
        Card temp = dealtDeck.removeTopCard();
        Gdx.app.log(TAG, "Dealt Deck count " + dealtDeck.count());
        Card temp1 = player.removeCard(touchedCard);
        player.addToHand(temp);
        discardedDeck.add(temp1);
        if (dealtDeck.count() == 0) {
            Gdx.app.log(TAG, "Dealt Deck empty refilling");
            refillDealtDeck();
        }
    }

}
