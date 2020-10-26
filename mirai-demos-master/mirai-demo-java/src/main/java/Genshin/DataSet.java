package Genshin;

import java.util.ArrayList;
import java.util.HashMap;

import module.FileSaver;

public class DataSet {

	private final String poolList = "";
	private String path;

	private String pool;
	private int totalcount;
	private boolean noup5;
	private boolean noup4;
	private int no5count;
	private int no4count;
	private String userName;
	private HashMap<String, Integer> Items;

	public void setpath() {
		if (System.getProperty("os.name").contains("Mac")) {
			path = System.getProperty("user.dir") + "/Resources/GenshinSavings/";
		} else {
			path = System.getProperty("user.dir") + "\\Resources\\GenshinSavings\\";
		}
	}

	public DataSet(String user) {
		setpath();
		pool = "奔行世间";
		noup5 = false;
		noup4 = false;
		no5count = 0;
		no4count = 0;
		userName = user;
		totalcount = 0;
		Items = new HashMap<String, Integer>();
	}

	public DataSet(HashMap<String, ArrayList<String>> Map) {
		setpath();
		System.out.println(Map);
		Items = new HashMap<String, Integer>();
		for (String i : Map.keySet()) {
			if (i.compareTo("userName") == 0) {
				userName = Map.get("userName").get(0);
			} else if (i.compareTo("pool") == 0) {
				pool = Map.get("pool").get(0);
			} else if (i.compareTo("noup") == 0) {
				noup5 = Boolean.parseBoolean(Map.get("noup").get(0));
				noup4 = Boolean.parseBoolean(Map.get("noup").get(1));
			} else if (i.compareTo("count") == 0) {
				totalcount = Integer.parseInt(Map.get("count").get(0));
				no5count = Integer.parseInt(Map.get("count").get(1));
				no4count = Integer.parseInt(Map.get("count").get(2));
			} else {
				Items.put(i, Integer.parseInt(Map.get(i).get(0)));
			}
		}
	}

	public String getUserName() {
		return userName;
	}

	public boolean setpool(String po) {
		if (poolList.contains(po)) {
			pool = po;
			return true;
		}
		return false;
	}

	public String getpool() {
		return pool;
	}

	public void addtotalcount() {
		totalcount += 1;
	}

	public int gettotalcount() {
		return totalcount;
	}

	public void addno5count() {
		no5count += 1;
	}

	public int getno5() {
		return no5count;
	}

	public void reset5() {
		no5count = 0;
	}

	public void addno4count() {
		no4count += 1;
	}

	public int getno4() {
		return no4count;
	}

	public void reset4() {
		no4count = 0;
	}

	public boolean isnoup5() {
		return noup5;
	}

	public void setnoup5(boolean set) {
		noup5 = set;
	}

	public boolean isnoup4() {
		return noup4;
	}

	public void setnoup4(boolean set) {
		noup4 = set;
	}

	public HashMap<String, Integer> getItems() {
		return Items;
	}

	public void AddItems(String item) {
		if (Items.containsKey(item)) {
			Items.put(item, Items.get(item) + 1);
		} else {
			Items.put(item, 1);
		}
	}

	public void resetall() {
		noup5 = false;
		noup4 = false;
		no5count = 0;
		no4count = 0;
		totalcount = 0;
		Items = new HashMap<String, Integer>();
	}

	public void toMap() {
		HashMap<String, ArrayList<String>> elements = new HashMap<String, ArrayList<String>>();
		ArrayList<String> name = new ArrayList<String>();
		name.add(this.getUserName());
		elements.put("userName", name);
		ArrayList<String> p = new ArrayList<String>();
		p.add(this.getpool());
		elements.put("pool", p);
		ArrayList<String> count = new ArrayList<String>();
		count.add(Integer.toString(this.gettotalcount()));
		count.add(Integer.toString(this.getno5()));
		count.add(Integer.toString(this.getno4()));
		elements.put("count", count);
		ArrayList<String> noup = new ArrayList<String>();
		noup.add(Boolean.toString(this.isnoup5()));
		noup.add(Boolean.toString(this.isnoup4()));
		elements.put("noup", noup);
		if (Items != null) {
			for (String i : getItems().keySet()) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(Integer.toString(getItems().get(i)));
				elements.put(i, temp);
			}
		}
		FileSaver fs = new FileSaver(path, userName);
		fs.SaveMap(elements);
	}

}
