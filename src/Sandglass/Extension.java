package Sandglass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Extension {
	private FileHandle fh;

	public String readFile(String filename) {
		FileHandle lf = Gdx.files.local("/files/" + filename + ".txt");
		String text = lf.readString();
//		Gdx.app.log("Strs", text);
		return text;
	}

	public void saveFile(String filename, String input) {
		FileHandle file = Gdx.files.local("/files/" + filename + ".txt");
		file.writeString(input, false);
		// false erase all
		// true add to end
	}

}
