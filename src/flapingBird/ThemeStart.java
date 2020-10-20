package flapingBird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

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

	private Texture textBgTx;
	private Texture cursorTx;
	private TextField myTextField;
	private TextField myPasswordField;

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
		if (Gdx.app.getPreferences("MyPref").getBoolean("isBgmOn", true)) {
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

		boolbgm = new CheckBox("BgmOn   ", boolStyle);
		boolbgm.setPosition(50, 200);
		boolbgm.setSize(boolbgm.getWidth() / 5, boolbgm.getHeight() / 5);
		Gdx.app.getPreferences("MyPref").putBoolean("isBgmOn", true);
		boolbgm.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				System.out.println(boolbgm.isChecked());
				Gdx.app.getPreferences("MyPref").putBoolean("isBgmOn", boolbgm.isChecked());
			}
		});

		boolsound = new CheckBox("SoundOn", boolStyle);
		boolsound.setPosition(50, 300);
		boolsound.setSize(boolsound.getWidth() / 5, boolsound.getHeight() / 5);
		Gdx.app.getPreferences("MyPref").putBoolean("isSoundOn", true);
		boolsound.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				System.out.println(boolsound.isChecked());
				Gdx.app.getPreferences("MyPref").putBoolean("isSoundOn", boolsound.isChecked());
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
				Gdx.app.log("userName", myTextField.getText());
				Gdx.app.log("password", myPasswordField.getText());
				Gdx.app.getPreferences("MyPref").putString("userName", myTextField.getText());
				Gdx.app.getPreferences("MyPref").putString("password", myPasswordField.getText());
				Gdx.app.log("tag", event.toString());
				flapingBird.showGame();

			}
		});
		go.setPosition(50, 100);
		go.setSize(go.getWidth() / 5, go.getHeight() / 5);
		Gdx.input.setInputProcessor(stage);

		textBgTx = new Texture(Gdx.files.internal("TextBox.jpg"));
		cursorTx = createPixMap(24, 20);
		TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		style.background = new TextureRegionDrawable(new TextureRegion(textBgTx));
		style.cursor = new TextureRegionDrawable(new TextureRegion(cursorTx));
		info.getData().setScale(0.6F);
		info.setColor(Color.CYAN);
		style.font = info;
		style.fontColor = new Color(0, 0, 0, 1); // RGBA
		myTextField = new TextField("", style);
		myTextField.setAlignment(Align.center);
//		myTextField.setPosition(左下角x, 左下角y);
		myTextField.setPosition(50, 370);
//		myTextField.setSize(宽度, 高度);
		myTextField.setSize(200, 30);
		myTextField.setMaxLength(16);
		// 默认文字不受限制

		myPasswordField = new TextField("", style);
		myPasswordField.setAlignment(Align.center);
//		myTextField.setPosition(左下角x, 左下角y);
		myPasswordField.setPosition(50, 330);
//		myTextField.setSize(宽度, 高度);
		myPasswordField.setSize(200, 30);
		myPasswordField.setPasswordMode(true); // 开启密码模式
		myPasswordField.setPasswordCharacter('*'); // 设置密码字符
		myPasswordField.setMaxLength(16);
		// 默认文字不受限制

		stage.addActor(go);
		stage.addActor(boolbgm);
		stage.addActor(boolsound);
		stage.addActor(myTextField);
		stage.addActor(myPasswordField);
		batch = new SpriteBatch();
	}

	private Texture createCursorTexture(int w, int h) {
		Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
		pixmap.setColor(0, 0, 0, 1); // RGBA
		pixmap.fill();
		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		return texture;
	}

	private Texture createPixMap(int w, int h) {
		Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
		pixmap.setColor(1, 0.5f, 0.5f, 1); // 设置RGBA的值
		pixmap.setColor(Color.valueOf("FF00EEAA")); // 设置RGBA的16进制值
		pixmap.setColor(Color.RED); // 设置颜色名称
//		绘制圆形
		pixmap.fillCircle(6, 6, 6); // 填充
		pixmap.fillCircle(18, 6, 6); // 填充
		pixmap.fillTriangle(0, 6, 12, 20, 24, 6); // 填充
		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		return texture;
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
		title.draw(batch, "flapingBird", 100, 450);
		batch.draw(createPixMap(100, 100), 50, 50);
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
