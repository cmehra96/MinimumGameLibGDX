package in.chetanmehra.minimum.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import in.chetanmehra.minimum.GameHelpers.Assests;

public abstract class AbstractScreen extends ScreenAdapter implements GestureDetector.GestureListener {
    public Assests assests;
    private boolean goToPreviousScreen = false;
    private boolean goToNextScreen = false;
    private Screen nextScreen = null;
    private Screen previousScreen = null;
    public Skin skin;

    public void SwitchToScreen(Screen screen) {
        goToNextScreen = true;
        setNextScreen(screen);
    }

    public boolean isGoToPreviousScreen() {
        return goToPreviousScreen;
    }

    public void setGoToPreviousScreen(boolean goToPreviousScreen) {
        this.goToPreviousScreen = goToPreviousScreen;
    }

    public boolean isGoToNextScreen() {
        return goToNextScreen;
    }

    public void setGoToNextScreen(boolean goToNextScreen) {
        this.goToNextScreen = goToNextScreen;
    }

    public Screen getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    public Screen getNextScreen() {
        return nextScreen;
    }

    public void setNextScreen(Screen nextScreen) {
        this.nextScreen = nextScreen;
    }

    public abstract void onBackPressed();

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
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
