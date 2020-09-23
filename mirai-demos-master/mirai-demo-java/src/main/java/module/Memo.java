package module;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Memo {
	private Calendar cl = Calendar.getInstance();
	private String title;
	private String key;
	private String time;
	private String content;

	public Memo(String input) {
		title = input;
		time = Integer.toString(cl.get(Calendar.YEAR)) + "." + Integer.toString(cl.get(Calendar.MONTH)) + "."
				+ Integer.toString(cl.get(Calendar.DATE)) + " " + Integer.toString(cl.get(Calendar.HOUR_OF_DAY)) + ":"
				+ Integer.toString(cl.get(Calendar.MINUTE)) + ":" + Integer.toString(cl.get(Calendar.SECOND));
		content = "";
	}

	public Memo(String input, String keyword) {
		title = input;
		key = keyword;
		time = Integer.toString(cl.get(Calendar.YEAR)) + "." + Integer.toString(cl.get(Calendar.MONTH)) + "."
				+ Integer.toString(cl.get(Calendar.DATE)) + " " + Integer.toString(cl.get(Calendar.HOUR_OF_DAY)) + ":"
				+ Integer.toString(cl.get(Calendar.MINUTE)) + ":" + Integer.toString(cl.get(Calendar.SECOND));
		content = "";
	}

	public Memo(String Title, String Time, String Content) {
		title = Title;
		time = Time;
		content = Content;
	}

	public Memo(String Title, String keyword, String Time, String Content) {
		title = Title;
		key = keyword;
		time = Time;
		content = Content;
	}

	public void writeMemo(String input) {
		content = content + input;
	}

	public void modifyMemo(String input) {
		content = input;
	}

	public void Save() throws Exception {
		FileSaver fs = new FileSaver(System.getProperty("user.dir") + "\\Resources\\Memo\\",this.getTitle());
		if (new File(System.getProperty("user.dir") + "\\Resources\\Memo\\" + this.getTitle() + ".txt").exists()) {
			throw new Exception("File Already Exsist.");
		} else {
			fs.SaveMemo(this);
		}
	}

	public String getTitle() {
		return this.title;
	}

	public String getDate() {
		return this.time;
	}

	public String getContent() {
		if (key == null) {
			return content;
		} else {
			AES a = new AES(key);
			return a.encrypt(content);
		}
	}

	public static void main(String[] args) throws IOException {
		String title = "Hello World";
		String key = "key";
		String temp;
		Memo m = new Memo(title, key);
		m.writeMemo("Here is my first memo.");
		try {
			m.Save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AES a = new AES("key");
		Memo n = new FileLoader(System.getProperty("user.dir") + "\\Resources\\Memo\\").ReadMemo("Hello World", "key");
		System.out.println(a.decrypt(n.getContent()));
	}
}
