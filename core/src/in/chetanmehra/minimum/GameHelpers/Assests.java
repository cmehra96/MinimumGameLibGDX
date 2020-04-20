package in.chetanmehra.minimum.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Arrays;
import java.util.List;

public class Assests {
    public static Assests assests;
    public static final AssetDescriptor<Texture> cardBackImage = new AssetDescriptor<Texture>("images/cards/blueback.png", Texture.class);
    public static final List<AssetDescriptor<Texture>> cards = Arrays.asList(
            new AssetDescriptor<Texture>("images/cards/clubsace.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs2.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs3.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs4.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs5.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs6.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs7.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs8.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs9.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubs10.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubsjack.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubsqueen.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/clubsking.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spadesace.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades2.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades3.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades4.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades5.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades6.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades7.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades8.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades9.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spades10.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spadesjack.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spadesqueen.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/spadesking.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/heartsace.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts2.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts3.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts4.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts5.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts6.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts7.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts8.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts9.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/hearts10.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/heartsjack.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/heartsqueen.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/heartsking.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamondsace.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds2.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds3.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds4.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds5.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds6.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds7.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds8.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds9.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamonds10.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamondsjack.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamondsqueen.png", Texture.class),
            new AssetDescriptor<Texture>("images/cards/diamondsking.png", Texture.class)


    );
    public static final AssetDescriptor<Texture> backgroundImageTexture = new AssetDescriptor<Texture>("images/background.png", Texture.class);
    public static final AssetDescriptor<Texture> callMinimumBtn = new AssetDescriptor<Texture>("images/call_button_up.png", Texture.class);
    public static final AssetDescriptor<Skin> neonSkin = new AssetDescriptor<Skin>("skin/neon/neon-ui.json", Skin.class);
    public static final AssetDescriptor<Skin> glassySkin = new AssetDescriptor<Skin>("skin/glassy/glassy-ui.json", Skin.class);
    public static final AssetDescriptor<Texture> exitButton = new AssetDescriptor<Texture>("images/icon-buttons/exit.png", Texture.class);
    public static final AssetDescriptor<Texture> scoreCardBackground = new AssetDescriptor<Texture>("images/scorebackground.png", Texture.class);
    public static final AssetDescriptor<Texture> line = new AssetDescriptor<Texture>("images/lineborder.png", Texture.class);
    public static FreeTypeFontGenerator generator;
    public static FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public static BitmapFont smallFont;
    public static BitmapFont smallestFont;
    public static BitmapFont largeFont;
    public AssetManager manager = new AssetManager();

    public void load() {
        manager.load(cardBackImage);
        for (AssetDescriptor<Texture> card : cards) {
            manager.load(card);

        }
        manager.load(backgroundImageTexture);
        manager.load(scoreCardBackground);
        manager.load(line);
        manager.load(callMinimumBtn);
        manager.load(exitButton);
        manager.load(neonSkin);
        manager.load(glassySkin);

        //  manager.load(checkIcon);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("bitmapfont/Font.ttf"));

        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        smallFont = generator.generateFont(parameter);
        smallFont.setColor(.21f, .22f, .21f, 1f);
        parameter.size = 72;
        largeFont = generator.generateFont(parameter);
        largeFont.setColor(.21f, .22f, .21f, 1f);
        parameter.size = 24;
        smallestFont = generator.generateFont(parameter);
        smallestFont.setColor(.21f, .22f, .21f, 1f);
        //generator.dispose();
    }

    public void dispose() {
        manager.dispose();
    }
}
