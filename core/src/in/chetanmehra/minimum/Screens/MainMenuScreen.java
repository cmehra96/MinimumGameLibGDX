package in.chetanmehra.minimum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

import in.chetanmehra.minimum.GameHelpers.Assests;

public class MainMenuScreen extends AbstractScreen {
    private String TAG = "Main Menu Screen";
    private Stage stage;
    private int width;
    private int height;
    private Button newComputerGame;
    private Button gameInstructionsBtn;
    private Skin skin;
    GestureDetector gestureDetector;


    public MainMenuScreen(Assests assests) {
        this.assests = assests;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        FillViewport viewport = new FillViewport(width, height, new OrthographicCamera());
        viewport.update(width, height, true);
        stage = new Stage(viewport);

    }

    public void show() {
        //  Gdx.app.log(TAG, "Enters show method");
        Image backgroundImage = new Image(assests.manager.get(Assests.backgroundImageTexture));
        backgroundImage.setSize(width, height);
        stage.addActor(backgroundImage);
        skin = assests.manager.get(Assests.neonSkin);
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        newComputerGame = new TextButton("Single Player", skin);
        newComputerGame.setSize(230.0f, 95.0f);
        newComputerGame.setPosition(width / 2, height / 4);
        stage.addActor(newComputerGame);
        LoadButtonListeners();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        gestureDetector = new GestureDetector(this);
        gestureDetector.setLongPressSeconds(0.5f);  // long press set to half second
        multiplexer.addProcessor(gestureDetector);
        Gdx.input.setInputProcessor(multiplexer);
        //Gdx.app.log(TAG, "Executed show method succussfully");
    }


    public void render(float delta) {
        //  Gdx.app.log(TAG, "Enters render method");
        Gdx.gl.glClearColor(0.187f, 0.246f, 0.621f, 1.0f);
        Gdx.gl.glClear(16384);
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();
        //  Gdx.app.log(TAG, "Executed render method succussfully");
    }

    public void dispose() {
        stage.dispose();
    }

    private void LoadButtonListeners() {
        newComputerGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StartGameWithComputerPlayers();
            }
        });
    }

    private void StartGameWithComputerPlayers() {
        SwitchToScreen(new GamePlayScreen(assests));
    }

    @Override
    public void onBackPressed() {

    }
}
