package in.chetanmehra.minimum.Utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

import in.chetanmehra.minimum.GameHelpers.Assests;

public class MyActor extends Actor {

    private BitmapFont font;
    private String text;
    float x;
    float y;

    public MyActor(float x, float y, String str, int i, Color color) {
        this.x = x;
        this.y = y;
        text = str;
        FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.size = i;
        freeTypeFontParameter.color = color;
        font = Assests.generator.generateFont(freeTypeFontParameter);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, text, x, y);
    }
}
