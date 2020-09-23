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

	private final String title = "[³£¹æ¿¨³Ø]";
	private final String[] grade6 = { "ÄÜÌìÊ¹", "ÍÆ½øÖ®Íõ", "ÒÁÜ½ÀûÌØ", "°¬ÑÅ·¨À­", "°²½àÀòÄÈ", "ÉÁÁé", "Ò¹İº", "ĞÇĞÜ", "ÈûÀ×æ«", "Òø»Ò", "Ë¹¿¨µÙ", "³Â",
			"ºÚ", "ºÕÀ­¸ñ", "ÂóÕÜÂ×", "ÄªË¹ÌáÂí", "»Í", "°¢", "¿Ì¶í°Ø", "·çµÑ", "¿şÓ°", "ÎÂµÙ", "ÔçÂ¶", "ÁåÀ¼", "¼¬´Ì", "É­òÅ" };
	private final String[] grade5 = { "°×Ãæû^", "Áİ¶¬", "µÂ¿ËÈøË¹", "Ü½À¼¿¨", "À­ÆÕÀ¼µÂ", "ÓÄÁéöè", "À¶¶¾", "°×½ğ", "ÔÉĞÇ", "Ìì»ğ", "Ã·¶û", "ºÕÄ¬",
			"»ª·¨ÁÕ", "ÁÙ¹â", "ºì", "À×Éß", "¿ÉËÌ", "ÆÕÂŞÍúË¹", "ÊØÁÖÈË", "ÑÂĞÄ", "³õÑ©", "ÕæÀí", "¿Õ", "Ê¨Ğ«", "Ê³ÌúÊŞ", "Ò¹Ä§", "Ê«»³ÑÅ", "¸ñÀÍ¿ËË¹", "ĞÇ¼«",
			"ËÍÔáÈË", "»±çú", "Î­²İ", "²¼Âå¿¨", "»Òºí", "¾ªÕİ", "ÉåÉ°", "Î×Áµ", "¼«¾³", "Ê¯ÃŞ", "ÔÂºÌ", "À³¶÷¹şÌØ", "¶ÏÑÂ", "ÃÛÀ¯", "¼ÖÎ¬", "°²ÕÜÀ­", "ìİÊ¯" };
	private final String[] grade4 = { "Ò¹ÑÌ", "Ô¶É½", "½ÜÎ÷¿¨", "Á÷ĞÇ", "°×Ñ©", "ÇåµÀ·ò", "ºì¶¹", "¶Å±ö", "²øÍè", "ËªÒ¶", "Ä½Ë¹", "Àù", "°µË÷",
			"Ä©Ò©", "µ÷ÏãÊ¦", "½Ç·å", "ÉßÍÀÏä", "¹ÅÃ×", "Éîº£É«", "µØÁé", "°¢Ïû", "ÁÔ·ä", "¸ñÀ×ÒÁ", "ËÕËÕÂå", "ÌÒ½ğÄï", "ºìÔÆ", "Ã·", "°²±È¶û", "Ñç", "¿Ìµ¶",
			"²¨µÇ¿É", "¿¨´ï", "æİ", "ËáÌÇ" };
	private final String[] grade3 = { "·Ò", "Ïã²İ", "ôáÓğ", "ÃµÀ¼É¯", "¿¨ç¾", "Ã×¸ñÂ³", "¿ËÂåË¿", "Ñ×ÈÛ", "Ü½ÈØ", "°²Èü¶û", "Ê·¶¼»ªµÂ", "è÷À¼", "¿Õ±¬",
			"ÔÂ¼ûÒ¹", "°ßµã", "ÅİÆÕ¿¨" };

	private int threshold6 = 2;
	private final int threshold5 = 8;
	private final int threshold4 = 50;
	private final String[] upCharacter6 = { "ÉÁÁé", "»Í" };
	private final String[] upCharacter5 = { "ÓÄÁéöè", "ºÕÄ¬", "ÑÂĞÄ" };
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
		result.append("¿¨³Ø£º" + title + " ÓÃ»§£º" + temp.getUserName() + " ÒÑ³é¿¨£º" + temp.getTotalCount() + "´Î¡£ " + " Î´³éÖĞÁùĞÇ´ÎÊı£º"
				+ temp.getRatio6() + "\n");
//		result.append(title + "ÓÃ»§£º" + temp.getUserName());
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
				result = result.append(" [¡ï¡ï¡ï¡ï¡ï¡ï " + pickoneFrom(p) + "] ");
			} else if (p.equals(grade5) || p.equals(upCharacter5)) {
				temp.increaseRatio6();
				temp.cancleslbd();
				result = result.append(" [¡ï¡ï¡ï¡ï¡ï " + pickoneFrom(p) + "] ");
			} else {
				temp.increaseRatio6();
				if (p.equals(grade4) || p.equals(upCharacter4)) {
					result = result.append(" [¡ï¡ï¡ï¡ï " + pickoneFrom(p) + "] ");
				} else {
					result = result.append(" [¡ï¡ï¡ï " + pickoneFrom(p) + "] ");
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