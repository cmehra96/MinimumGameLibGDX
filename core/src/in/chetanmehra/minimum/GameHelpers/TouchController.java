package in.chetanmehra.minimum.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import in.chetanmehra.minimum.Players.Player;
import in.chetanmehra.minimum.engine.AbstractGameController;


public class TouchController implements GestureDetector.GestureListener {
    private String TAG = "TouchController";
    private AbstractGameController gameController;

    public TouchController(AbstractGameController gameController) {
        this.gameController = gameController;
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        Vector3 input = new Vector3(x, y, 0.0f);
        gameController.getCamera().unproject(input);

        Gdx.app.log("Touch Controller", "Inside touch down method");

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector3 input = new Vector3(x, y, 0.0f);
        gameController.getCamera().unproject(input);
        Gdx.app.log("Touch Controller", "Inside tap method");
        if (gameController.getMainPlayer() != gameController.getCurrentPlayer()) {
            Gdx.app.log(TAG, "Current player is not main player");
            return false;
        }
        Player mainPlayer = gameController.getMainPlayer();
        int decksize = mainPlayer.getMyDeck().count();
        for (int i = 0; i < decksize; i++) {
            if (mainPlayer.getCardByIndex(i).getBoundingRectangle().contains(input.x, input.y)) {
                gameController.mainPlayerCardTapped(i, false);
                return true;
            }
        }
        if (gameController.getDealtDeck().getTopCard() != null && gameController.getDealtDeck().getTopCard().getBoundingRectangle().contains(input.x, input.y)) {
            gameController.topCardOfDealtDeckTapped();
        } else if (gameController.getDiscardedDeck().getTopCard() != null && gameController.getDiscardedDeck().getTopCard().getBoundingRectangle().contains(input.x, input.y)) {
            gameController.topCardOfDiscardedDeckTapped();
        }


        return true;
    }

    @Override
    public boolean longPress(float x, float y) {

        Vector3 input = new Vector3(x, y, 0.0f);
        gameController.getCamera().unproject(input);
        Gdx.app.log("Touch Controller", "Inside long press method");
        if (gameController.getMainPlayer() != gameController.getCurrentPlayer()) {
            Gdx.app.log(TAG, "Current player is not main player");
            return false;
        }
        Player mainPlayer = gameController.getMainPlayer();
        int decksize = mainPlayer.getMyDeck().count();
        for (int i = 0; i < decksize; i++) {
            if (mainPlayer.getCardByIndex(i).getBoundingRectangle().contains(input.x, input.y)) {
                gameController.mainPlayerCardTapped(i, true);
                return true;
            }
        }
        if (gameController.getDealtDeck().getTopCard() != null && gameController.getDealtDeck().getTopCard().getBoundingRectangle().contains(input.x, input.y)) {
            gameController.topCardOfDealtDeckTapped();
        }
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}

