package flapingBird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * 游戏主程序的入口类, 实现 ApplicationListener 接口
 */
public class ThemeLoading implements Screen {
	private flapingBird flapingBird;
	private Stage stage;
	private Preferences mypref;

	public ThemeLoading(flapingBird t) {
		flapingBird = t;
	}

	@Override
	public void show() {
		mypref = Gdx.app.getPreferences("MyPref");
		flapingBird.setPreference(mypref);
		flapingBird.setBestScore(mypref.getInteger("bestScore", -1));
		flapingBird.setBestScore(mypref.getInteger("bestScore", -1));
		Gdx.app.log("bestScore", Integer.toString(mypref.getInteger("bestScore", -1)));

	}

	@Override
	public void render(float delta) {
		flapingBird.showStart();
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
	}
}