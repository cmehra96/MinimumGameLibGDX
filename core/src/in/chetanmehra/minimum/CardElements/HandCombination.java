package in.chetanmehra.minimum.CardElements;

import com.badlogic.gdx.Gdx;
import com.rits.cloning.Cloner;

import in.chetanmehra.minimum.Players.Player;

public class HandCombination {
    private final static String TAG = "Hand Combination";

    /**
     * Method to find whether selected touched
     * cards are straight cards or not.
     *
     * @param player            current Player
     * @param tempLongTouchList Deck of touched cards
     * @return true if selected cards belongs to Straight else false
     */
    public static boolean isStraight(Player player, Deck tempLongTouchList) {
        Gdx.app.log(TAG, "Checking deck is straight or not");
        if (tempLongTouchList.count() != 3) {
            return false;
        }
        Deck tempDeck = tempLongTouchList;
        tempDeck.sortByRankAsc();
        if (tempDeck.getCardByIndex(0).cardRank() == 1)            //If first card is Ace
        {
            Card tempCard = tempDeck.getCardByIndex(0);
            tempDeck.add(tempCard);
        }
        int straightCount = 1;
        int size = tempDeck.count();
        for (int i = 0; i < size - 1; i++) {
            if (straightCount == 3)
                break;
            int currentRank = tempDeck.getCardByIndex(i).cardRank();
            int nextRank = tempDeck.getCardByIndex(i + 1).cardRank();
            if (nextRank - currentRank == 1)     //if cards suit differ by 1, increment straight
                straightCount++;
            else if (nextRank == 1 && currentRank == 13) //specific condition for King and Ace
                straightCount++;
            else if (nextRank - currentRank != 1)         //if cards suit not equal to 1, reset the straight counter
                straightCount = 1;

        }
        if (tempDeck.getCardByIndex(0).cardRank() == 1)
            tempDeck.removeCard(tempDeck.getTopCard());
        if (straightCount == 3)
            return true;

        return false;

    }

    /**
     * Method to find if straight set exist using combination player deck and Discarded Deck card
     *
     * @param player Current player
     * @param card   Top Card Discarded Deck
     * @return 1 if straight exist in deck belongs, -1 if straight exist using combination of deck and @card parameter, 0 if no straight exist.
     */

    public static int isStraight(Player player, Card card) {
        Gdx.app.log(TAG, "Inside isStraight method2");
        Deck temp = player.getMyDeck();
        temp.sortByRankAsc();
        if (temp.getCardByIndex(0).cardRank() == 1) {
            Card tempCard = temp.getCardByIndex(0);
            temp.add(tempCard);
        }
        int straightCount = 1;   //To count number of straight cards from player deck
        int otherStaightCount = 1; // To count if straight belongs with {@card}
        int size = temp.count() - 1;
        for (int i = 0; i < size; i++) {
            if (straightCount == 3)
                break;
            int currentRank = temp.getCardByIndex(i).cardRank();
            int nextRank = temp.getCardByIndex(i + 1).cardRank();
            if (nextRank - currentRank == 1)  //if cards suit differ by 1, increment straight
                straightCount++;

            else if (nextRank == 1 && currentRank == 13)  //specific condition for King and Ace
                straightCount++;
            else if (nextRank - currentRank != 1)  //if cards suit not equal to 1, reset the straight counter
                straightCount = 1;


        }
        if (temp.getCardByIndex(0).cardRank() == 1)
            temp.removeCard(temp.getTopCard());
        if (straightCount == 3) {
            Gdx.app.log(TAG, "Straight exist in player deck");
            return 1;
        }
        // Excution to find straight cards with Discarded Deck card starts


        temp.add(card);
        temp.sortByRankAsc();
        if (temp.getCardByIndex(0).cardRank() == 1) {
            Card tempCard = temp.getCardByIndex(0);
            temp.add(tempCard);
        }
        for (int i = 0; i < temp.count() - 1; i++) {
            if (otherStaightCount == 3)
                break;
            int currentRank = temp.getCardByIndex(i).cardRank();
            int nextRank = temp.getCardByIndex(i + 1).cardRank();
            if (nextRank - currentRank == 1)
                otherStaightCount++;
            else if (nextRank == 1 && currentRank == 13)
                otherStaightCount++;
            else if (nextRank - currentRank != 1)
                otherStaightCount = 1;
        }
        temp.removeCard(card);
        if (otherStaightCount == 3)
            return -1;
        return 0;


    }

