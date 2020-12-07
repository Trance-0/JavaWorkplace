package C16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H260b {
	private int[][] data;
	private int[] result;
	private int cereal;
	private int cow;

	public H260b() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cow = Integer.parseInt(st.nextToken()) + 1;
		cereal = Integer.parseInt(st.nextToken()) + 1;
		data = new int[cow + 1][2];
		result = new int[cow - 1];
		for (int i = cow - 1; i >= 1; i--) {
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
		int[] cerealowner = new int[cereal];
		int optimizecount = 0;
		for (int i = 1; i < cow; i++) {
//				printArray(cerealowner);
//				System.out.println(data[i][0] + ":" + data[i][1]);
			if (cerealowner[data[i][0]] == 0) {
				optimizecount++;
				cerealowner[data[i][0]] = i;
			} else {
				int lastowner = cerealowner[data[i][0]];
				while (data[i][0] != data[lastowner][1]) {
					int temp = cerealowner[data[lastowner][1]];
					cerealowner[data[lastowner][1]] = lastowner;
					lastowner = temp;
					if (temp == 0) {
						optimizecount++;
						break;
					}
				}
				cerealowner[data[i][0]] = i;
			}
//			System.out.println("----------");
			result[cow - i - 1] = optimizecount;
		}
		printArray(result);
	}

	private void printArray(int[] input) {
		for (int i = 0; i < input.length; i++) {
			System.out.println(input[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		H260b a = new H260b();
	}
}
