package in.chetanmehra.minimum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
    private Stage stage2;
    private int width;
    private int height;
    private Button newComputerGame;
    private Button creditsScreen;
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
        stage2 = new Stage(viewport);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);

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
        creditsScreen = new TextButton("Credits", skin);
        newComputerGame.setSize(230.0f, 95.0f);
        newComputerGame.setPosition(width / 2, height / 2);
        stage.addActor(newComputerGame);
        stage.addActor(creditsScreen);
        LoadButtonListeners();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(stage2);
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
        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
            onBackPressed();
        stage2.act();
        stage2.draw();
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
        creditsScreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SwitchToScreen(new CreditsScreen(assests));
            }
        });
    }

    private void StartGameWithComputerPlayers() {
        SwitchToScreen(new GamePlayScreen(assests));
    }

    @Override
    public void onBackPressed() {
        Gdx.app.log(TAG, "Back Key Pressed");
        Skin skin = assests.manager.get(Assests.glassySkin);
        Button btnQuit = new TextButton("Quit", skin);
        Button btnRestart = new TextButton("Restart", skin);
        Button btnCancel = new TextButton("Cancel", skin);
        Dialog dialog = new Dialog("Are you sure you want to exit", skin) {
            @Override
            protected void result(Object object) {
                if (object.equals(Integer.valueOf(0)))      //Cancel button
                {
                    hide();
                } else if (object.equals(Integer.valueOf(-1))) {
                    Gdx.app.exit();   //Quit Button
                }
            }
        };
        dialog.button(btnQuit, Integer.valueOf(-1));
        dialog.button(btnCancel, Integer.valueOf(0));

        dialog.show(stage2);

    }
}