    public static Deck getStraight(Player player) {
        Gdx.app.log(TAG, "Inside GetStraight method");
        Deck straightCards = new Deck(player.getMyDeck().getAssets());

        Deck tempDeck = player.getMyDeck();
        tempDeck.sortByRankAsc();
        if (tempDeck.getCardByIndex(0).cardRank() == 1)
            tempDeck.add(tempDeck.getCardByIndex(0));

        straightCards.add(player.getCardByIndex(0));
        int size = tempDeck.count() - 1;
        for (int i = 0; i < size; i++) {
            if (straightCards.count() == 3)
                return straightCards;

            int currentRank = tempDeck.getCardByIndex(i).cardRank();
            int nextRank = tempDeck.getCardByIndex(i + 1).cardRank();
            if (nextRank - currentRank == 1)
                straightCards.add(tempDeck.getCardByIndex(i + 1));
            else if (nextRank == 1 && currentRank == 13)
                straightCards.add(tempDeck.getCardByIndex(i + 1));
            else if (nextRank - currentRank != 1) {
                straightCards.clear();
                straightCards.add(tempDeck.getCardByIndex(i + 1));
            }
        }
        if (tempDeck.getCardByIndex(0).cardRank() == 1)
            tempDeck.removeCard(tempDeck.getTopCard());
        Gdx.app.log(TAG, "Get Straight method executed succussfully");
        return straightCards;
    }

    public static Card createStraight(Player player, Card topCard) {
        Gdx.app.log(TAG, "Inside Create straight method");
        Card removeCard = null;

        Deck tempDeck = player.getMyDeck();
        tempDeck.sortByRankAsc();
        if (tempDeck.getCardByIndex(0).cardRank() == 1)
            tempDeck.add(tempDeck.getCardByIndex(0));
        int decksize = tempDeck.count();
        Deck straightDeck = new Deck(player.getMyDeck().getAssets());
        straightDeck.add(tempDeck.getCardByIndex(0));
        for (int i = 0; i < decksize - 1; i++) {
            if (straightDeck.count() == 3)
                break;
            int currentRank = tempDeck.getCardByIndex(i).cardRank();
            int nextRank = tempDeck.getCardByIndex(i + 1).cardRank();
            if (nextRank - currentRank == 1)
                straightDeck.add(tempDeck.getCardByIndex(i + 1));
            else if (nextRank == 1 && currentRank == 13)
                straightDeck.add(tempDeck.getCardByIndex(i + 1));
            else if (nextRank - currentRank != 1) {
                straightDeck.add(topCard);              // Added discarded deck top card
                if (isStraight(straightDeck)) {
                    removeCard = tempDeck.removeCard(tempDeck.getCardByIndex(i + 1));
                    break;
                } else {
                    removeCard = tempDeck.removeCard(tempDeck.getCardByIndex(i));
                }
                straightDeck.clear();
                straightDeck.add(tempDeck.getCardByIndex(i + 1));


            }


        }
        if (tempDeck.getCardByIndex(0).cardRank() == 1)
            tempDeck.removeCard(tempDeck.getTopCard());
        Gdx.app.log(TAG, "Removed Card is" + removeCard.getSuit() + " " + removeCard.getCardValue());
        return removeCard;

    }

