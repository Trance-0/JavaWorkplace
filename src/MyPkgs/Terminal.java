package MyPkgs;

import com.badlogic.gdx.Game;

public class Terminal extends Game {
	private Startpage i = new Startpage(this);
	private Gamepage ii = new Gamepage(this);
	private Endpage iii = new Endpage(this);

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
