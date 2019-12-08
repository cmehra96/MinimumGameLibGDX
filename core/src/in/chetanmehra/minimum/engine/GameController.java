package in.chetanmehra.minimum.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

import java.util.ArrayList;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.CardElements.HandCombination;
import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.GameHelpers.CurrentGameState;
import in.chetanmehra.minimum.Players.AIPlayer;
import in.chetanmehra.minimum.Players.Player;
import in.chetanmehra.minimum.listeners.PlayerEventsListener;

public class GameController extends AbstractGameController {
    private final String TAG = "GameController";
    private final int NUMBER_OF_CPUPLAYERS = 5;
    private Card touchedCard = null;
    Deck tempLongTouchList = null;
    private boolean isLongPressed = false;
    private CurrentGameState currentGameState;

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
            players.add(new AIPlayer("Bot " + (i), assests));
        }
        for (Player player : players
                ) {
            player.registerListeners(new PlayerEventsListener() {
                @Override
                public void singleSwapFromDealtDeck(Player player, Card swapCard) {
                    GameController.this.singleCardDealtDeckSwap(player, swapCard);
                }

                @Override
                public void multiSwapFromDealtDeck(Player player, Deck tempLongTouchList) {
                    GameController.this.multiCardDealtDeckSwap(player, tempLongTouchList);
                }

                @Override
                public void singleSwapFromDiscardedDeck(Player player, Card swapCard) {
                    GameController.this.singleCardDiscardedDeckSwap(player, swapCard);
                }

                @Override
                public void multiSwapFromDiscardedDeck(Player player, Deck tempLongTouchList) {
                    GameController.this.multiCardDiscardedDeckSwap(player, tempLongTouchList);
                }

                @Override
                public void sayMinimum(Player player) {
                    GameController.this.callMinimumByPlayer(player);
                }
            });

        }
        currentGameState = new CurrentGameState();

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
                //touchedCard = null;
            }
        } else {
            multiCardDealtDeckSwap(mainPlayer, tempLongTouchList);

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
        Gdx.app.log(TAG, "Inside Single Card method, dealt deck size" + dealtDeck.count());
        Card temp1 = player.removeCard(touchedCard);
        player.addToHand(temp);
        discardedDeck.add(temp1);
        this.touchedCard = null;          //Class variable
        if (dealtDeck.count() == 0) {
            Gdx.app.log(TAG, "Dealt Deck empty refilling");
            refillDealtDeck();
        }
        switchTurnToNextPlayer(false);

    }

    /**
     * Method to remove all the touched cards of Player
     * deck if they belong to removable rules.
     *
     * @param player
     * @param tempLongTouchList
     */
    private void multiCardDealtDeckSwap(Player player, Deck tempLongTouchList) {
        if (HandCombination.isStraight(player, tempLongTouchList) == true || HandCombination.isThreeOfKind(player, tempLongTouchList) == true) {
            tempLongTouchList.sortByRankAsc();
            Card temp = dealtDeck.removeTopCard();
            Gdx.app.log(TAG, "Inside Multi Card method, dealt deck size" + dealtDeck.count());
            int i = 0;
            int size = tempLongTouchList.count();
            while (i < size) {
                Card removeCard = player.removeCard(tempLongTouchList.getCardByIndex(i));
                discardedDeck.add(removeCard);
                i++;
            }
            player.addToHand(temp);
            tempLongTouchList.clear();
            if (dealtDeck.count() == 0) {
                Gdx.app.log(TAG, "Dealt Deck empty refilling");
                refillDealtDeck();
            }
            switchTurnToNextPlayer(false);

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
                // touchedCard = null;
            }
        } else {
            multiCardDiscardedDeckSwap(mainPlayer, tempLongTouchList);
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
     * @param tempLongTouchList
     */
    private void multiCardDiscardedDeckSwap(Player player, Deck tempLongTouchList) {
        if (HandCombination.isStraight(player, tempLongTouchList) == true || HandCombination.isThreeOfKind(player, tempLongTouchList) == true) {
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
            switchTurnToNextPlayer(false);

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
        this.touchedCard = null;      //Global variable
        switchTurnToNextPlayer(false);
    }

    public void switchTurnToNextPlayer(boolean isshowdowncalled) {
        int size = players.size();
        if (isshowdowncalled) {
            for (int i = 0; i < size; i++) {
                if (players.get(i).isRoundwon())
                    currentPlayerIndex = (i + 1) % size;
            }

        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % size;
        }

        ((Player) players.get(currentPlayerIndex)).notifyPlayerForHisTurn(getCurrentGameState());

    }

    @Override
    public void minimumButtonTapped() {
        callMinimumByPlayer(mainPlayer);

    }

    private void callMinimumByPlayer(Player currentPlayer) {
        Gdx.app.log(TAG, "Minimum Called By " + currentPlayer.getName());
        boolean roundwon = true;
        Player winnerPlayer = currentPlayer;
        int roundScore = currentPlayer.evaluateScore();
        for (Player player : players) {
            if (player == currentPlayer)
                continue;
            int playerRoundScore = player.evaluateScore();
            if (roundScore >= playerRoundScore) {
                roundwon = false;
                winnerPlayer = player;
                roundScore = playerRoundScore;

            }

        }
        if (roundwon) {
            for (Player player :
                    players) {
                if (player == currentPlayer)
                    continue;
                player.addScore(player.evaluateScore());
                player.setRoundwon(false);
            }
        } else {
            currentPlayer.addScore(2 * currentPlayer.evaluateScore());       // Double points added to player if lost
            players.get(players.indexOf(winnerPlayer)).setRoundwon(true);   // Index of player who won the round.
        }
        Gdx.app.log(TAG, "Winner of this round " + winnerPlayer.getName());
        switchTurnToNextPlayer(true);

    }

    private CurrentGameState getCurrentGameState() {
        currentGameState.dealtDeck = this.dealtDeck;
        currentGameState.discardedDeck = this.discardedDeck;
        currentGameState.players = this.players;

        return currentGameState;
    }

}
