package in.chetanmehra.minimum.Players;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;
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
        int callPercent = getCallPercent(currentGameState.players, this);
        Gdx.app.log(TAG, "Call percent " + callPercent);
        if (callPercent >= 75) {
            listener.sayMinimum(this);
        } else if (myDeck.count() < 3) {
            pickBestCard(myDeck, currentGameState.discardedDeck.getTopCard());
        } else {

        }
    }

    private int getCallPercent(ArrayList<Player> players, Player currentPlayer) {
        Gdx.app.log(TAG, "Inside get Call Percent method");
        int cardCount = myDeck.count();
        int size = 0;
        int noOfPlayers = players.size();
        int lastRoundScore = previousRoundScore;
        Card roundCard = currentRoundCard;
        int tempPercent = 0;
        int index = 0;
        int score = currentPlayer.evaluateScore();
        if (cardCount <= 2) {
            if (score <= 3) {
                callPercent = 100;
                return callPercent;
            }
        }
        for (Player player :
                players) {
            if (player == currentPlayer)
                continue;
            int otherPlayerCard = player.getMyDeck().count();
            int otherPlayerLastRoundScore = player.getPreviousRoundScore();
            if (cardCount <= otherPlayerCard)   // if current player card is less than other player card
            {
                if ((lastRoundScore + roundCard.cardRank()) <= otherPlayerLastRoundScore)      // if last round won by current player
                {
                    tempPercent += 100;
                } else if ((lastRoundScore + roundCard.cardRank()) <= otherPlayerCard + 2) {
                    tempPercent += new Random().nextInt(51) + 50; // [0,50] +50 => [50,100] //random percent from 50 to 100
                } else {
                    tempPercent += new Random().nextInt(101);      // if last round won by current player
                }
            } else {
                if ((float) (score / cardCount) <= 2.5) {
                    tempPercent += new Random().nextInt(51) + 50; // [0,50] +50 => [50,100] //random percent from 50 to 100
                } else {
                    tempPercent += new Random().nextInt(101);  // if last round won by current player
                }
            }

        }
        callPercent = tempPercent / (noOfPlayers - 1); //excluding current player
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
