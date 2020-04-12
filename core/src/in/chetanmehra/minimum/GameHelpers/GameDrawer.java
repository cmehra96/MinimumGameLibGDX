package in.chetanmehra.minimum.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import in.chetanmehra.minimum.CardElements.Card;
import in.chetanmehra.minimum.CardElements.Deck;
import in.chetanmehra.minimum.Players.Player;
import in.chetanmehra.minimum.engine.AbstractGameController;

public class GameDrawer {
    final float screen_width = Gdx.graphics.getWidth();
    final float screen_height = Gdx.graphics.getHeight();
    Player currentPlayer;
    ArrayList<Player> players;
    private Assests assests;
    private Batch batch;


    public GameDrawer(SpriteBatch batch, Assests assests) {
        this.assests = assests;
        this.batch = batch;
    }

    public void drawDealtDeck(Deck dealtDeck) {
        int size = dealtDeck.getSize();
        Card card = dealtDeck.getTopCard();
        if (card == null)
            return;
        card.setCardToShowFront();
        card.setOriginCenter();
        card.setRotation(0.0f);
        card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
        card.setPosition((screen_width / 2 - card.getWidth()), (screen_height / 2 - card.getHeight() / 2));
        card.draw(batch);


    }

    public void drawDiscardedDeck(Deck discardedDeck) {
        int size = discardedDeck.getSize();

        Card card = discardedDeck.getTopCard();
        if (card == null)
            return;
        float card_width = card.getWidth();
        float card_height = card.getHeight();
        card.setCardToShowFront();
        card.setOriginCenter();
        card.setRotation(0.0f);
        card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
        card.setPosition(screen_width / 2 + card_width, screen_height / 2 - card_height / 2);
        card.draw(batch);

    }


    public void drawPlayerDeck(AbstractGameController gameController) {
        if (players == null) {
            players = gameController.getPlayers();
        }
        currentPlayer = gameController.getCurrentPlayer();
        drawMainPlayerDeck(players.get(0));

        // drawPlayerAtLeft(players.get(1));
        drawPlayerAtBottomLeft(players.get(1));
        drawPlayerAtTopLeft(players.get(2));
        drawPlayerAtTop(players.get(3));
        drawPlayerAtTopRight(players.get(4));
        drawPlayerAtBottomRight(players.get(5));
    }


    private void drawMainPlayerDeck(Player player) {
        int decksize = player.getMyDeck().count();
        if (currentPlayer == player) {
            Sprite minimumBtn = player.getMinimumButton();
            minimumBtn.setSize((float) (screen_width / 9), (float) (screen_height / 5));
            minimumBtn.setPosition((screen_width - 2.5f * minimumBtn.getWidth()), screen_height / 2 - minimumBtn.getHeight() / 2);
            minimumBtn.draw(batch);
        }
        float cardgap = (float) 0.045 * screen_width;
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            if (card == null)
                return;
            card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
            card.setCardToShowFront();
            card.setPosition((float) (((screen_width / 2) - (decksize / 2) * cardgap) + (cardgap * i)), (float) 10.0f);
            card.draw(batch);

        }
    }

    private void drawPlayerAtLeft(Player player) {
        int decksize = player.getMyDeck().count();
        float cardgap = (float) 0.05 * screen_height;
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            if (player.isShowCardFace())
                card.setCardToShowFront();
            else
                card.setCardToShowBack();
            card.setOriginCenter();
            card.setRotation(90.0f);
            card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
            card.setPosition((float) (10.0f), (float) (screen_height / 2 - (decksize / 2) * cardgap) - (cardgap * i));
            card.draw(batch);
        }
    }

    private void drawPlayerAtBottomLeft(Player player) {
        int decksize = player.getMyDeck().count();
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            if (card == null)
                return;
            if (player.isShowCardFace())
                card.setCardToShowFront();
            else
                card.setCardToShowBack();
            card.setOriginCenter();
            card.setRotation(315.0f);
            card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
            card.setPosition((float) ((0.07 * screen_width - (decksize / 2) * (0.01 * screen_width)) + (i * (0.016 * screen_width)))
                    , (float) (((decksize / 2 * (0.018 * screen_height)) + (0.074 * screen_height)) - (i * (0.027 * screen_height))));
            card.draw(batch);
        }
    }

    private void drawPlayerAtTop(Player player) {
        int decksize = player.getMyDeck().count();
        float cardgap = (float) 0.025 * screen_width;
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            if (card == null)
                return;
            if (player.isShowCardFace())
                card.setCardToShowFront();
            else
                card.setCardToShowBack();
            card.setOriginCenter();
            card.setRotation(0.0f);
            card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
            card.setPosition((float) ((screen_width / 2 - (decksize / 2) * cardgap) + (cardgap * i)), (float) (screen_height - card.getHeight()));
            card.draw(batch);
        }

    }

    private void drawPlayerAtTopLeft(Player player) {
        int decksize = player.getMyDeck().count();
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            if (card == null)
                return;
            if (player.isShowCardFace())
                card.setCardToShowFront();
            else
                card.setCardToShowBack();
            card.setOriginCenter();
            card.setRotation(225.0f);
            card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
            card.setPosition((float) ((((decksize / 2) * 10) + (0.1 * screen_width)) - (i * 20)),
                    (float) ((((decksize / 2) * 10) + (0.75 * screen_height)) - (i * 20)));
            card.draw(batch);
        }

    }

    private void drawPlayerAtTopRight(Player player) {
        int decksize = player.getMyDeck().count();
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            if (card == null)
                return;
            if (player.isShowCardFace())
                card.setCardToShowFront();
            else
                card.setCardToShowBack();
            card.setOriginCenter();
            card.setRotation(135.0f);
            card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
            card.setPosition((float) (((0.87 * screen_width) - (decksize / 2 * 10)) + (i * 10)), (float) (((decksize / 2 * 10) + (0.72 * screen_height)) - (i * 10)));
            card.draw(batch);
        }
    }

    private void drawPlayerAtBottomRight(Player player) {
        int decksize = player.getMyDeck().count();
        for (int i = 0; i < decksize; i++) {
            Card card = player.getCardByIndex(i);
            if (card == null)
                return;
            if (player.isShowCardFace())
                card.setCardToShowFront();
            else
                card.setCardToShowBack();
            card.setOriginCenter();
            card.setRotation(45.0f);
            card.setSize((float) 0.08 * screen_width, (float) 0.2 * screen_height);
            card.setPosition((float) (((decksize / 2 * 10) + (0.85 * screen_width)) - (i * 10)),
                    (float) (((decksize / 2 * 10) + 40) - (i * 10)));
            card.draw(batch);
        }
    }


}
