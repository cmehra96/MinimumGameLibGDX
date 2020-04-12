package in.chetanmehra.minimum.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import in.chetanmehra.minimum.Utility.BackgroundColor;

public class ScoreBoard {
    Skin skin;
    Container<Table> tableContainer;
    float sw = Gdx.graphics.getWidth();
    float sh = Gdx.graphics.getHeight();
    float cw = sw * 0.7f;
    float ch = sh * 0.5f;
    private Stage stage;
    private Assests assests;
    public ScoreBoard(Stage stage, Assests assests) {
        this.stage = stage;
        this.assests = assests;
        skin = assests.manager.get(Assests.glassySkin);
        tableContainer = new Container<Table>();
    }

    public void displayScoreCard() {
        tableContainer.setSize(cw, ch);
        tableContainer.setPosition(0.0f, 0.0f);
        tableContainer.fillX();

        Table table = new Table(skin);
        table.setDebug(true);


        Label topLabel = new Label("Score Card", skin);
        topLabel.setAlignment(Align.center);
        Slider slider = new Slider(0, 100, 1, false, skin);
        Label anotherLabel = new Label("ANOTHER LABEL", skin);
        anotherLabel.setAlignment(Align.center);

        CheckBox checkBoxA = new CheckBox("Checkbox Left", skin);
        CheckBox checkBoxB = new CheckBox("Checkbox Center", skin);
        CheckBox checkBoxC = new CheckBox("Checkbox Right", skin);

        Table buttonTable = new Table(skin);
        buttonTable.setDebug(true);

        TextButton buttonA = new TextButton("LEFT", skin);
        TextButton buttonB = new TextButton("RIGHT", skin);

        table.row().colspan(3).expandX().fillX();
        table.add(topLabel).fillX();
        table.row().colspan(3).expandX().fillX();
        table.add(slider).fillX();
        table.row().colspan(3).expandX().fillX();
        table.add(anotherLabel).fillX();
        table.row().expandX().fillX();

        table.add(checkBoxA).expandX().fillX();
        table.add(checkBoxB).expandX().fillX();
        table.add(checkBoxC).expandX().fillX();
        table.row().expandX().fillX();
        ;

        table.add(buttonTable).colspan(3);

        buttonTable.pad(16);
        buttonTable.row().fillX().expandX();
        buttonTable.add(buttonA).width(cw / 3.0f);
        buttonTable.add(buttonB).width(cw / 3.0f);
        BackgroundColor backgroundColor = new BackgroundColor("white_color_texture.png");
        backgroundColor.setColor(2, 255, 2, 255); // r, g, b, a
        table.setBackground(backgroundColor);

        tableContainer.setActor(table);
        stage.addActor(tableContainer);
    }
}
