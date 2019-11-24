package in.chetanmehra.minimum.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.Players.Player;

public class GameDrawer {
    private Assests assests;
    private Batch batch;
    final float screen_width = Gdx.graphics.getWidth();
    final float screen_height = Gdx.graphics.getHeight();
    Player currentPlayer;


    public GameDrawer(SpriteBatch batch, Assests assests) {
        this.assests = assests;
        this.batch = batch;
    }

    public void drawDealtDeck(Deck dealtDeck) {
        int size = dealtDeck.getSize();
        Card card = dealtDeck.getTopCard();
        card.setCardToShowFront();
        card.setOriginCenter();
        card.setRotation(0.0f);
        card.setPosition((screen_width / 2 - card.getWidth()), (screen_height / 2 - card.getHeight() / 2));
        card.draw(batch);


    }

    public void drawDiscardedDeck(Deck discardedDeck) {
        int size = discardedDeck.getSize();
        for (int i = 0; i < size; i++) {
            Card card = discardedDeck.getCardByIndex(i);
            float card_width = card.getWidth();
            float card_height = card.getHeight();
            card.setCardToShowFront();
            card.setOriginCenter();
            card.setRotation(0.0f);
            card.setPosition(screen_width / 2 + card_width, screen_height / 2 - card_height / 2);
            card.draw(batch);
        }
    }


    public void drawPlayerDeck(ArrayList<Player> players) {
        drawMainPlayerDeck(players.get(0));
        drawPlayerAtTopLeft(players.get(1));
        drawPlayerAtTop(players.get(2));
    }

    private void drawMainPlayerDeck(Player player) {
        int decksize = player.getMyDeck().count();
        float cardgap = (float) 0.075 * screen_width;
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            card.setCardToShowFront();
            card.setPosition((float) (((screen_width / 2) - (decksize / 2) * cardgap) + (cardgap * i)), (float) 10.0f);
            card.draw(batch);

        }
    }

    private void drawPlayerAtTop(Player player) {
        int decksize = player.getMyDeck().count();
        float cardgap = (float) 0.025 * screen_width;
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            card.setCardToShowFront();
            card.setOriginCenter();
            card.setRotation(0.0f);
            card.setPosition((float) ((screen_width / 2 - (decksize / 2) * cardgap) + (cardgap * i)), (float) (screen_height - card.getHeight()));
            card.draw(batch);
        }

    }

    private void drawPlayerAtTopLeft(Player player) {
        int decksize = player.getMyDeck().count();
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            card.setCardToShowFront();
            card.setOriginCenter();
            card.setRotation(225.0f);
            card.setPosition((float) ((((decksize / 2) * 10) + (0.1 * screen_width)) - (i * 20)), (float) ((((decksize / 2) * 10) + (0.75 * screen_height)) - (i * 20)));
            card.draw(batch);
        }

    }


}
