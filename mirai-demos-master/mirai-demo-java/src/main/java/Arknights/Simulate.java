package Arknights;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import module.FileLoader;
import module.FileSaver;

public class Simulate {

	private String path;
	private HashMap<String, ArrayList<String>> dir;
	private final String title;
	private final ArrayList<String> grade6;
	private final ArrayList<String> grade5;
	private final ArrayList<String> grade4;
	private final ArrayList<String> grade3;

	private final ArrayList<String> upCharacter6;
	private final ArrayList<String> upCharacter5;
	private final ArrayList<String> upCharacter4;
	private final int upthreshold6;
	private final int upthreshold5;
	private final int upthreshold4;

	private int threshold6 = 2;
	private final int threshold5 = 8;
	private final int threshold4 = 50;

	public Simulate() throws IOException {
		if (System.getProperty("os.name").contains("Mac")) {
			path = System.getProperty("user.dir") + "/Resources/ArknightsSaving/";
		} else {
			path = System.getProperty("user.dir") + "\\Resources\\ArknightsSaving\\";
		}
		FileLoader fl = new FileLoader(System.getProperty("user.dir") + "\\Resources\\textFile\\[无拘熔火].txt");
		dir = fl.ReadFileByMap();
		System.out.print(dir);
		title = dir.get("title").get(0);
		grade6 = dir.get("grade6");
		grade5 = dir.get("grade5");
		grade4 = dir.get("grade4");
		grade3 = dir.get("grade3");

		upCharacter6 = dir.get("upCharacter6");
		upCharacter5 = dir.get("upCharacter5");
		upCharacter4 = dir.get("upCharacter4");
		upthreshold6 = Integer.parseInt(dir.get("upthreshold").get(0));
		upthreshold5 = Integer.parseInt(dir.get("upthreshold").get(1));
		upthreshold4 = Integer.parseInt(dir.get("upthreshold").get(2));
	}

	private class DataSet {
		private int totalcount;
		private String userName;
		private int no6 = 0;
		private boolean slbd;

		public DataSet(String user) {
			slbd = true;
			userName = user;
			no6 = 0;
			totalcount = 0;
		}

		public DataSet(ArrayList<String> readFileByLine) {
			slbd = Boolean.parseBoolean(readFileByLine.get(2));
			userName = readFileByLine.get(0);
			no6 = Integer.parseInt(readFileByLine.get(3));
			totalcount = Integer.parseInt(readFileByLine.get(1));
		}

		public void addtotalcount() {
			totalcount += 1;
		}

		public void resetRatio6() {
			no6 = 0;
		}

		public String getUserName() {
			return userName;
		}

		public int getRatio6() {
			return no6;
		}

		public void increaseRatio6() {
			no6 += 1;
		}

		public boolean getslbd() {
			return slbd;
		}

		public void cancleslbd() {
			slbd = false;
		}

		public int getTotalCount() {
			return totalcount;
		}

		public void toMap() {
			ArrayList<String> elements = new ArrayList<String>();
			elements.add(this.getUserName());
			elements.add(Integer.toString(this.getTotalCount()));
			elements.add(Boolean.toString(this.getslbd()));
			elements.add(Integer.toString(this.getRatio6()));
			FileSaver fs = new FileSaver(path, userName);
			fs.SaveArray(elements);
		}
	}

	private ArrayList<String> core(DataSet d) {
		Random rand = new Random();
		int pos = rand.nextInt(100) + 1;
		int up = rand.nextInt(100) + 1;
		if (pos < threshold6) {
			if (up < upthreshold6 && upCharacter6.size() > 0) {
				return upCharacter6;
			}
			return grade6;
		} else if (pos < threshold5) {
			if (up < upthreshold5 && upCharacter5.size() > 0) {
				return upCharacter5;
			}
			return grade5;
		} else if (pos < threshold4) {
			if (up < upthreshold4 && upCharacter4.size() > 0) {
				return upCharacter4;
			}
			return grade4;
		} else {
			return grade3;
		}
	}

	public String pickoneFrom(ArrayList<String> p) {
		Random rand = new Random();
		return p.get(rand.nextInt(p.size()));

	}

	public DataSet loadSaving(String userName) {

		return null;
	}

	public String simulate(String user, int times) {
		System.out.print(path);
		DataSet temp = null;
		try {
			FileLoader fl = new FileLoader(path + user + ".txt");
			temp = new DataSet(fl.ReadFileByLine());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			temp = new DataSet(user);
		}

		StringBuilder result = new StringBuilder();
		result.append("卡池：" + title + " 用户：" + temp.getUserName() + " 已抽卡：" + temp.getTotalCount() + "次。 " + " 未抽中六星次数："
				+ temp.getRatio6() + "\n");
		for (int i = 0; i < times; i++) {
			if (temp.no6 > 50) {
				threshold6 = 2 + (temp.no6 - 50) * 2;
			} else {
				threshold6 = 2;
			}
			temp.addtotalcount();
			ArrayList<String> p = core(temp);
			System.out.print(" ");
			if (temp.getslbd() && temp.getTotalCount() == 9) {
				p = grade5;
			}
			if (p.equals(grade6) || p.equals(upCharacter6)) {
				temp.cancleslbd();
				temp.resetRatio6();
				result = result.append(" [★★★★★★ " + pickoneFrom(p) + "] ");
			} else if (p.equals(grade5) || p.equals(upCharacter5)) {
				temp.increaseRatio6();
				temp.cancleslbd();
				result = result.append(" [★★★★★ " + pickoneFrom(p) + "] ");
			} else {
				temp.increaseRatio6();
				if (p.equals(grade4) || p.equals(upCharacter4)) {
					result = result.append(" [★★★★ " + pickoneFrom(p) + "] ");
				} else {
					result = result.append(" [★★★ " + pickoneFrom(p) + "] ");
				}
			}
		}
		temp.toMap();
		return result.toString();
	}

}