package FirstContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B2 {
	private int length;
	private int result;
	private int[] pasture;

	public B2() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		pasture = new int[length];
		result = length;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < length; i++) {
			pasture[i] = Integer.parseInt(st.nextToken());
		}
		for (int w = 2; w <= length; w++) {
			for (int i = 0; i + w <= length; i++) {
				LinkedList<Integer> frame = new LinkedList<Integer>();
				int sum = 0;
				for (int j = i; j < i + w; j++) {
					sum += pasture[j];
					frame.add(pasture[j]);
//					System.out.println(sum);
				}
//				System.out.println("----" + w);
				if (sum % w == 0 && frame.contains(sum / w)) {
					result++;
				}
			}
		}
		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {
		B2 a = new B2();
	}
}
