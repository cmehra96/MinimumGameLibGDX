package in.chetanmehra.minimum.Screens.SubScreens;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import in.chetanmehra.minimum.GameHelpers.Assests;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class Instructions extends Group {
    private Assests assests;
    private boolean isvisible = false;
    float width, height;

    public Instructions() {

    }

    public Instructions(float width, float height, Assests assests) {
        this.width = width;
        this.height = height;
        setWidth(width);
        setHeight(height);
        this.assests = assests;
        Image backgroundImage = new Image(assests.manager.get(Assests.backgroundImageTexture));
        backgroundImage.setPosition(-width, 0.0f);
        addActor(backgroundImage);
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter = Assests.parameter;
        freeTypeFontParameter.size = 25;
        freeTypeFontParameter.color = Color.BLACK;
        labelStyle.font = Assests.generator.generateFont(freeTypeFontParameter);
        labelStyle.fontColor = Color.BLACK;
        Label label = new Label("\n-- How to Play --\n\nCallbreak is played among exactly four players with 52 cards. \nIn each deal 13 cards are dealt to each player in each ROUND. \nThere are total Five rounds in the game. Cards are dealt facing upside down \nto each player in anti-clockwise direction to every player. \n\n- After Dealing is done BIDS are collected from each player. \nThe players after checking their cards bid with the expected number of \nwinning hands. If player fails to secure the bid, the negative score of \nthe bid number is assigned to the player in that round.\n\n- The Player to the dealers right leads the round by playing the first trick and \nthe turns follow in anti-clockwise direction following the same suite of cards.\n\n- Among the four cards in the trick the Highest card of the trick-suite wins. \nIf the user doesn't have any card of that suite He can ether use \nTrump card (Spade) or any card if he doesn't have any card from Trump suite.\n\n- Player with Highest Rank wins the trick and one point per trick is given to \nthe player. Thus, in the entire round if the user secures the number of tricks \nhe expected in the bid. He gets the points that he bid and any \nadditional tricks(Often called OT or Over Tricks) worth 0.1 each. \nIf player bids 3 and  wins 5 tricks his score is recorded as 3.2 in that round. \nHowever if he wins less then 3 tricks his score in that round becomes -3.\n\n- Scores across 5 rounds are added and highest scorer in 5 rounds wins the Game.\n\nEnjoy Playing Call Break Ace !", labelStyle);
        label.setAlignment(8);
        ScrollPane scrollPane = new ScrollPane(label);
        scrollPane.layout();
        scrollPane.setTouchable(Touchable.enabled);
        Table table = new Table();
        table.setFillParent(true);
        table.add(scrollPane);
        table.setPosition(-width, 0.0f);
        addActor(table);
        Image exitButton = new Image(assests.manager.get(Assests.exitButton));
        exitButton.setPosition(-width / 12.5f, (float) (height - (0.13 * height)));
        exitButton.setTouchable(Touchable.childrenOnly.enabled);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                load();
            }
        });
        addActor(exitButton);


    }

    public void load() {
        if (!isvisible) {
            isvisible = true;
            setPosition(0.0f, 0.0f);
            toFront();
            addAction(Actions.moveTo(width, 0.0f, 0.5f));
            return;
        }
        isvisible = false;
        addAction(Actions.moveTo(2 * width, 0.0f, 0.5f));
    }

    public boolean isIsvisible() {
        return isvisible;
    }
}
