package C11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H00782 {
	private class node {
		private int[] to;
		private node from;

		public node(node f, int[] t) {
			to = t;
			from = f;
		}
	}

	private int L;
	private int R;
	private int C;
	private int[] end = new int[3];
	private int[] start = new int[3];
	private String temp;
	private String[][][] maze;
	private LinkedList<node> his = new LinkedList<node>();
	private LinkedList<int[]> pos = new LinkedList<int[]>();

	public H00782() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		while (L != 0 && R != 0 && C != 0) {
			maze = new String[L][R][C];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					temp = br.readLine();
					for (int k = 0; k < C; k++) {
						String tempkey = temp.substring(k, k + 1);
						if (tempkey.compareTo("S") == 0) {
							start = createpos(i, j, k);
						} else if (tempkey.compareTo("E") == 0) {
							end = createpos(i, j, k);
						}
						maze[i][j][k] = tempkey;
					}
				}
				br.readLine();
			}
//			for (int i = 0; i < L; i++) {
//				for (int j = 0; j < R; j++) {
//					for (int k = 0; k < C; k++) {
//						System.out.print(maze[i][j][k]);
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
		}
	}

	public int[] createpos(int L, int R, int C) {
		int[] shit = new int[3];
		shit[0] = L;
		shit[1] = R;
		shit[2] = C;
		return shit;
	}

	public int findpath() {
		his.add(new node(null, start));
		int index = 0;
		int result = 0;
		while (index < his.size()) {
			node loc = his.get(index);
			L = loc.to[0];
			R = loc.to[1];
			C = loc.to[2];
			int[] test = new int[3];
			if (comparepos(test, end)) {
				break;
			}
			test = createpos(L + 1, R, C);
			if (maze[L + 1][R][C].compareTo(".") == 0) {
				his.add(new node(loc, test));
			}
			test = createpos(L - 1, R, C);
			if (maze[L - 1][R][C].compareTo(".") == 0) {
				his.add(new node(loc, test));
			}
			test = createpos(L, R + 1, C);
			if (maze[L][R + 1][C].compareTo(".") == 0) {
				his.add(new node(loc, test));
			}
			test = createpos(L, R - 1, C);
			if (maze[L][R - 1][C].compareTo(".") == 0) {
				his.add(new node(loc, test));
			}
			test = createpos(L, R, C + 1);
			if (maze[L][R][C + 1].compareTo(".") == 0) {
				his.add(new node(loc, test));
			}
			test = createpos(L, R, C - 1);
			if (maze[L][R][C - 1].compareTo(".") == 0) {
				his.add(new node(loc, test));
			}
			index++;
		}
		while 
		return result;
	}

	private boolean comparepos(int[] i, int[] j) {
		if (i[0] == j[0] && i[1] == j[1] && i[2] == j[2]) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		H00782 a = new H00782();
	}
}
