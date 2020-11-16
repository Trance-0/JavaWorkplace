package C7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class H00124 {
	private Stack<Integer> input = new Stack<Integer>();
	private String ori;
	private int index;
	private int nextelement;
	private boolean multiply = false;

	public H00124() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ori = br.readLine() + "+";
		for (int i = 0; i < ori.length(); i++) {
			if (ori.charAt(i) == '+') {
				if (multiply) {
					input.add((Integer.parseInt(ori.substring(index, i)) * input.pop()) % 10000);
					multiply = false;
				} else {
					input.add((Integer.parseInt(ori.substring(index, i))) % 10000);
				}
				index = i + 1;
			} else if (ori.charAt(i) == '*') {
				if (multiply) {
					input.add((Integer.parseInt(ori.substring(index, i)) * input.pop()) % 10000);
				} else {
					input.add((Integer.parseInt(ori.substring(index, i))) % 10000);
					multiply = true;
				}
				index = i + 1;
			}
		}
		int sum = 0;
		while (!input.isEmpty()) {
			int temp = input.pop();
//			System.out.println(temp);
			sum += temp;
		}
		System.out.println(sum % 10000);
	}

	public static void main(String[] args) throws IOException {
		H00124 a = new H00124();
	}
}
