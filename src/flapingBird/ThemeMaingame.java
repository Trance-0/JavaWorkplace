package flapingBird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//引入处理精灵类的库文件
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * 游戏主程序的入口类, 实现 ApplicationListener 接口
 */
public class ThemeMaingame implements Screen {
	private flapingBird flapingBird;
	private Stage stage;

	public ThemeMaingame(flapingBird t) {
		flapingBird = t;
	}

	private BitmapFont scoreText, fpsText;
	private Music bgm;
	// 纹理的画布
	private SpriteBatch batch;
	// 纹理
	private Texture texture;

	// 精灵类
	private Sprite bg;

	private ActorBird bbb;
	private ActorPillar pillar;

	@Override
	public void show() {
		stage = new Stage();
		bbb = new ActorBird();
		pillar = new ActorPillar(bbb);
		bgm = Gdx.audio.newMusic(Gdx.files.internal("sounds\\gameBG.mp3"));
		scoreText = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));
		fpsText = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));
		batch = new SpriteBatch();
		texture = new Texture("background.png");
		bg = new Sprite(texture);
		bgm.setLooping(true);
		bgm.setVolume(0.1F);
		bgm.play();
		scoreText.getData().setScale(0.9F);
		fpsText.setColor(Color.CYAN);
		scoreText.setColor(Color.valueOf("FF00EEAA"));
		Gdx.app.log("Texture Width", texture.getWidth() + "px");
		Gdx.app.log("Texture Height", texture.getHeight() + "px");
		stage.addActor(bbb);
		stage.addActor(pillar);
	}

	@Override
	public void render(float delta) {
		/*
		 * 设置清屏颜色为红色（RGBA）,
		 * 
		 * LibGDX 中使用 4 个浮点类型变量（值范围 0.0 ~ 1.0）表示一个颜色（分别表示颜色的 RGBA 四个通道）,
		 * 
		 * 十六进制颜色与浮点颜色之间的转换: 将十六进制颜色的每一个分量除以 255 得到的浮点数就是浮点颜色对应的通道值。
		 */
		stage.act(delta);
		// 纹理的画布
		batch.begin();
		bg.draw(batch);
		scoreText.draw(batch, "分数:" + Double.toString((double) pillar.score / 2), 10, 500);
		fpsText.draw(batch, "FPS:" + Gdx.graphics.getFramesPerSecond(), 10, 400);
		batch.end();

		stage.draw();
		if (pillar.gameover)
			flapingBird.showEnd();
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