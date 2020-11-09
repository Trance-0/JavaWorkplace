package C5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H01097 {
	private static int[] Ba;
	private static int f1;
	private static int f2;
	private static int tail;
	private static int length;
	private static int a;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			f1 = 0;
			f2 = 0;
			tail = 0;
			String temp = f.readLine();
			if (temp == null) {
				break;
			}
			StringTokenizer st = new StringTokenizer(temp);

			a = Integer.parseInt(st.nextToken());
			length = Integer.parseInt(st.nextToken());
			Ba = new int[length];
			Ba[tail] = a;
			tail++;
			while (tail < length) {
				if (fun1() > fun2()) {
					Ba[tail] = fun2();
					f2++;
				} else if (fun1() == fun2()) {
					Ba[tail] = fun2();
					f2++;
					f1++;
				} else {
					Ba[tail] = fun1();
					f1++;
				}
				tail++;
			}
			System.out.println(Ba[length - 1]);
		}
	}

	private static int fun1() {
		return 2 * Ba[f1] + 1;
	}

	private static int fun2() {
		return 3 * Ba[f2] + 1;
	}
}
