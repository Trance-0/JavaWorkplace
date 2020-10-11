package flapingBird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

public class flapingBird extends Game {
	private FileHandle fh;
	private ThemeLoading zero;
	private ThemeStart i;
	private ThemeMaingame ii;
	private ThemeEnd iii;
	private int bestScore;
	private int score = 0;
	private Preferences prefs;

	public String readFile(String filename) {
		FileHandle lf = Gdx.files.local("/files/" + filename + ".txt");
		String text = lf.readString();
//		Gdx.app.log("Strs", text);
		return text;
	}

	public void setPreference(Preferences p) {
		prefs = p;
	}

	public void saveFile(String filename, String input) {
		FileHandle file = Gdx.files.local("/files/" + filename + ".txt");
		file.writeString(input, false);
		// false erase all
		// true add to end
	}

	public void updateBestScore() {
		if (score > bestScore) {
			bestScore = score;
		}
//		saveFile("bestScore", Integer.toString(bestScore));
		prefs.putInteger("bestScore", bestScore);
		prefs.flush();
	}

	public void setBestScore(int input) {
		bestScore = input;
	}

	public int getScore() {
		return score;
	}

	public void addScore() {
		score += 1;
	}

	public void resetScore() {
		score = 0;
	}

	private void resetTheme() {
		if (zero != null) {
			zero.dispose();
			zero = null;
		}
		if (iii != null) {
			iii.dispose();
			iii = null;
		}
		if (ii != null) {
			ii.dispose();
			ii = null;
		}
		if (i != null) {
			i.dispose();
			i = null;
		}
	}

	public void showGame() {
		resetTheme();
		ii = new ThemeMaingame(this);
		setScreen(ii);
	}

	public void showEnd() {
		resetTheme();
		iii = new ThemeEnd(this);
		setScreen(iii);
	}

	public void showStart() {
		resetTheme();
		i = new ThemeStart(this);
		setScreen(i);
	}

	@Override
	public void create() {
		resetTheme();
		zero = new ThemeLoading(this);
		setScreen(zero);
	}

}
