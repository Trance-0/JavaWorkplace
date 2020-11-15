package C7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class H00210 {
	private Stack<String> input = new Stack<String>();

	public H00210() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ori = br.readLine();
		for (int i = 0; i < ori.length(); i++) {
			String temp = ori.substring(i, i + 1);
//			System.out.println(temp);
			if (input.isEmpty()) {
				input.add(temp);
				continue;
			}
			if (ispaired(temp, input.peek())) {
				input.pop();
			} else {
				input.add(temp);
			}
		}
		if (input.isEmpty()) {
			System.out.println("Exact matched");
		} else if (orp()) {
			System.out.println("Extra right parenthesis");
		} else if (olp()) {
			System.out.println("Extra left parenthesis");
		} else {
			System.out.println("Different left and right brackets");
		}
	}

	private boolean orp() {
		while (!input.isEmpty()) {
			String temp = input.pop();
			if (temp.compareTo("{") == 0 || temp.compareTo("(") == 0 || temp.compareTo("[") == 0) {
				return false;
			}
		}
		return true;
	}

	private boolean olp() {
		while (!input.isEmpty()) {

			String temp = input.pop();
			if (temp.compareTo("}") == 0 || temp.compareTo(")") == 0 || temp.compareTo("]") == 0) {
				return false;
			}
		}
		return true;
	}

	private boolean ispaired(String temp, String peek) {
		return (temp.compareTo(")") == 0 && peek.compareTo("(") == 0)
				|| (temp.compareTo("]") == 0 && peek.compareTo("[") == 0)
				|| (temp.compareTo("}") == 0 && peek.compareTo("{") == 0);
	}

	public static void main(String[] args) throws IOException {
		H00210 a = new H00210();
	}
}
