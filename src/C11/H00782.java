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

	private node loc;
	private int L;
	private int R;
	private int C;
	private int[] end = new int[3];
	private int[] start = new int[3];
	private String temp;
	private String[][][] maze;
	private LinkedList<node> his = new LinkedList<node>();
	private boolean pos[];
	private node lastnode;

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
			int find = findpath();
			if (find == 0) {
				System.out.println("Trapped!");
			} else {
				System.out.println("Escaped in " + find + " minute(s).");
			}
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
		his = new LinkedList<node>();
		pos = new boolean[L * R * C];
		his.add(new node(null, start));

		node lastnode = new node(null, start);
		pos[start[0] * R * C + start[1] * C + start[2]] = true;
		int index = 0;
		int result = 0;
		while (index < his.size()) {
			loc = his.get(index);
			int l = loc.to[0];
			int r = loc.to[1];
			int c = loc.to[2];
			if (comparepos(loc.to, end)) {
				lastnode = loc;
				System.out.print(loc.to[0] + "" + loc.to[1] + "" + loc.to[2]);
				break;
			}
			addpossiblenode(l + 1, r, c, loc);
			addpossiblenode(l - 1, r, c, loc);
			addpossiblenode(l, r + 1, c, loc);
			addpossiblenode(l, r - 1, c, loc);
			addpossiblenode(l, r, c + 1, loc);
			addpossiblenode(l, r, c - 1, loc);
			index++;
		}
//		System.out.println(pos.size());
		while (lastnode.from != null) {
			lastnode = lastnode.from;
			maze[lastnode.to[0]][lastnode.to[1]][lastnode.to[2]] = "*";
			result++;
		}
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					System.out.print(maze[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		return result;
	}

	private void addpossiblenode(int l, int r, int c, node last) {
//		System.out.println("l" + l + "r" + r + "c" + c);
		if (l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C) {
//			System.out.println("requirement passed!");
			int[] test = createpos(l, r, c);
			if (maze[l][r][c].compareTo("#") != 0 && containpos(test)) {
				his.add(new node(last, test));
				pos[test[0] * R * C + test[1] * C + test[2]] = true;
			}
		}
	}

	private boolean containpos(int[] test) {
//		System.out.print(pos[test[0] * R + test[1] * C + test[2]]);
		return !pos[test[0] * R * C + test[1] * C + test[2]];
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
