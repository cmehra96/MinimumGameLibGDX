package in.chetanmehra.minimum.listeners;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.Players.Player;

public interface PlayerEventsListener {
    void singleSwapFromDealtDeck(Player player, Card swapCard);

    void multiSwapFromDealtDeck(Player player, Deck tempLongTouchList);

    void singleSwapFromDiscardedDeck(Player player, Card swapCard);

    void multiSwapFromDiscardedDeck(Player player, Deck tempLongTouchList);

    void sayMinimum(Player player);

}
