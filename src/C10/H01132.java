package C10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H01132 {
	private int xsize;
	private int ysize;
	private String[][] grid;
	private LinkedList<int[]> pos;
	private String temp;
	private String key;
	private int index = 0;

	public H01132() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nextline = br.readLine();
		StringTokenizer st = new StringTokenizer(nextline);
		while (true) {
			index = 0;
			pos = new LinkedList<int[]>();
			xsize = Integer.parseInt(st.nextToken());
			ysize = Integer.parseInt(st.nextToken());
			if (xsize == 0 && ysize == 0) {
				break;
			}
			grid = new String[ysize][xsize];
			for (int i = 0; i < ysize; i++) {
				temp = br.readLine();
				for (int j = 0; j < xsize; j++) {
					key = temp.substring(j, j + 1);
					grid[i][j] = key;
					if (key.compareTo("@") == 0) {
						addpos(j, i);
					}
				}
			}
//			for (int i = 0; i < ysize; i++) {
//				System.out.println();
//				for (int j = 0; j < xsize; j++) {
//					System.out.print(grid[i][j] + " ");
//				}
//			}
			while (index < pos.size()) {
				int[] shit = pos.get(index);
				addpospos(shit[0], shit[1] + 1);
				addpospos(shit[0], shit[1] - 1);
				addpospos(shit[0] + 1, shit[1]);
				addpospos(shit[0] - 1, shit[1]);
				index++;
			}
			System.out.println(pos.size());
			nextline = br.readLine();
			st = new StringTokenizer(nextline);
		}
	}

	private void addpospos(int x, int y) {
		int[] shit = new int[2];
		shit[0] = x;
		shit[1] = y;
		if (x >= 0 && x < xsize && y >= 0 && y < ysize) {
			if (containpos(x, y) && grid[y][x].compareTo(".") == 0) {
//				System.out.println(x + " " + y);
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
		H01132 a = new H01132();
	}
}
