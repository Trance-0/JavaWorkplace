package C18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class H257C {
	private int time;
	private int[] cows;
	private int a1;
	private int a2;
	private int b1;
	private int b2;

	public H257C() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		cows = new int[size];
		for (int i = 0; i < cows.length; i++) {
			cows[i] = i + 1;
		}
		time = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		a1 = Integer.parseInt(st.nextToken()) - 1;
		a2 = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		b1 = Integer.parseInt(st.nextToken()) - 1;
		b2 = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 0; i < time; i++) {
			int[] temp = Arrays.copyOfRange(cows, a1, a2 + 1);
			for (int j = a1; j <= a2; j++) {
				cows[j] = temp[a2 - j];
			}

//			for (int f = 0; f < size; f++) {
//				System.out.println(cows[f]);
//			}

			int[] tem = Arrays.copyOfRange(cows, b1, b2 + 1);
			for (int j = b1; j <= b2; j++) {
				cows[j] = tem[b2 - j];
			}

			boolean issame = true;
			for (int f = 0; f < size; f++) {
				if (cows[f] != f + 1) {
					issame = false;
					break;
				}
			}
			if (issame) {
				time = time % (i + 1);
				i = -1;
			}
		}
		for (int i = 0; i < size; i++) {
			System.out.println(cows[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		H257C a = new H257C();
	}
}
