package in.chetanmehra.minimum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;

import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.GameHelpers.GameDrawer;
import in.chetanmehra.minimum.GameHelpers.TouchController;
import in.chetanmehra.minimum.engine.AbstractGameController;
import in.chetanmehra.minimum.engine.GameController;

public class GamePlayScreen extends AbstractScreen {
    private String TAG = "Game Play Screen";
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private int width = 0;
    private int height = 0;
    private GameDrawer gameDrawer;
    private AbstractGameController gameController;


    public GamePlayScreen(Assests assests) {
        this.assests = assests;
        camera = new OrthographicCamera();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera.setToOrtho(false, width, height);
        camera.update();
        batch = new SpriteBatch();
        FillViewport viewport = new FillViewport(width, height, camera);
        viewport.update(width, height, true);
        stage = new Stage(viewport);
        gameDrawer = new GameDrawer(batch, assests);
        gameController = new GameController(camera, assests);

    }

    public void show() {
        Gdx.app.log(TAG, "Enters show method");
        Image backgroundImage = new Image(assests.manager.get(Assests.backgroundImageTexture));
        backgroundImage.setSize(width, height);
        stage.addActor(backgroundImage);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new GestureDetector(new TouchController(gameController)));
        Gdx.input.setInputProcessor(multiplexer);
        Gdx.app.log(TAG, "Executed show method succussfully");
    }

    public void render(float delta) {
        Gdx.app.log(TAG, "Enters render method");
        Gdx.gl.glClearColor(0.187f, 0.246f, 0.621f, 1.0f);
        Gdx.gl.glClear(16384);
        batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();
        batch.begin();
        gameDrawer.drawDealtDeck(gameController.getDealtDeck());
        gameDrawer.drawDiscardedDeck(gameController.getDiscardedDeck());
        gameController.processGameRender();
        gameDrawer.drawPlayerDeck(gameController.getPlayers());
        batch.end();

        Gdx.app.log(TAG, "render metod executed succussfully");
    }
}
