package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import module.FileLoader;

public class Extension {
	private String lastSentence = "djghjkjghireh";
	private HashMap<String, Boolean> statusCode;
	private HashMap<String, ArrayList<String>> response;
	private Random rand;
	private String path;

	public Extension() {
		response = new HashMap<String, ArrayList<String>>();
		statusCode = new HashMap<String, Boolean>();
		loadResponse();
		rand = new Random();
	}

	private void loadResponse() {
			if( System.getProperty("os.name").contains("Mac")) {
				path = System.getProperty("user.dir") + "/Resources/textFile/Response.txt";
			}else {
				path = System.getProperty("user.dir") + "\\Resources\\textFile\\Response.txt";
			}
		FileLoader fr = new FileLoader(path);
		try {
			response = fr.ReadFileByMap(":", ",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setStatusCode(String statusName, boolean input) {
		if (statusCode.containsKey(statusName)) {
			statusCode.put(statusName, input);
		} else {
			throw new NullPointerException();
		}
	}

	public boolean getStatusCode(String statusName) {
		if (statusCode.containsKey(statusName)) {
			return statusCode.get(statusName);
		} else {
			throw new NullPointerException();
		}
	}

	public boolean containKeywords(String input, ArrayList<String> temp) {
		for (int i =0;i<temp.size();i++) {
			if (input.contains(temp.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containKeywords(String input, String[] keywords) {
		for (String i : keywords) {
			if (input.contains(i)) {
				return true;
			}
		}
		return false;
	}
	
	public String findType(String msgString,HashMap<ArrayList<String>,String> fl) {
		String answer="";
		for (ArrayList<String>temp:fl.keySet()) {
			if (containKeywords(msgString,temp)) {
				answer=fl.get(temp);
			}
		}
		return answer;
	}


	public String getResponse(String Status) {
		return response.get(Status).get(rand.nextInt(response.get(Status).size()));
	}

	public String getgreet() {
		String result = "";
		// TODO Auto-generated method stub
		String[] harmlessGreet = { "_(:з」∠)_", "Ծ‸Ծ", "- ( ゜- ゜)つロ乾杯~", "(￣_,￣ )" };
		Calendar cl = Calendar.getInstance();
		int h = cl.get(Calendar.HOUR_OF_DAY);
		int week = cl.get(Calendar.DAY_OF_WEEK);
		int month = cl.get(Calendar.MONTH);
		if ((month >= 7 && month < 9) || month == 2) {
			result = harmlessGreet[new Random().nextInt(harmlessGreet.length)];
		} else {
			if (h >= 23 && week <= 4) {
				result = "这么晚还不睡你在修仙吗？";
			} else if (h <= 8) {
				result = "早！";
			} else if (h < 6 && week <= 5) {
				result = "What ?!";
			} else if (h < 7 && week > 5) {
				result = "周末不是用来睡懒觉的吗你在这里干嘛？";
			} else {
				result = harmlessGreet[new Random().nextInt(harmlessGreet.length)];
			}
		}
		return result;
	}

	public String getLastSentence() {
		return lastSentence;
	}

	public void setLastSentence(String Sentence) {
		lastSentence = Sentence;
	}
}