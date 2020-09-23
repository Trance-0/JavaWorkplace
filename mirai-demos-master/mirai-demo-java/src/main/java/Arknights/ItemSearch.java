package Arknights;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import module.FileLoader;

public class ItemSearch {
	private String path;
	private ArrayList<String> Item;
	private ArrayList<String> Filename;
	public ItemSearch() throws IOException {
		if( System.getProperty("os.name").contains("Mac")) {
			path = System.getProperty("user.dir") + "/Resources/Items/";
		}else {
			path = System.getProperty("user.dir") + "\\Resources\\Items\\";
		}
		FileLoader fl=new FileLoader(path+"ItemSearch.txt");
		Item=fl.ReadFileByMap().get("Item");
		Filename=fl.ReadFileByMap().get("Filename");
	}
//	private final String localPath = "/mirai-demos-master.zip_expanded/mirai-demos-master/mirai-demo-java/src/main/java/Item";
	

	public String getAddress(String i) {
		int index = indexOfKey(Item, i);
		if (index > 0) {
//			File file = new File(localPath + i + ".png");
			File file = new File(path
					+ Filename.get(index) + ".png");
			if (file.exists()) {
//				return localPath + i + ".png";
				return file.toString();
			} else {
				return "Image Not Found" + file.toString();
			}
		} else {
			throw new java.lang.IllegalArgumentException();
		}
	}

	private int indexOfKey(ArrayList<String> item2, String i) {
		for (int j = 0; j < item2.size(); j++) {
			String temp = item2.get(j);
			if (i.indexOf(temp) > 0) {
				return j;
			}
		}
		return -1;
	}

//	public static void main(String[] args) {
//		ItemSearch i = new ItemSearch();
//		System.out.print(i.getAddress("鎴戣鐧介┈閱�"));
//	}
}