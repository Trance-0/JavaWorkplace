package C10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H00262 {
	private int size;
	private int quesion;
	private int[][] grid;
//get index of pos
	private LinkedList<LinkedList<int[]>> sum;
	private LinkedList<int[]> pos;
	private String temp;
	private String key;
	private int index = 0;

	public H00262() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		quesion = Integer.parseInt(st.nextToken());
		grid = new int[size][size];
		for (int i = 0; i < size; i++) {
			temp = br.readLine();
			for (int j = 0; j < size; j++) {
				key = temp.substring(j, j + 1);
				grid[i][j] = Integer.parseInt(key);
			}
		}
		String nextline = br.readLine();
		for (int i = 0; i < quesion; i++) {
			index = 0;
			st = new StringTokenizer(nextline);
			pos = new LinkedList<int[]>();
			addpos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
//			System.out.println(grid[pos.get(index)[0]][pos.get(index)[1]]);
			while (index < pos.size()) {
				int[] shit = pos.get(index);
				int prevalue = grid[shit[0]][shit[1]];
//				System.out.println(prevalue);
				addpospos(shit[0], shit[1] + 1, prevalue);
				addpospos(shit[0], shit[1] - 1, prevalue);
				addpospos(shit[0] + 1, shit[1], prevalue);
				addpospos(shit[0] - 1, shit[1], prevalue);
				index++;
			}
//			System.out.println(nextline);
			System.out.println(pos.size());
			nextline = br.readLine();
		}
	}

	private void addpospos(int x, int y, int prevalue) {
		int[] shit = new int[2];
		shit[0] = x;
		shit[1] = y;
		if (x >= 0 && x < size && y >= 0 && y < size) {
			if (containpos(x, y) && grid[x][y] + prevalue == 1) {
				pos.add(shit);
			}
		}
	}

	private boolean containpos(int x, int y) {
		for (int i = 0; i < pos.size(); i++) {
			if (pos.get(i)[0] == x && pos.get(i)[1] == y) {
				return false;
			}
		}
		return true;
	}

	private void addpos(int x, int y) {
		int[] shit = new int[2];
		shit[0] = x;
		shit[1] = y;
		pos.add(shit);
	}

	public static void main(String[] args) throws IOException {
		H00262 a = new H00262();
	}
}
