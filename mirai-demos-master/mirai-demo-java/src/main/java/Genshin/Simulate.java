package Genshin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import module.FileLoader;

public class Simulate {

	private String path;
	private HashMap<String, ArrayList<String>> dir;
	private String title;
	private ArrayList<String> grade5;
	private ArrayList<String> grade4;
	private ArrayList<String> grade3;

	private ArrayList<String> upCharacter5;
	private ArrayList<String> upCharacter4;
	private int upthreshold5;
	private int upthreshold4;

	private int threshold5;
	private int threshold4;

	private boolean isLimited;

	public Simulate() {
		if (System.getProperty("os.name").contains("Mac")) {
			path = System.getProperty("user.dir") + "/Resources/GenshinSavings/";
		} else {
			path = System.getProperty("user.dir") + "\\Resources\\GenshinSavings\\";
		}
	}

	public void Init(String pool) throws IOException {
		String temp;
		if (System.getProperty("os.name").contains("Mac")) {
			temp = System.getProperty("user.dir") + "/Resources/textFile/"+ pool + ".txt";
		} else {
			temp = System.getProperty("user.dir") + "\\Resources\\textFile\\"+ pool + ".txt";
		}
		FileLoader fl = new FileLoader(temp);
		dir = fl.ReadFileByMap();
		System.out.print(dir);
		title = dir.get("title").get(0);
		grade5 = dir.get("grade5");
		grade4 = dir.get("grade4");
		grade3 = dir.get("grade3");

		upCharacter5 = dir.get("upCharacter5");
		upCharacter4 = dir.get("upCharacter4");
		upthreshold5 = Integer.parseInt(dir.get("upthreshold").get(0));
		upthreshold4 = Integer.parseInt(dir.get("upthreshold").get(1));

		threshold5 = Integer.parseInt(dir.get("threshold").get(0));
		threshold4 = Integer.parseInt(dir.get("threshold").get(1));

		isLimited = Boolean.parseBoolean(dir.get("isLimited").get(0));

	}

	private ArrayList<String> core(DataSet d) {
		Random rand = new Random();
		int pos = rand.nextInt(1000) + 1;
		int up = rand.nextInt(100) + 1;
		if (pos < threshold5 || d.getno5() >= 89) {
			d.addno4count();
			d.reset5();
			if ((up < upthreshold5 || d.isnoup5()) && upCharacter5.size() > 0) {
				d.setnoup5(false);
				return upCharacter5;
			}
			d.setnoup5(true);
			return grade5;
		} else if (pos < threshold4 || d.getno4() >= 9) {
			d.addno5count();
			d.reset4();
			if ((up < upthreshold4 || d.isnoup5()) && upCharacter4.size() > 0) {
				d.setnoup4(false);
				return upCharacter4;
			}
			d.setnoup4(true);
			return grade4;
		} else {
			d.addno5count();
			d.addno4count();
			return grade3;
		}
	}

	public String pickoneFrom(ArrayList<String> p) {
		Random rand = new Random();
		return p.get(rand.nextInt(p.size()));
	}

	public DataSet loadSaving(String userName) throws IOException {
		DataSet temp = null;
		FileLoader fl = new FileLoader(path + userName + ".txt");
		temp = new DataSet(fl.ReadFileByMap());
//		System.out.println(temp.getpool());
		if (temp.getUserName() == null) {
			// TODO Auto-generated catch block
			temp = new DataSet(userName);
		}
		return temp;
	}

	public String simulate(String user, int times) throws IOException {
		DataSet temp = null;
		FileLoader fl = new FileLoader(path + user + ".txt");
		temp = new DataSet(fl.ReadFileByMap());
		System.out.println(temp.getpool());
		if (temp.getUserName() == null) {
			// TODO Auto-generated catch block
			temp = new DataSet(user);
		}

		Init(temp.getpool());

		StringBuilder result = new StringBuilder();
		result.append("卡池：" + title + " 用户：" + temp.getUserName() + " 已抽卡：" + temp.gettotalcount() + "次。 " + " 未抽中5星次数："
				+ temp.getno5() + " 未抽中4星次数：" + temp.getno4() + "\n");
		for (int i = 0; i < times; i++) {
			temp.addtotalcount();
			ArrayList<String> p = core(temp);
			System.out.print(" ");
			if (p.equals(grade5) || p.equals(upCharacter5)) {
				String item = pickoneFrom(p);
				temp.AddItems(item);
				result = result.append(" [★★★★★ " + item + "] ");
			} else if (p.equals(grade4) || p.equals(upCharacter4)) {
				String item = pickoneFrom(p);
				temp.AddItems(item);
				result = result.append(" [★★★★ " + item + "] ");
			} else {
				String item = pickoneFrom(p);
				temp.AddItems(item);
				result = result.append(" [★★★ " + item + "] ");
			}
		}
		temp.toMap();
//		System.out.println(temp.getItems());
		return result.toString();
	}

//	public static void main(String[] args) throws IOException {
//		Simulate sim = new Simulate();
//		System.out.println(sim.simulate("Admin", 10));
//	}
}