package flapingBird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class flapingBird extends Game {
	private FileHandle fh;
	private ThemeStart i;
	private ThemeMaingame ii;
	private ThemeEnd iii;
	private boolean bgmOn = true;
	private boolean soundOn = true;
	private int score = 0;

	public flapingBird() {
		FileHandle fh = Gdx.files.internal("asdf.txt");
		score = Integer.parseInt(fh.readString());
	}

	public int getScore() {
		return score;
	}

	public void addScore() {
		score += 1;
	}

	public boolean isBgmOn() {
		return bgmOn;
	}

	public void setBgm(boolean status) {
		bgmOn = status;
	}

	public boolean isSoundOn() {
		return soundOn;
	}

	public void setSound(boolean status) {
		soundOn = status;
	}

	public void showGame() {
		ii = new ThemeMaingame(this);
		setScreen(ii);
		if (i != null) {
			i.dispose();
			i = null;
		}
		if (iii != null) {
			iii.dispose();
			iii = null;
		}
	}

	public void showEnd() {
		iii = new ThemeEnd(this);
		setScreen(iii);
		if (ii != null) {
			ii.dispose();
			ii = null;
		}
		if (i != null) {
			i.dispose();
			i = null;
		}
	}

	@Override
	public void create() {
		i = new ThemeStart(this);
		setScreen(i);
		if (iii != null) {
			iii.dispose();
			iii = null;
		}
		if (ii != null) {
			ii.dispose();
			ii = null;
		}
	}

}
