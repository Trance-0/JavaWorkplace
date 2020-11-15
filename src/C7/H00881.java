package C7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class H00881 {
	private int length;
	private int A = 1;
	private Stack<Integer> C = new Stack<Integer>();

	public H00881() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < length; i++) {
			int target = Integer.parseInt(st.nextToken());
			if (target >= peekA()) {
				while (peekA() != target) {
					C.add(popA());
				}
				popA();
			} else {
				if (peekC() == target) {
					popC();
				} else {
					System.out.println("NO");
					break;
				}
			}
			if (i == length - 1) {
				System.out.println("YES");
			}
		}

	}

	private int popA() {
		int temp = A;
		A++;
		return temp;
	}

	private int peekA() {
		return A;
	}

	private int popC() {
		return C.pop();
	}

	private int peekC() {
		return C.peek();
	}

	public static void main(String[] args) throws IOException {
		H00881 a = new H00881();
	}
}