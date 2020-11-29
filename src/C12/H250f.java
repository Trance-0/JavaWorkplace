package C12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H250f {
	private int n;
	private int depth;
	private int num;
	private int are;

	public H250f() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		System.out.println(findnum(n) + findnum(n - 1));
		System.out.println(are);
		// n=1 1,1
		// 1x1
		// n=2 5,8
		// 1x4 2x1
		// n=3 13,23
		// 1x9 2x3 3x1
		// n=4 27,87
		// 1x16 2x7 3x3 4x1
		// n=5
		// 1x16 2x7 3x3 4x1
		// 1,4,9,16,25
	}

	private int findnum(int size) {
		int result = 0;
		for (int i = 0; i <= size; i++) {
			result += i;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		H250f a = new H250f();
	}

}