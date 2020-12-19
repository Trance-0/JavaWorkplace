package FirstContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2 {
	public int length;
	public int[][] pos;

	public S2() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		pos = new int[length][2];
		StringTokenizer st;
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}
		int badcount = 0;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int xmax = Math.max(pos[i][0], pos[j][0]);
				int xmin = Math.min(pos[i][0], pos[j][0]);
				int ymax = Math.max(pos[i][1], pos[j][1]);
				int ymin = Math.min(pos[i][1], pos[j][1]);
				for (int k = 0; k < length; k++) {
					if (k == i || k == j) {
						continue;
					}
					if (pos[k][0] <= xmax && pos[k][0] >= xmin && pos[k][1] >= ymin && pos[k][1] <= ymax) {
						badcount++;
					}
				}
			}
		}
		System.out.println((int) (Math.pow(2, length) - (1 + badcount) * badcount / 2));
	}

	public static void main(String[] args) throws IOException {
		S2 a = new S2();
	}
}
