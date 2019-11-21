package in.chetanmehra.minimum;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.Screens.AbstractScreen;
import in.chetanmehra.minimum.Screens.MainMenuScreen;

public class Game extends com.badlogic.gdx.Game {
    private SpriteBatch batch;
    public Assests assests;
    private OrthographicCamera camera;


    @Override
    public void create() {
        assests = new Assests();
        assests.load();
        assests.manager.finishLoading();
        batch = new SpriteBatch();
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();
        //img = new Texture("badlogic.jpg");
    }

    @Override
    public void render() {
        super.render();
        AbstractScreen currentscreen = (AbstractScreen) getScreen();
        if (currentscreen != null) {
            if (currentscreen.isGoToPreviousScreen() && currentscreen.getPreviousScreen() != null) {
                setScreen(currentscreen.getPreviousScreen());
            } else if (currentscreen.isGoToNextScreen() && currentscreen.getNextScreen() != null) {
                setScreen(currentscreen.getNextScreen());
            }
        } else if (assests.manager.update()) {
            setScreen(new MainMenuScreen(assests));
        } else {
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            batch.draw(assests.manager.get(Assests.cardBackImage), 050, 0);
            batch.end();
        }

    }


    @Override
    public void dispose() {
        batch.dispose();

    }
}
