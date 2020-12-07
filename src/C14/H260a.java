package C14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private long[][] grassland;
	private long cowsize;

	public Main() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cowsize = Long.parseLong(st.nextToken());
		grassland = new long[Integer.parseInt(st.nextToken())][2];
		for (int i = 0; i < grassland.length; i++) {
			st = new StringTokenizer(br.readLine());
			grassland[i][0] = Long.parseLong(st.nextToken());
			grassland[i][1] = Long.parseLong(st.nextToken());
		}
		long i = 0;
		long j = 10000000;
		Arrays.sort(grassland, new Comparator<long[]>() {
			public int compare(long[] a, long[] b) {
				if ((a[0] - b[0]) > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		prlongArray(grassland);
		while (i < j) {
			long mid = (i + j) / 2;
			System.out.println(comparedis(mid) + "mid:" + mid);
			if (comparedis(mid) >= 0) {
				i = mid + 1;
			} else {
				j = mid - 1;
			}
		}

		while (comparedis(i) > 0) {
			i++;
		}
		System.out.println(i);
	}

	private void prlongArray(long[][] a) {
		for (long[] h : a) {
//			System.out.prlongln(h[0] + " " + h[1]);
		}
	}

	private long comparedis(long mid) {
		long result = 0;
		long polonger = grassland[0][0];
		if (grassland[0][1] - grassland[0][0] <= mid) {
			result += (grassland[0][1] - grassland[0][0]) / mid + 1;
			polonger = grassland[0][0] + mid * result;
		} else {
			result++;
			polonger = grassland[0][0] + mid;
		}
		for (int i = 1; i < grassland.length; i++) {
			long l = grassland[i][0];
			long r = grassland[i][1];
			if (polonger <= r && polonger >= l) {
				result += (r - polonger) / mid;
				result++;
				polonger = r - (r - polonger) % mid;
			} else if (polonger + mid <= r && polonger + mid >= l) {
				result += (r - (polonger + mid)) / mid;
				result++;
				polonger = r - (r - (polonger + mid)) % mid;
			} else if (polonger + mid <= l) {
				result += (r - l) / mid;
				result++;
				polonger = r - (r - l) % mid;
			}
		}
		return result - cowsize;
	}

	public static void main(String[] args) throws IOException {
		Main a = new Main();
	}
}
