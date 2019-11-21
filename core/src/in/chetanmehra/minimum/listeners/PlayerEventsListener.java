package in.chetanmehra.minimum.listeners;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.Players.Player;

public interface PlayerEventsListener {
    boolean playCard(Player player, Card card);

    boolean requestCardfromDealtDeck(Player player);

    boolean requestCardfromDiscardedDeck(Player player);

    boolean sayMinimum(Player player);

}
