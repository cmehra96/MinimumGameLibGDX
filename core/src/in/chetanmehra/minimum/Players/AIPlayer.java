package in.chetanmehra.minimum.Players;

import java.util.Random;

import in.chetanmehra.minimum.GameHelpers.Assests;

public class AIPlayer extends Player {
    private static String[] playerName = {"Bot 1", "Bot 2", "Bot 3", "Bot 4", "Bot 5"};

    public AIPlayer(Assests assests) {

        super(playerName[new Random().nextInt(5)], assests);
    }
}
