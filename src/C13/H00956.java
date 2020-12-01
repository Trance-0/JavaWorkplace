package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H00956 {
	private int length;
	private int[][] data;

	public H00956() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		data = new int[length][length];
		st = new StringTokenizer(br.readLine());
		data[0][0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				if (j == 0) {
					data[i][j] = Integer.parseInt(st.nextToken()) + data[i - 1][j];
				} else {
					data[i][j] = Integer.parseInt(st.nextToken()) + Math.max(data[i - 1][j], data[i - 1][j - 1]);
				}
			}
		}
		int result = 0;
		for (int i : data[length - 1]) {
//			System.out.println(i);
			result = Math.max(i, result);
		}
		System.out.println("max=" + result);
	}

	public static void main(String[] args) throws IOException {
		H00956 a = new H00956();
	}
}
