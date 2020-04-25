package in.chetanmehra.minimum.Screens.SubScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import in.chetanmehra.minimum.GameHelpers.Assests;
import in.chetanmehra.minimum.Utility.MyActor;

public class ScoreBoard extends Group {

    private FillViewport viewport;
    private Camera camera;
    private SpriteBatch batch;
    private Assests assests;
    private boolean isVisible = false;
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    float scoreboard_startingX = 0f;
    float scoreboard_startingY = 0f;
    float scoreboard_endingX = 0.0f;
    float scoreboard_endingY = 0.0f;
    float coloumn_gap = 0.0f;
    float row_gap = 0.0f;
    ArrayList<Integer> playerscore = new ArrayList<>();
    private final ScheduledThreadPoolExecutor asyncTaskExecutor = new ScheduledThreadPoolExecutor(1);

    public ScoreBoard() {

    }


    public ScoreBoard(FillViewport viewport, OrthographicCamera camera, SpriteBatch batch, Assests assests) {
        this.viewport = viewport;
        this.camera = camera;
        this.batch = batch;
        this.assests = assests;
        Image scorebackground = new Image(assests.manager.get(Assests.scoreCardBackground));
        scorebackground.setSize((float) (width * .75), (float) (height * 0.75));
        scorebackground.setPosition((float) (0.125 * width), (float) (0.2 * height));
        scoreboard_startingX = scorebackground.getX();
        scoreboard_startingY = scorebackground.getY();
        scoreboard_endingX = scoreboard_startingX + scorebackground.getWidth();
        scoreboard_endingY = scoreboard_startingY + scorebackground.getHeight();
        coloumn_gap = scorebackground.getWidth() / 6;  // divided by no of players
        row_gap = scorebackground.getHeight() / 8;
        scorebackground.setTouchable(Touchable.enabled);
        scorebackground.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                float touched_x = event.getStageX();
                float touched_y = event.getStageY();


            }
        });
        addActor(scorebackground);
        addActor(new MyActor(scoreboard_endingX / 2, scoreboard_endingY - (float) (0.02 * scoreboard_endingY), "Score-Board", 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + coloumn_gap / 4), (float) (scoreboard_endingY - row_gap), "You", 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + coloumn_gap), (float) (scoreboard_endingY - row_gap), "Bot 1", 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + 2 * coloumn_gap), (float) (scoreboard_endingY - row_gap), "Bot 2", 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + 3 * coloumn_gap), (float) (scoreboard_endingY - row_gap), "Bot 3", 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + 4 * coloumn_gap), (float) (scoreboard_endingY - row_gap), "Bot 4", 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + 5 * coloumn_gap), (float) (scoreboard_endingY - row_gap), "Bot 5", 24, Color.WHITE));
        Image line = new Image(assests.manager.get(Assests.line));
        line.setSize((float) (width * .75), (float) (height * 0.01));
        line.setPosition((float) (scoreboard_startingX), (float) (scoreboard_endingY - 2 * row_gap));
        //line.setRotation(180);
        addActor(line);
        setVisible(false);

    }

    public void setPlayerscore(ArrayList<Integer> playerscore) {
        this.playerscore = playerscore;
    }

    public void load() {
        Gdx.app.log("ScoreBoardClass", "load method");
        //addAction(Actions.moveTo(200.0f, 200.0f, 0.3f));
        if (!isVisible) {

            setVisible(true);
            Gdx.app.log("ScoreBoardClass", "inside if condition");
            toFront();
            return;
        }
        isVisible = false;
        setVisible(false);
    }

    public void addScoreRow(ArrayList<Integer> playerRoundScore, int roundCounter) {
        int currentrow = 2 + roundCounter;
        addActor(new MyActor((float) (scoreboard_startingX + coloumn_gap / 4), (float) (scoreboard_endingY - currentrow * row_gap), playerRoundScore.get(0).toString(), 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + coloumn_gap), (float) (scoreboard_endingY - currentrow * row_gap), playerRoundScore.get(1).toString(), 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + 2 * coloumn_gap), (float) (scoreboard_endingY - currentrow * row_gap), playerRoundScore.get(2).toString(), 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + 3 * coloumn_gap), (float) (scoreboard_endingY - currentrow * row_gap), playerRoundScore.get(3).toString(), 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + 4 * coloumn_gap), (float) (scoreboard_endingY - currentrow * row_gap), playerRoundScore.get(4).toString(), 24, Color.WHITE));
        addActor(new MyActor((float) (scoreboard_startingX + 5 * coloumn_gap), (float) (scoreboard_endingY - currentrow * row_gap), playerRoundScore.get(5).toString(), 24, Color.WHITE));

    }
}
