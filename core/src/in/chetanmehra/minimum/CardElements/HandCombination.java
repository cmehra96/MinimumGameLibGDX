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
        Cloner clone = new Cloner();
        Deck tempDeck = clone.deepClone(tempLongTouchList);
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
        if (straightCount == 3)
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
        if (tempLongTouchList.count() != 3)
            return false;
        if (tempLongTouchList.getCardByIndex(0).cardRank() == tempLongTouchList.getCardByIndex(1).cardRank()
                && tempLongTouchList.getCardByIndex(1).cardRank() == tempLongTouchList.getCardByIndex(2).cardRank())
            return true;

        return false;
    }
}
