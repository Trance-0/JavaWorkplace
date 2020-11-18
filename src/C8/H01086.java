package C8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H01086 {
	private int length;
	private int index;
	private String command;
	private String input;
	private LinkedList<String> text = new LinkedList<String>();

	public H01086() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(br.readLine());
			command = st.nextToken();
			if (command.compareTo("i") == 0) {
				insert(st);
			} else if (command.compareTo("b") == 0) {
				back();
			} else if (command.compareTo("f") == 0) {
				forward();
			} else {
				delete();
			}
		}
		StringBuilder sb = new StringBuilder();
		int size = text.size();
		for (int i = 0; i < size; i++) {
			String temp = text.poll();
			sb.append(temp);
		}
		System.out.println(sb.toString());
	}
	public void back() {
		if (index > 0) {
			index--;
		}
	}
	public void forward() {
		if (index < text.size()) {
			index++;
		}
	}
	public void delete() {
		if (!text.isEmpty() && index< text.size()) {
			text.remove(index);
		}
	}
public void insert(StringTokenizer st){input = st.nextToken();
text.add(index, input);
forward();
}

	public static void main(String[] args) throws IOException {
		H01086 a = new H01086();
	}
}
