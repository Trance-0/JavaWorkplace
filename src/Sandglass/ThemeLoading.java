package Sandglass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;

/**
 * 游戏主程序的入口类, 实现 ApplicationListener 接口
 */
public class ThemeLoading implements Screen {
	private Sandglass sandglass;
	private Preferences pref;
	private String userName;
	private String password;

	public ThemeLoading(Sandglass t) {
		sandglass = t;
	}

	@Override
	public void show() {
		pref = Gdx.app.getPreferences("SandglassPref");
		userName = pref.getString("userName", "");
		password = pref.getString("password", "");
		Gdx.app.log("UserName", userName);
		Gdx.app.log("Password", password);
		sandglass.setPreference(pref);
	}

	@Override
	public void render(float delta) {
		sandglass.showMain();
//		if (userName != "" && password != "") {
//			sandglass.showMain();
//		} else {
//			sandglass.showLogin();
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
	}
}