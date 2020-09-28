package module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; // Import the IOException class to handle errors
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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
			File f=new File(path + title + ".txt");
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
			BufferedWriter myWriter = new BufferedWriter(osw);
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
			File f=new File(path + m.getTitle() + ".txt");
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
			BufferedWriter myWriter = new BufferedWriter(osw);
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
			File f = new File(path + title + ".txt");
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
			BufferedWriter myWriter = new BufferedWriter(osw);
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
			File f = new File(path + title + ".txt");
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(f),StandardCharsets.UTF_8);
			BufferedWriter myWriter = new BufferedWriter(osw);
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
			File f = new File(path + hm.get(0) + ".txt");
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
			BufferedWriter myWriter = new BufferedWriter(osw);
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
	public static void main(String args[]) {
		String[] title = {"[无拘熔火]"};
		String[] grade6 = { "能天使", "推进之王", "伊芙利特", "艾雅法拉", "安洁莉娜", "闪灵", "夜莺", "星熊", "塞雷娅", "银灰", "斯卡蒂", "陈",
				"黑", "赫拉格", "麦哲伦", "莫斯提马", "煌", "阿", "刻俄柏", "风笛", "傀影", "温蒂", "早露", "铃兰", "棘刺", "森蚺","史尔特尔"};
		String[] grade5 = { "白面鸮", "凛冬", "德克萨斯", "芙兰卡", "拉普兰德", "幽灵鲨", "蓝毒", "白金", "陨星", "天火", "梅尔", "赫默",
				"华法琳", "临光", "红", "雷蛇", "可颂", "普罗旺斯", "守林人", "崖心", "初雪", "真理", "空", "狮蝎", "食铁兽", "夜魔", "诗怀雅", "格劳克斯", "星极",
				"送葬人", "槐琥", "苇草", "布洛卡", "灰喉", "惊蛰", "慑砂", "巫恋", "极境", "石棉", "月禾", "莱恩哈特", "断崖", "蜜蜡", "贾维", "安哲拉", "燧石" ,"四月"};
		String[] grade4 = { "夜烟", "远山", "杰西卡", "流星", "白雪", "清道夫", "红豆", "杜宾", "缠丸", "霜叶", "慕斯", "砾", "暗索",
				"末药", "调香师", "角峰", "蛇屠箱", "古米", "深海色", "地灵", "阿消", "猎蜂", "格雷伊", "苏苏洛", "桃金娘", "红云", "梅", "安比尔", "宴", "刻刀",
				"波登可", "卡达", "孑", "酸糖" ,"芳汀"};
		String[] grade3 = { "芬", "香草", "翎羽", "玫兰莎", "卡缇", "米格鲁", "克洛丝", "炎熔", "芙蓉", "安赛尔", "史都华德", "梓兰", "空爆",
				"月见夜", "斑点", "泡普卡" };

		String[] upCharacter6 = { "史尔特尔" };
String[] upCharacter5 = {  "极境","四月" };
String[] upCharacter4 = {"芳汀"};
String[] upthreshold = {"50","50","20"};
String[] name= {"title","grade6","grade5","grade4","grade3","upCharacter6","upCharacter5","upCharacter4","upthreshold"};
String[][] elements= {title,grade6,grade5,grade4,grade3,upCharacter6,upCharacter5,upCharacter4,upthreshold};
FileSaver fs=new FileSaver(System.getProperty("user.dir") + "\\Resources\\textFile\\",title[0]);
HashMap<String,ArrayList<String>>hm=fs.makeaMap(name, elements);
fs.SaveMap(hm);
		
		
	}
}