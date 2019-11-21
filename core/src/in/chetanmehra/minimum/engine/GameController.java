package in.chetanmehra.minimum.engine;

import com.badlogic.gdx.graphics.Camera;

import java.util.ArrayList;

import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.Players.AIPlayer;
import in.chetanmehra.minimum.Players.Player;

public class GameController extends AbstractGameController {
    private final String TAG = "GameController";
    private final int NUMBER_OF_CPUPLAYERS = 5;

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


}
