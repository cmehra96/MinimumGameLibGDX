package in.chetanmehra.minimum.Players;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.GameHelpers.CurrentGameState;
import in.chetanmehra.minimum.Utility.Pair;


public class AIPlayer extends Player {
    private String TAG;
    private static String[] playerName = {"Bot 1", "Bot 2", "Bot 3", "Bot 4", "Bot 5"};
    private ScheduledThreadPoolExecutor asyncTaskExecutor = new ScheduledThreadPoolExecutor(5);
    private int callPercent = 0;
    private CurrentGameState currentGameState;

    public AIPlayer(String name, Assests assests) {

        super(name, assests);
        //super(playerName[new Random().nextInt(5)], assests);
        TAG = "AIPlayer " + name;
    }

    public void notifyPlayerForHisTurn(CurrentGameState currentGameState) {
        Gdx.app.log(TAG, "Inside Notify Player for His Turn method");
        this.currentGameState = currentGameState;
        asyncTaskExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                AIPlayer.this.choseActionToPlayAndInformListeners();
            }
        }, (long) GetRandomNumber(1, 3), TimeUnit.SECONDS);
    }

    private void choseActionToPlayAndInformListeners() {
        // int callPercent=getCallPercent(currentGameState.players,this);
        pickBestCard(myDeck, currentGameState.discardedDeck.getTopCard());
    }

    private int getCallPercent(ArrayList<Player> players, Player currentPlayer) {
        Gdx.app.log(TAG, "Inside get Call Percent method");
        int cardCount = myDeck.count();
        int size = 0;
        int noOfPlayers = players.size();
        return -1;

    }

    private void pickBestCard(Deck myDeck, Card deckTopCard) {
        Gdx.app.log(TAG, "Inside pick Best Card method");
        Pair<Card, Boolean> result = getLargestCardByRank(deckTopCard);
        if (result.second == true) {
            listener.singleSwapFromDealtDeck(this, result.first);
        } else {
            listener.singleSwapFromDiscardedDeck(this, result.first);
        }


    }

    private Pair<Card, Boolean> getLargestCardByRank(Card deckTopCard) {
        Card temp = myDeck.getCardByIndex(0);
        int size = myDeck.count();
        for (int i = 1; i < size; i++) {
            if (myDeck.getCardByIndex(i).cardRank() > temp.cardRank()) {
                temp = myDeck.getCardByIndex(i);
            }

        }
        if (temp.cardRank() > deckTopCard.cardRank())
            return new Pair<>(temp, true);
        else
            return new Pair<>(temp, false);
    }

    private int GetRandomNumber(int min, int max) {
        return ((int) (Math.random() * ((double) ((max + 1) - min)))) + min;
    }
}
