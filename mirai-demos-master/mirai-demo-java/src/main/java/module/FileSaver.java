package module;

import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.util.ArrayList;
import java.util.HashMap;

public class FileSaver {

	private String defaultSpliterOfList=":";
	private String defaultSpliterOfElement=",";
	private String path;
	private String title;

	public FileSaver(String abspath,String filetitle) {
		path = abspath;
		title =filetitle;
	}
	
	public FileSaver(String filetitle) {
		if( !System.getProperty("os.name").contains("Win")) {
			path = System.getProperty("user.dir") + "/Resources/";
		}else {
			path = System.getProperty("user.dir") + "\\Resources\\";
		}
		title =filetitle;
	}

	public void SaveFile(String input) {
		try {
			FileWriter myWriter = new FileWriter(path + title + ".txt");
			myWriter.write(input);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	//save Memo
	public void SaveMemo(Memo m) {
		try {
			FileWriter myWriter = new FileWriter(path + m.getTitle() + ".txt");
			myWriter.write("[" + m.getTitle() + "]" + "\n");
			myWriter.write("*" + m.getDate() + "*" + "\n");
			myWriter.write(m.getContent());
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	//save HashMap<String, ArrayList<String>>
	public void SaveMap(HashMap<String, ArrayList<String>> hm) {
		try {
			FileWriter myWriter = new FileWriter(path + title + ".txt");
			for (String i : hm.keySet()) {
				myWriter.write(i + defaultSpliterOfList);
				ArrayList<String> temp = hm.get(i);
				for (int j = 0; j < temp.size(); j++) {
					myWriter.write(temp.get(j) + defaultSpliterOfElement);
				}
				myWriter.write("\n");
			}
			System.out.println("Successfully wrote to the file.");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	//save HashMap<ArrayList<String>, String>
	public void SaveDirMap(HashMap<ArrayList<String>, String> hm) {
		try {
			FileWriter myWriter = new FileWriter(path + title + ".txt");
			for (ArrayList<String> i : hm.keySet()) {
				ArrayList<String> temp = i;
				for (int j = 0; j < temp.size(); j++) {
					myWriter.write(temp.get(j) + defaultSpliterOfElement);
				}
				myWriter.write(defaultSpliterOfList + hm.get(i));
				myWriter.write("\n");
			}
			System.out.println("Successfully wrote to the file.");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	//save ArrayList<String>
	public void SaveArray(ArrayList<String> hm) {
		try {
			FileWriter myWriter = new FileWriter(path + hm.get(0) + ".txt");
			for (int i = 0; i < hm.size(); i++) {
				myWriter.write(hm.get(i) + "\n");
			}
			System.out.println("Successfully wrote to the file.");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	//make a HashMap<String, ArrayList<String>>
	public HashMap<String, ArrayList<String>> makeaMap(String[] name, ArrayList<String>[] elements) {
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < name.length; i++) {
			result.put(name[i], elements[i]);
		}
		return result;
	}

	//make a HashMap<String, ArrayList<String>>
	public HashMap<String, ArrayList<String>> makeaMap(String[] name, String[][] elements) {
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < name.length; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			for (String j : elements[i]) {
				temp.add(j);
			}
			result.put(name[i], temp);
		}
		return result;
	}

	//make a HashMap<ArrayList<String>, String>
	public HashMap<ArrayList<String>, String> makeaDirMap(String[][] elements, String[] name) {
		HashMap<ArrayList<String>, String> result = new HashMap<ArrayList<String>, String>();
		for (int i = 0; i < name.length; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			for (String j : elements[i]) {
				temp.add(j);
			}
			result.put(temp, name[i]);
		}
		return result;
	}
}