    public static boolean isStraight(Deck tempdeck) {
        Gdx.app.log(TAG, "Inside isStraight method3");
        tempdeck.sortByRankAsc();

        if (tempdeck.getCardByIndex(0).cardRank() == 1)       // If first card is Ace
        {
            Card tempCard = tempdeck.getCardByIndex(0);
            tempdeck.add(tempCard);
        }
        int straightcount = 1;
        for (int i = 0; i < tempdeck.count() - 1; i++) {
            if (straightcount == 3) {
                // Log.d(TAG, "is straight worked successfully");
                break;
            }
            int currentrank = tempdeck.getCardByIndex(i).cardRank();
            int nextrank = tempdeck.getCardByIndex(i + 1).cardRank();
            if (nextrank - currentrank == 1)           //if cards suit differ by 1, increment straight
            {
                straightcount++;
            } else if (nextrank == 1 && currentrank == 13)     //specific condition for King and Ace
            {
                straightcount++;
            } else if (nextrank - currentrank != 1)    //if cards suit not equal to 1, reset the straight counter
            {
                straightcount = 1;
            }
        }
        Gdx.app.log(TAG, "Mehtod excuted succussfully");
        if (tempdeck.getCardByIndex(0).cardRank() == 1)
            tempdeck.removeCard(tempdeck.getTopCard());
        if (straightcount == 3)
            return true;

        return false;

    }

    /**
     * Method to check whether selected card of
     * player is Three of a kind or not
     *
     * @param player            Current Player
     * @param tempLongTouchList Deck of cards touched by player
     * @return True if touch cards are three of kind else false
     */
    public static boolean isThreeOfKind(Player player, Deck tempLongTouchList) {
        Gdx.app.log(TAG, "Inside Multi touch three of kind");
        if (tempLongTouchList.count() != 3)
            return false;
        if (tempLongTouchList.getCardByIndex(0).cardRank() == tempLongTouchList.getCardByIndex(1).cardRank()
                && tempLongTouchList.getCardByIndex(1).cardRank() == tempLongTouchList.getCardByIndex(2).cardRank())
            return true;

        return false;
    }

    /**
     * @param player
     * @param card
     * @return
     */
    public static int isThreeOfKind(Player player, Card card) {
        Gdx.app.log(TAG, "Inside is three of kind method 2");
        Deck tempDeck = player.getMyDeck();
        tempDeck.sortByRankAsc();
        int count = tempDeck.count();
        for (int i = 0; i < count - 2; i++) {
            if (tempDeck.getCardByIndex(i).cardRank() == tempDeck.getCardByIndex(i + 1).cardRank()
                    && tempDeck.getCardByIndex(i).cardRank() == tempDeck.getCardByIndex(i + 2).cardRank())
                return 1;

        }
        for (int i = 0; i < count - 1; i++) {
            if (tempDeck.getCardByIndex(i).cardRank() == tempDeck.getCardByIndex(i + 1).cardRank()
                    && tempDeck.getCardByIndex(i).cardRank() == card.cardRank())
                return -1;
        }
        return 0;
    }


    public static Deck getThreeOfKind(Player player) {
        Deck threeOfKind = new Deck(player.getMyDeck().getAssets());

        Deck tempDeck = player.getMyDeck();
        int count = tempDeck.count();
        for (int i = 0; i < count - 2; i++) {
            if (tempDeck.getCardByIndex(i).cardRank() == tempDeck.getCardByIndex(i + 1).cardRank()
                    && tempDeck.getCardByIndex(i).cardRank() == tempDeck.getCardByIndex(i + 2).cardRank()) {
                threeOfKind.add(tempDeck.getCardByIndex(i));
                threeOfKind.add(tempDeck.getCardByIndex(i + 1));
                threeOfKind.add(tempDeck.getCardByIndex(i + 2));
                break;
            }
        }
        return threeOfKind;
    }

    public static Card createThreeOfKind(Player player, Card topCard) {
        Card removedcard = null;

        Deck tempDeck = player.getMyDeck();
        tempDeck.sortByRankDesc();
        int count = tempDeck.count();
        for (int i = 0; i < count - 1; i++) {
            if (tempDeck.getCardByIndex(i).cardRank() == tempDeck.getCardByIndex(i + 1).cardRank()
                    && tempDeck.getCardByIndex(i).cardRank() == topCard.cardRank()) {
                if (i - 1 >= 0)
                    removedcard = tempDeck.removeCard(tempDeck.getCardByIndex(i - 1));
                else
                    removedcard = tempDeck.removeCard(tempDeck.getCardByIndex(i + 2));
                break;
            }
        }
        return removedcard;
    }
}
