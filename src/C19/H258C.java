package C19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class H258C {
	private int size;
	private int loglength;
	private int[] result;
	private int[][] logs;
	private int inf;
	private int kmax;
	private int kmin;
	private boolean[] possibleori;

	public H258C() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		loglength = Integer.parseInt(st.nextToken());
		String temp = br.readLine();
		result = new int[size];
		int[] simulation = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = Integer.parseInt(temp.substring(i, i + 1));
		}
		logs = new int[loglength][3];
		for (int i = 0; i < loglength; i++) {
			st = new StringTokenizer(br.readLine());
			logs[i][0] = Integer.parseInt(st.nextToken());
			logs[i][1] = Integer.parseInt(st.nextToken()) - 1;
			logs[i][2] = Integer.parseInt(st.nextToken()) - 1;
		}
		Arrays.sort(logs, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
		});
		kmin = loglength;
		kmax = 0;
		possibleori = new boolean[size];
		for (int cowzero = 0; cowzero < size; cowzero++) {
			for (int k = 0; k <= loglength; k++) {
				boolean isok = true;
				simulation[cowzero] = k + 1;// 03000
				for (int t = 0; t < logs.length; t++) {
					int[] pull = logs[t];
					if (simulation[pull[1]] > 1 && simulation[pull[2]] == 0) {
						simulation[pull[2]] = k + 1;
						simulation[pull[1]]--;
						if (result[pull[2]] != 1) {
							isok = false;
							break;
						}
					} else if (simulation[pull[1]] > 0 && simulation[pull[2]] > 0) {
						simulation[pull[1]]--;
						simulation[pull[2]]--;
					} else if (simulation[pull[1]] == 0 && simulation[pull[2]] > 1) {
						simulation[pull[1]] = k + 1;
						simulation[pull[2]]--;
						if (result[pull[1]] != 1) {
							isok = false;
							break;
						}
					}
				}
				for (int i = 0; i < size; i++) {
					if (result[i] == 0 && simulation[i] != 0) {
						isok = false;
						break;
					} else if (result[i] == 1 && simulation[i] == 0) {
						isok = false;
						break;
					}
				}
				for (int i : simulation) {
					System.out.print(i + " ");
				}
				System.out.println("when k is " + k + " cowzero is " + cowzero + ".");
				if (isok) {
//					System.out.println("when k is " + k + " cowzero is " + cowzero + " isok.");
//					for (int i : simulation) {
//						System.out.print(i + " ");
//					}
					possibleori[cowzero] = true;
					kmin = Math.min(kmin, k);
					kmax = Math.max(kmax, k);
				}
				simulation = new int[size];
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(countoftrue(possibleori));
		sb.append(" ");
		sb.append(kmin);
		sb.append(" ");
		if (kmax == loglength) {
			sb.append("Infinity");
		} else {
			sb.append(kmax);
		}
		System.out.println(sb.toString());
	}

	private int countoftrue(boolean[] possibleori2) {
		int result = 0;
		for (boolean i : possibleori2) {
			if (i) {
				result++;
			}
		}
		// TODO Auto-generated method stub
		return result;
	}

	public static void main(String[] args) throws IOException {
		H258C a = new H258C();
	}
}
