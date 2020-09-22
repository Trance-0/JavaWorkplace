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

public class ThemeEnd implements Screen {
	private flapingBird flapingBird;

	public ThemeEnd(flapingBird t) {
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

	@Override
	public void show() {

		batch = new SpriteBatch();

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
		bgm.play();

		title.getData().setScale(0.9F);
		info.setColor(Color.CYAN);
		title.setColor(Color.valueOf("FF00EEAA"));
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

		setTime += delta;
		animationRegion = inloading.getKeyFrame(setTime);

		// 纹理的画布
		batch.begin();
		bg.draw(batch);
//				batch.draw(animationRegion, bird.getX(), bird.getY());
//		title.draw(batch, "飞飞飞", 120, 400);
		info.draw(batch, "失败!", 120, 300);
		batch.end();
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
