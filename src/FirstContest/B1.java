package FirstContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1 {
	private int[] numbers = new int[7];
	private int a;
	private int b;
	private int c;

	public B1() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		for (int i = 0; i < 7; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
		a = numbers[0];
		b = numbers[1];
		c = numbers[6] - a - b;
		System.out.println(a + " " + b + " " + c);
	}

	public static void main(String[] args) throws IOException {
		B1 a = new B1();
	}
}
