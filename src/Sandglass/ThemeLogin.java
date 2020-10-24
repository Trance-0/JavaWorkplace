package Sandglass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class ThemeLogin implements Screen {
	/*
	 * 登录界面 用户名【TextField】 密码【TextField】 登录【Button】 找回密码【Button】 注册【Button】 ​ 默认记住密码
	 */

	private Sandglass sandglass;
	private Stage stage;

	private BitmapFont font;

	private Texture texture;
	private TextField userName;
	private TextField password;

	private Button defaultButton;
	private Texture defaultC;
	private Texture defaultU;
	private Texture defaultS;
	private Button.ButtonStyle defaultStyle;

	public ThemeLogin(Sandglass s) {
		// TODO Auto-generated constructor stub
		sandglass = s;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		font = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

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

	}

}
