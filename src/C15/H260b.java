package C15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H260b {
	private boolean[] ce;
	private int[][] data;

	public H260b() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		data = new int[Integer.parseInt(st.nextToken())][2];
		ce = new boolean[Integer.parseInt(st.nextToken()) + 1];
		for (int i = 0; i < data.length; i++) {
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < data.length; i++) {
			int result = 0;
			ce = new boolean[ce.length];
			for (int j = data.length - 1; j >= i; j--) {
				if (!ce[data[j][0]]) {
					ce[data[j][0]] = true;
					result++;
				} else if (!ce[data[j][1]]) {
					ce[data[j][1]] = true;
					result++;
				}
			}
			System.out.println(result);
		}
	}

	public static void H260b(String[] args) throws IOException {
		H260b a = new H260b();
	}
}
