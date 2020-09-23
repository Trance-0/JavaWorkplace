package Arknights;

import java.util.ArrayList;
import java.util.Random;

import module.FileLoader;
import module.FileSaver;

public class Simulate {
	
	private String path;
public Simulate() {
	if( System.getProperty("os.name").contains("Mac")) {
		path = System.getProperty("user.dir") + "/Resources/ArknightsSaving/";
	}else {
		path = System.getProperty("user.dir") + "\\Resources\\ArknightsSaving\\";
	}
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

		public void addtotalcount(int count) {
			totalcount += count;
		}

		public void addNo6(int ratio) {
			no6 += ratio;
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
			FileSaver fs = new FileSaver(path);
			fs.SaveArray(elements);
		}
	}

	private final String title = "[���濨��]";
	private final String[] grade6 = { "����ʹ", "�ƽ�֮��", "��ܽ����", "���ŷ���", "��������", "����", "ҹݺ", "����", "�����", "����", "˹����", "��",
			"��", "������", "������", "Ī˹����", "��", "��", "�̶���", "���", "��Ӱ", "�µ�", "��¶", "����", "����", "ɭ��" };
	private final String[] grade5 = { "�����^", "�ݶ�", "�¿���˹", "ܽ����", "��������", "������", "����", "�׽�", "����", "���", "÷��", "��Ĭ",
			"������", "�ٹ�", "��", "����", "����", "������˹", "������", "����", "��ѩ", "����", "��", "ʨЫ", "ʳ����", "ҹħ", "ʫ����", "���Ϳ�˹", "�Ǽ�",
			"������", "����", "έ��", "���忨", "�Һ�", "����", "��ɰ", "����", "����", "ʯ��", "�º�", "��������", "����", "����", "��ά", "������", "��ʯ" };
	private final String[] grade4 = { "ҹ��", "Զɽ", "������", "����", "��ѩ", "�����", "�춹", "�ű�", "����", "˪Ҷ", "Ľ˹", "��", "����",
			"ĩҩ", "����ʦ", "�Ƿ�", "������", "����", "�ɫ", "����", "����", "�Է�", "������", "������", "�ҽ���", "����", "÷", "���ȶ�", "��", "�̵�",
			"���ǿ�", "����", "��", "����" };
	private final String[] grade3 = { "��", "���", "����", "õ��ɯ", "���", "�׸�³", "����˿", "����", "ܽ��", "������", "ʷ������", "����", "�ձ�",
			"�¼�ҹ", "�ߵ�", "���տ�" };

	private int threshold6 = 2;
	private final int threshold5 = 8;
	private final int threshold4 = 50;
	private final String[] upCharacter6 = { "����", "��" };
	private final String[] upCharacter5 = { "������", "��Ĭ", "����" };
	private final String[] upCharacter4 = {};
	private final int upthreshold6 = 50;
	private final int upthreshold5 = 50;
	private final int upthreshold4 = 20;

	private String[] core(DataSet d) {
		Random rand = new Random();
		int pos = rand.nextInt(100) + 1;
		int up = rand.nextInt(100) + 1;
		if (pos < threshold6) {
			if (up < upthreshold6 && upCharacter6.length > 0) {
				return upCharacter6;
			}
			return grade6;
		} else if (pos < threshold5) {
			if (up < upthreshold5 && upCharacter5.length > 0) {
				return upCharacter5;
			}
			return grade5;
		} else if (pos < threshold4) {
			if (up < upthreshold4 && upCharacter4.length > 0) {
				return upCharacter4;
			}
			return grade4;
		} else {
			return grade3;
		}
	}

	public String pickoneFrom(String[] grade) {
		Random rand = new Random();
		return grade[rand.nextInt(grade.length)];

	}

	public DataSet loadSaving(String userName) {

		return null;
	}

	public String simulate(String user, int times) {

		DataSet temp = null;
		try {
			FileLoader fl = new FileLoader(path + user + ".txt");
			temp = new DataSet(fl.ReadFileByLine());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			temp = new DataSet(user);
		}

		StringBuilder result = new StringBuilder();
		result.append("���أ�" + title + " �û���" + temp.getUserName() + " �ѳ鿨��" + temp.getTotalCount() + "�Ρ� " + " δ�������Ǵ�����"
				+ temp.getRatio6() + "\n");
//		result.append(title + "�û���" + temp.getUserName());
		for (int i = 0; i < times; i++) {
			if (temp.no6 > 50) {
				threshold6 = 2 + (temp.no6 - 50) * 2;
			} else {
				threshold6 = 2;
			}
			temp.addtotalcount(1);
			String[] p = core(temp);
			System.out.print(" ");
			if (temp.getslbd() && temp.getTotalCount() == 9) {
				p = grade5;
			}
			if (p.equals(grade6) || p.equals(upCharacter6)) {
				temp.cancleslbd();
				temp.resetRatio6();
				result = result.append(" [������� " + pickoneFrom(p) + "] ");
			} else if (p.equals(grade5) || p.equals(upCharacter5)) {
				temp.increaseRatio6();
				temp.cancleslbd();
				result = result.append(" [������ " + pickoneFrom(p) + "] ");
			} else {
				temp.increaseRatio6();
				if (p.equals(grade4) || p.equals(upCharacter4)) {
					result = result.append(" [����� " + pickoneFrom(p) + "] ");
				} else {
					result = result.append(" [���� " + pickoneFrom(p) + "] ");
				}
			}
		}
		temp.toMap();
		return result.toString();
	}

//	public static void main(String[] args) {
//		Simulate s = new Simulate();
//		System.out.println("Enter the times you want to try.");
//		Scanner i = new Scanner(System.in);
//		System.out.println(s.simulate(i.nextInt()));
//	}
}