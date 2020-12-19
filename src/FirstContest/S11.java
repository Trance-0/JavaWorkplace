
package FirstContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S11 {
	private int[][] logs;
	private boolean[] issick;
	private int length;
	private int remain;

	public S11() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		remain = length - 1;
		logs = new int[length - 1][2];
		issick = new boolean[length];
		StringTokenizer st;
		for (int i = 0; i < length - 1; i++) {
			st = new StringTokenizer(br.readLine());
			logs[i][0] = Integer.parseInt(st.nextToken());
			logs[i][1] = Integer.parseInt(st.nextToken());
		}
		int time = 0;
		issick[0] = true;
		while (remain > 0) {
			for (int i = 0; i < length - 1; i++) {
				if (issick[logs[i][0]] != issick[logs[i][1]]) {

				}
			}
		}
		System.out.println(time);
	}

	private int doublecost(int size) {
		int result = 1;
		int doublecost = 0;
		while (result < size + 1) {
			doublecost++;
			result *= 2;
		}
		return doublecost;
	}

	private void printlist(LinkedList<Integer> a) {
		StringBuilder sb = new StringBuilder();
		for (int i : a) {
			sb.append(i);
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		S11 a = new S11();
	}
}
