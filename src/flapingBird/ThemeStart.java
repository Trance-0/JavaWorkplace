package flapingBird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//引入处理精灵类的库文件
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ThemeStart implements Screen {
	private flapingBird flapingBird;
	private Stage stage;

	public ThemeStart(flapingBird t) {
		flapingBird = t;
	}

	private float setTime;
	private Texture texture;
	private BitmapFont info, title;
	private Animation inloading;
	private TextureRegion animationRegion;

	private Texture loading;
	private Music bgm;

	private SpriteBatch batch;

	private Sprite bg;

	private CheckBox boolbgm, boolsound;
	private Texture boolOn;
	private Texture boolOff;
	private CheckBox.CheckBoxStyle boolStyle;

	private Button go;
	private Texture goC;
	private Texture goU;
	private Texture goS;
	private Button.ButtonStyle goStyle;

	@Override
	public void show() {
		stage = new Stage();

		setTime = 0;
		bgm = Gdx.audio.newMusic(Gdx.files.internal("sounds\\gameBG.mp3"));
		title = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));
		info = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));

		loading = new Texture("background.png");

//		animationBird.
		int frameW = loading.getWidth() / 3;
		int frameH = loading.getHeight();
		TextureRegion[][] tmp = TextureRegion.split(loading, frameW, frameH);

		inloading = new Animation(0.05F, tmp[0]);
		inloading.setPlayMode(Animation.PlayMode.LOOP);

		texture = new Texture("background.png");
		bg = new Sprite(texture);

		bgm.setLooping(true);
		bgm.setVolume(0.1F);
		if (flapingBird.isBgmOn()) {
			bgm.play();
		}

		title.getData().setScale(0.9F);
		info.setColor(Color.CYAN);
		title.setColor(Color.valueOf("FF00EEAA"));

		boolOn = new Texture(Gdx.files.internal("shortSelected.png"));
		boolOff = new Texture(Gdx.files.internal("shortConfirmed.png"));
		boolStyle = new CheckBox.CheckBoxStyle();
		boolStyle.checkboxOn = new TextureRegionDrawable(new TextureRegion(boolOn));
		boolStyle.checkboxOff = new TextureRegionDrawable(new TextureRegion(boolOff));
		boolStyle.font = info;
//		boolStyle.

		boolbgm = new CheckBox("BgmOn   ", boolStyle);
//		boolbgm.setPosition(50, 200);
//		System.out.println(boolbgm.getWidth());
//		System.out.println(boolbgm.getHeight());
		boolbgm.setSize(boolbgm.getWidth() / 5, boolbgm.getHeight() / 5);
		flapingBird.setBgm(boolbgm.isChecked());
		boolbgm.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				System.out.println(boolbgm.isChecked());
				flapingBird.setBgm(boolbgm.isChecked());
			}
		});

		boolsound = new CheckBox("SoundOn", boolStyle);
		boolsound.setPosition(50, 300);
		boolsound.setSize(boolsound.getWidth() / 5, boolsound.getHeight() / 5);
//		System.out.println(boolsound.getWidth());
//		System.out.println(boolsound.getHeight());
		flapingBird.setSound(boolsound.isChecked());
		boolsound.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				System.out.println(boolsound.isChecked());
				flapingBird.setSound(boolsound.isChecked());
			}
		});

		goC = new Texture(Gdx.files.internal("longConfirmed.png"));
		goS = new Texture(Gdx.files.internal("longSelected.png"));
		goU = new Texture(Gdx.files.internal("longUnselected.png"));
		goStyle = new Button.ButtonStyle();
		goStyle.up = new TextureRegionDrawable(new TextureRegion(goU));
		goStyle.over = new TextureRegionDrawable(new TextureRegion(goS));
		goStyle.down = new TextureRegionDrawable(new TextureRegion(goC));
		go = new Button(goStyle);
		go.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("tag", event.toString());
				flapingBird.showGame();

			}
		});
		go.setPosition(50, 100);
		go.setSize(go.getWidth() / 5, go.getHeight() / 5);
		Gdx.input.setInputProcessor(stage);

		stage.addActor(go);
		stage.addActor(boolbgm);
		stage.addActor(boolsound);
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		stage.act(delta);
		setTime += delta;
		animationRegion = inloading.getKeyFrame(setTime);

		// 纹理的画布
		batch.begin();
		bg.draw(batch);
//				batch.draw(animationRegion, bird.getX(), bird.getY());
		title.draw(batch, "flapingBird", 100, 400);
		batch.end();
		stage.draw();
//		if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
//			flapingBird.showGame();
//		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		// 销毁画布,释放空间
		if (bgm != null) {
			bgm.dispose();
		}
		if (texture != null) {
			texture.dispose();
		}
	}

}
