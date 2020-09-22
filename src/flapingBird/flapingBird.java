package flapingBird;

import com.badlogic.gdx.Game;

public class flapingBird extends Game {
	private ThemeStart i = new ThemeStart(this);
	private ThemeMaingame ii = new ThemeMaingame(this);
	private ThemeEnd iii = new ThemeEnd(this);

	public void showGame() {
		setScreen(ii);
		if (i != null) {
			i.dispose();
			i = null;
		}
	}

	public void showEnd() {
		setScreen(iii);
		if (ii != null) {
			ii.dispose();
			ii = null;
		}
	}

	@Override
	public void create() {
		setScreen(i);
	}

}
