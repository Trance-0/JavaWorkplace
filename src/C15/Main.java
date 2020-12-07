package C15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private long[][] grassland;
	private long cowsize;
	private int grasssize;

	public Main() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cowsize = Long.parseLong(st.nextToken());
		grasssize = Integer.parseInt(st.nextToken());
		grassland = new long[grasssize][2];
		for (int i = 0; i < grasssize; i++) {
			st = new StringTokenizer(br.readLine());
			grassland[i][0] = Long.parseLong(st.nextToken());
			grassland[i][1] = Long.parseLong(st.nextToken());
		}
		long i = 0;
		long j = 1000000000000000L;
		Arrays.sort(grassland, new Comparator<long[]>() {
			public int compare(long[] a, long[] b) {
				return Long.compare(a[0], b[0]);
			}
		});
		// prlongArray(grassland);
		while (i < j) {
			long mid = (i + j) / 2;
			if (comparedis(mid) >= 0) {
				i = mid + 1;
			} else {
				j = mid - 1;
			}
		}
		if (comparedis(i) >= 0) {
			System.out.println(i);
		} else {
			System.out.println(i - 1);
		}
	}

	/*
	 * private void prlongArray(long[][] a) { for (long[] h : a) {
	 * System.out.println(h[0] + " " + h[1]); } }
	 */

	private long comparedis(long mid) {
		long result = 0;
		long polonger = grassland[0][0];
		long num;
		// if (grassland[0][1] - grassland[0][0] <= mid) {
		num = (grassland[0][1] - grassland[0][0]) / mid;
		polonger = grassland[0][0] + mid * num;
		result = result + num + 1;
		// } //else {
		// result++;
		// polonger = grassland[0][0] + mid;
		// }
		for (int i = 1; i < grassland.length; i++) {
			long l = grassland[i][0];
			long r = grassland[i][1];
			// if (polonger <= r && polonger >= l) {
			if (l - polonger >= mid) {
				num = (r - l) / mid;
				polonger = l + num * mid;
				result = result + num + 1;
				// result += (r - polonger) / mid;
				// result++;
				// polonger = polonger + ((r - polonger) / mid+1)*mid;
				// polonger = r - (r - polonger) % mid;
				// } else if (polonger + mid <= r && polonger + mid >= l) {
				// result += (r - (polonger + mid)) / mid;
				// result++;
				// polonger = polonger + ((r - polonger) / mid+1)*mid;
				// polonger = r - (r - (polonger + mid)) % mid;
				// } else if (polonger + mid < l) {
			} else if (r - polonger >= mid) {
				num = (r - (polonger + mid)) / mid;
				result = result + num + 1;
				polonger = polonger + mid + num * mid;
				// result += (r - l) / mid;
				// result++;
				// polonger = l + ((r - l) / mid+1)*mid;
				// polonger = r - (r - l) % mid;
			}
		}
		return result - cowsize;
	}

	public static void main(String[] args) throws IOException {
		Main a = new Main();
	}
}