package in.chetanmehra.minimum.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

import java.util.ArrayList;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.Players.AIPlayer;
import in.chetanmehra.minimum.Players.Player;

public class GameController extends AbstractGameController {
    private final String TAG = "GameController";
    private final int NUMBER_OF_CPUPLAYERS = 5;
    private Card touchedCard = null;
    Deck tempLongTouchList = null;
    private boolean isLongPressed = false;

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

    /**
     * Method to add single or multiple touch cards
     *
     * @param cardindex   Index of the card touched
     * @param longPressed Detect whether tap or long press event trigger
     */
    @Override
    public void mainPlayerCardTapped(int cardindex, boolean longPressed) {
        isLongPressed = longPressed;
        if (!isLongPressed) {

            touchedCard = mainPlayer.getCardByIndex(cardindex);
            Gdx.app.log("Game Controller", "Single Card Touched " + touchedCard.getSuit() + " " + touchedCard.getCardValue());
        } else {
            if (tempLongTouchList == null) {
                tempLongTouchList = new Deck(assests);

            }
            tempLongTouchList.add(mainPlayer.getCardByIndex(cardindex));


        }


    }

    /**
     * Method to detect Dealt Deck tapped
     */
    @Override
    public void topCardOfDealtDeckTapped() {
        if (!isLongPressed) {
            if (touchedCard != null) {
                singleCardDealtDeckSwap(mainPlayer, touchedCard);
                touchedCard = null;
            }
        } else {
            multiCardDealtDeckSwap(mainPlayer);

        }
        if (dealtDeck.count() == 0) {
            Gdx.app.log(TAG, "Dealt Deck empty refilling");
            refillDealtDeck();
        }
    }

    /**
     * Method to swap card between Dealt Deck
     * and Player touched card
     *
     * @param player
     * @param touchedCard
     */
    private void singleCardDealtDeckSwap(Player player, Card touchedCard) {
        Gdx.app.log(TAG, "Inside single Card Dealt Deck swap method");
        Card temp = dealtDeck.removeTopCard();
        Gdx.app.log(TAG, "Dealt Deck Card touched " + temp.getSuit() + " " + temp.getCardValue());
        Card temp1 = player.removeCard(touchedCard);
        player.addToHand(temp);
        discardedDeck.add(temp1);
    }

    /**
     * Method to remove all the touched cards of Player
     * deck if they belong to removable rules.
     *
     * @param player
     */
    private void multiCardDealtDeckSwap(Player player) {
        if (tempLongTouchList.count() != 0) {
            tempLongTouchList.sortByRankAsc();
            Card temp = dealtDeck.removeTopCard();
            int i = 0;
            int size = tempLongTouchList.count();
            while (i < size) {
                Card removeCard = player.removeCard(tempLongTouchList.getCardByIndex(i));
                discardedDeck.add(removeCard);
                i++;
            }
            player.addToHand(temp);
            tempLongTouchList.clear();

        }

    }

    /**
     * Method to detect Discarded Deck is touched
     */
    @Override
    public void topCardOfDiscardedDeckTapped() {
        if (!isLongPressed) {
            if (touchedCard != null) {
                singleCardDiscardedDeckSwap(mainPlayer, touchedCard);
                touchedCard = null;
            }
        } else {
            multiCardDiscardedDeckSwap(mainPlayer);
        }

    }

    /**
     * Method to remove all the touched cards of Player
     * deck if they belong to removable rules,
     * and add them to discarded deck
     * and add top card of discarded deck to Player
     * deck
     *
     * @param player
     */
    private void multiCardDiscardedDeckSwap(Player player) {
        if (tempLongTouchList.count() != 0) {
            tempLongTouchList.sortByRankAsc();
            Card temp = discardedDeck.removeTopCard();
            int i = 0;
            int size = tempLongTouchList.count();
            while (i < size) {
                Card removeCard = player.removeCard(tempLongTouchList.getCardByIndex(i));
                discardedDeck.add(removeCard);
                i++;
            }
            player.addToHand(temp);
            tempLongTouchList.clear();

        }

    }

    /**
     * Method to swap card between Discarded Deck
     * and Player touched card
     *
     * @param player
     * @param touchedCard
     */
    private void singleCardDiscardedDeckSwap(Player player, Card touchedCard) {
        Gdx.app.log(TAG, "Inside single card Discarded Deck method");
        Card temp = discardedDeck.removeTopCard();
        Card temp2 = player.removeCard(touchedCard);
        Gdx.app.log(TAG, "Discarded Deck Card touched " + temp.getSuit() + " " + temp.getCardValue());
        discardedDeck.add(temp2);
        player.addToHand(temp);
    }


}
