package in.chetanmehra.minimum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;

import in.chetanmehra.minimum.GameHelpers.Assests;

public class InstructionsScreen extends AbstractScreen {

    private Stage stage;
    private Table screenTitle;
    private Table instruction;
    private Assests assests;
    private int width;
    private int height;
    private final String bullet = "\u2022";
    private float fontsize;

    public InstructionsScreen(Assests assests) {
        this.assests = assests;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        FillViewport viewport = new FillViewport(width, height, new OrthographicCamera());
        viewport.update(width, height, true);
        stage = new Stage(viewport);
        skin = assests.manager.get(Assests.glassySkin);
        if (height < 700f)
            fontsize = 1.5f;
        else
            fontsize = 3.0f;
        createScreen();
    }

    private void createScreen() {
        Image backgroundImage = new Image(assests.manager.get(Assests.backgroundImageTexture));
        backgroundImage.setSize(width, height);
        stage.addActor(backgroundImage);
        Label title = new Label("Instructions", skin);
        title.setFontScale(3);
        instruction = new Table();
        instruction.setFillParent(true);
        instruction.top();
        instruction.add(title).expandX().padTop(0.05f * height);
        instruction.row();
        Label label1 = new Label(bullet + " It is a 6 players game", skin);
        label1.setFontScale(fontsize);
        instruction.add(label1).expandX().left();
        instruction.row();
        Label label2 = new Label(bullet + " In the start of game each player will have 2 cards", skin);
        label2.setFontScale(fontsize);
        instruction.add(label2).expandX().left();
        instruction.row();
        Label label3 = new Label(bullet + " There are two deck on the table,one with dealt cards and other with discarded cards", skin);
        label3.setFontScale(fontsize);
        label3.setWrap(true);
        instruction.add(label3).expandX().width(width);
        instruction.row();
        Label label4 = new Label(bullet + " There are 5 rounds in each set and 5 set in each game", skin);
        label4.setFontScale(fontsize);
        label4.setWrap(true);
        instruction.add(label4).expandX().width(width);
        instruction.row();
        Label label5 = new Label(bullet + " User can exchange cards can either exchange cards from either deck or call minimum", skin);
        label5.setFontScale(fontsize);
        label5.setWrap(true);
        instruction.add(label5).expandX().width(width);
        instruction.row();
        Label label6 = new Label(bullet + " If Minimum called player wins the round, score of cards in hand added to each respective player total score, else twice of" +
                "called player hand added to his/her score", skin);
        label6.setFontScale(fontsize);
        label6.setWrap(true);
        instruction.add(label6).expandX().width(width);
        instruction.row();
        Label label7 = new Label(bullet + " Player can remove \"Three of Kind cards or Three Straight cards\". ", skin);
        label7.setFontScale(fontsize);
        label7.setWrap(true);
        instruction.add(label7).expandX().width(width);
        instruction.row();
        Label label8 = new Label(bullet + "Each cards has its own value, with Ace lowest and King highest", skin);
        label8.setFontScale(fontsize);
        label8.setWrap(true);
        instruction.add(label8).expandX().width(width);
        instruction.row();
        stage.addActor(instruction);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.187f, 0.246f, 0.621f, 1.0f);
        Gdx.gl.glClear(16384);
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();

    }

    @Override
    public void onBackPressed() {

    }
}
