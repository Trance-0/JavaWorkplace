package module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class FileLoader {
	private String defaultSpliterOfList=":";
	private String defaultSpliterOfElement=",";
	private String path;

	public FileLoader(String abspath) {
		path = abspath;
	}

	//read Arraylist
	public ArrayList<String> ReadFileByLine() throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)),  StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			String data;
			while ((data = br.readLine()) != null) {
				result.add(data);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return result;
	}

	//read HashMap<ArrayList<String>, String> without slipter
	public HashMap<ArrayList<String>, String> ReadFileByDirMap()
			throws IOException {
		HashMap<ArrayList<String>, String> result = new HashMap<ArrayList<String>, String>();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)), StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			String data;
			while ((data = br.readLine()) != null) {
				String key = data.substring(data.indexOf(defaultSpliterOfList) + 1);
				data = data.substring(0, data.indexOf(defaultSpliterOfList));
				ArrayList<String> element = new ArrayList<String>();
				int nextIndex = data.indexOf(defaultSpliterOfElement, 0);
				while (nextIndex > 0) {
					String temp = data.substring(0, nextIndex);
					data = data.substring(nextIndex + 1);
					nextIndex = data.indexOf(defaultSpliterOfElement);
					element.add(temp);
				}
				result.put(element, key);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return result;
	}

	//read HashMap<ArrayList<String>, String>
	public HashMap<ArrayList<String>, String> ReadFileByDirMap(String spliterOfList, String spliterOfElement)
			throws IOException {
		HashMap<ArrayList<String>, String> result = new HashMap<ArrayList<String>, String>();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)), StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			String data;
			while ((data = br.readLine()) != null) {
				String key = data.substring(data.indexOf(spliterOfList) + 1);
				data = data.substring(0, data.indexOf(spliterOfList));
				ArrayList<String> element = new ArrayList<String>();
				int nextIndex = data.indexOf(spliterOfElement, 0);
				while (nextIndex > 0) {
					String temp = data.substring(0, nextIndex);
					data = data.substring(nextIndex + 1);
					nextIndex = data.indexOf(spliterOfElement);
					element.add(temp);
				}
				result.put(element, key);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return result;
	}

	//read HashMap<String, ArrayList<String>> without spliter
		public HashMap<String, ArrayList<String>> ReadFileByMap() throws IOException {
			HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
			try {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)),StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr);
				String data;
				while ((data = br.readLine()) != null) {
					String key = data.substring(0, data.indexOf(defaultSpliterOfList));
					data = data.substring(data.indexOf(defaultSpliterOfList) + 1);
					ArrayList<String> element = new ArrayList<String>();
					int nextIndex = data.indexOf(defaultSpliterOfElement, 0);
					while (nextIndex > 0) {
						String temp = data.substring(0, nextIndex);
						data = data.substring(nextIndex + 1);
						nextIndex = data.indexOf(defaultSpliterOfElement);
						element.add(temp);
					}
					result.put(key, element);
				}
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			return result;
		}
		
	//read HashMap<String, ArrayList<String>> 
	public HashMap<String, ArrayList<String>> ReadFileByMap(String spliterOfList, String spliterOfElement)
			throws IOException {
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)), StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			String data;
			while ((data = br.readLine()) != null) {
				String key = data.substring(0, data.indexOf(spliterOfList));
				data = data.substring(data.indexOf(spliterOfList) + 1);
				ArrayList<String> element = new ArrayList<String>();
				int nextIndex = data.indexOf(spliterOfElement, 0);
				while (nextIndex > 0) {
					String temp = data.substring(0, nextIndex);
					data = data.substring(nextIndex + 1);
					nextIndex = data.indexOf(spliterOfElement);
					element.add(temp);
				}
				result.put(key, element);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return result;
	}
	
	//read Memo with key
		public Memo ReadMemo(String title, String key) throws IOException {
			String time = "";
			StringBuilder content = new StringBuilder();
			try {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path + title + ".txt")),
						StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr);
				AES a = new AES(key);
				int line = 0;
				String data;
				while ((data = br.readLine()) != null) {

					System.out.println(data);
					if (line == 0) {
						title = data;
					} else if (line == 1) {
						time = data;
					} else {
						content.append(a.decrypt(data));
					}
					line++;
				}
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			return new Memo(title, key, time, content.toString());
		}

		//read Memo without key
		public Memo ReadMemo(String title) throws IOException {
			String time = "";
			StringBuilder content = new StringBuilder();
			try {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path + title + ".txt")),
						StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr);
				int line = 0;
				String data;
				while ((data = br.readLine()) != null) {
					if (line == 1) {
						title = data;
					} else if (line == 2) {
						time = data;
					} else {
						content.append(data);
					}
					line++;
				}
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			return new Memo(title, time, content.toString());
		}

}