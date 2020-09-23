package module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class music {
	private HashMap<String, String> soundList;
	private ArrayList<String> musicName;
	private Random rand;

	public music() {
		rand = new Random();
		musicName = new ArrayList<String>();
		soundList = new HashMap<String, String>();
		FileLoader f = new FileLoader(System.getProperty("user.dir") + "\\Resources\\textFile\\soundURL.txt");
		ArrayList<String> temp = null;
		try {
			temp = f.ReadFileByLine();
			for (String i : temp) {
				String soundName = i.substring(i.lastIndexOf("/") + 1);
				musicName.add(soundName);
				soundList.put(soundName, i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String pickaSong() {
		int index = rand.nextInt(soundList.keySet().size());
		int k = 0;
		for (String i : soundList.keySet()) {
			if (k == index) {
				return "Name of episode:" + i + "\n" + soundList.get(i);
			}
			k++;
		}
		return null;
	}

	public String SearchSong(String name) {
		if (soundList.containsKey(name)) {
			return soundList.get(name);
		}
		return "Sound Not Found.";
	}

	public static void main(String[] args) {
		music m = new music();
		System.out.print(m.pickaSong());
	}
}
