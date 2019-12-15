package in.chetanmehra.minimum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    private Stage stage2;
    private Stage stage3;
    private int width = 0;
    private int height = 0;
    private GameDrawer gameDrawer;
    private AbstractGameController gameController;
    // private ScoreBoard scoreBoard;


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
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        stage = new Stage(viewport);
        stage2 = new Stage(viewport);
        stage3 = new Stage(viewport);
        gameDrawer = new GameDrawer(batch, assests);
        gameController = new GameController(camera, assests);
        // scoreBoard = new ScoreBoard(stage3, this.assests);
        //gameController.setScoreBoard(scoreBoard);


    }

    public void show() {
        //    Gdx.app.log(TAG, "Enters show method");
        Image backgroundImage = new Image(assests.manager.get(Assests.backgroundImageTexture));
        backgroundImage.setSize(width, height);
        stage.addActor(backgroundImage);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(stage2);
        multiplexer.addProcessor(stage3);
        multiplexer.addProcessor(new GestureDetector(new TouchController(gameController)));
        Gdx.input.setInputProcessor(multiplexer);
        batch.setProjectionMatrix(camera.combined);


        //   Gdx.app.log(TAG, "Executed show method succussfully");
    }

    public void render(float delta) {
        //Gdx.app.log(TAG, "Enters render method");
        Gdx.gl.glClearColor(0.187f, 0.246f, 0.621f, 1.0f);
        Gdx.gl.glClear(16384);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
            onBackPressed();
        stage.act();
        stage.draw();

        batch.begin();
        gameController.processGameRender();

        gameDrawer.drawDealtDeck(gameController.getDealtDeck());
        gameDrawer.drawDiscardedDeck(gameController.getDiscardedDeck());

        gameDrawer.drawPlayerDeck(gameController);
        // scoreBoard.displayScoreCard();

        batch.end();
        stage2.act();
        stage2.draw();
        stage3.act();
        stage3.draw();

        //   Gdx.app.log(TAG, "render metod executed succussfully");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "Disposing assests");
        batch.dispose();
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
                    SwitchToScreen(new MainMenuScreen(assests));   //Quit Button
                } else {
                    SwitchToScreen(new GamePlayScreen(assests));
                }
            }
        };
        dialog.button(btnQuit, Integer.valueOf(-1));
        dialog.button(btnCancel, Integer.valueOf(0));
        dialog.button(btnRestart, Integer.valueOf(1));
        dialog.show(stage2);
        //dialog.padLeft(50.0f);
        // dialog.padRight(50.0f);

         /*  Dialog dialog= new Dialog("Are you sure want to Exit",skin){
            @Override
            public float getPrefWidth() {
                return width/2;
            }

            @Override
            public float getPrefHeight() {
               return height/2;
            }
        };
        dialog.setModal(false);
        dialog.setMovable(false);
        dialog.setResizable(false);
        dialog.setFillParent(false);
        btnQuit.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG,"Quit Button Pressed");

            return true;
            }
        });
        float btnsize=40.0f;
        Table table=new Table();
        table.add(btnQuit).width(btnsize).height(btnsize);
        table.add(btnRestart).width(btnsize).height(btnsize);
        table.add(btnCancel).width(btnsize).height(btnsize);
        dialog.getButtonTable().add(table).center();
        dialog.show(stage).setPosition(width/4,height/4);
        stage.addActor(dialog);
        */


    }
}
