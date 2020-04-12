package in.chetanmehra.minimum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;

import in.chetanmehra.minimum.GameHelpers.Assests;

public class CreditsScreen extends AbstractScreen {
    private static final String ABOUT_TEXT = "Developed by: @Chetan Mehra\nPowered by: " +
            "@libgdx\nSkins: @Raymond \"Raeleus\" Buckley.\nTaught By: @Kashish Chowdhary";
    float textWidth, textHeigh;
    private String TAG = "Credits Screen";
    private Assests assests;
    private Stage stage;
    private int width;
    private int height;
    // private Rectangle bounds;
    private BitmapFont font;
    private SpriteBatch batch;
    private GlyphLayout glyphLayout;


    public CreditsScreen(Assests assests) {
        this.assests = assests;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        FillViewport viewport = new FillViewport(width, height, new OrthographicCamera());
        viewport.update(width, height, true);
        stage = new Stage(viewport);
        //   bounds=new Rectangle(0, viewport.getCamera().viewportHeight*5/8,
        //       viewport.getCamera().viewportWidth, viewport.getCamera().viewportHeight / 4);
        if (width > 900.0f)
            font = Assests.largeFont;
        else
            font = Assests.smallFont;
        batch = new SpriteBatch();
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, ABOUT_TEXT);
        textWidth = glyphLayout.width;
        textHeigh = glyphLayout.height;

    }


    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "Enters render method");
        Gdx.gl.glClearColor(0.187f, 0.246f, 0.621f, 1.0f);
        Gdx.gl.glClear(16384);
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();
        batch.begin();
        font.draw(batch, ABOUT_TEXT, (width - textWidth) / 2.0f, (height - textHeigh));
        batch.end();
    }

    @Override
    public void show() {
        Image backgroundImage = new Image(assests.manager.get(Assests.backgroundImageTexture));
        backgroundImage.setSize(width, height);
        stage.addActor(backgroundImage);


    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void onBackPressed() {

    }
}
