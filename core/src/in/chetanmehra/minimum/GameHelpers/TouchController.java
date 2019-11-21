package in.chetanmehra.minimum.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import in.chetanmehra.minimum.Players.Player;
import in.chetanmehra.minimum.engine.AbstractGameController;

public class TouchController implements GestureDetector.GestureListener {
    private AbstractGameController gameController;

    public TouchController(AbstractGameController gameController) {
        this.gameController = gameController;
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        Vector3 input = new Vector3(x, y, 0.0f);
        gameController.getCamera().unproject(input);
/*
        Player mainPlayer=gameController.getMainPlayer();
        int decksize=mainPlayer.getMyDeck().count();
        for(int i=0;i<decksize;i++)
        {
            if(mainPlayer.getCardByIndex(i).getBoundingRectangle().contains(x,y))
            {
                Gdx.app.debug("Tapped Card",mainPlayer.getName()+mainPlayer.getCardByIndex(i).getCardValue()+mainPlayer.getCardByIndex(i).getSuit());
            }
        }
        */
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector3 input = new Vector3(x, y, 0.0f);
        gameController.getCamera().unproject(input);
/*
        Player mainPlayer=gameController.getMainPlayer();
        int decksize=mainPlayer.getMyDeck().count();
        for(int i=0;i<decksize;i++)
        {
            if(mainPlayer.getCardByIndex(i).getBoundingRectangle().contains(x,y))
            {
                Gdx.app.debug("Tapped Card",mainPlayer.getName()+mainPlayer.getCardByIndex(i).getCardValue()+mainPlayer.getCardByIndex(i).getSuit());
            }
        }
        */
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {

        Vector3 input = new Vector3(x, y, 0.0f);
        gameController.getCamera().unproject(input);

      /*  Player mainPlayer=gameController.getMainPlayer();
        int decksize=mainPlayer.getMyDeck().count();
        for(int i=0;i<decksize;i++)
        {
            if(mainPlayer.getCardByIndex(i).getBoundingRectangle().contains(x,y))
            {
                Gdx.app.debug("Tapped Card",mainPlayer.getName()+mainPlayer.getCardByIndex(i).getCardValue()+mainPlayer.getCardByIndex(i).getSuit());
            }
        }*/
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
