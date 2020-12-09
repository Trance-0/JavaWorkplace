package C17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H260C {
	private LinkedList<int[]> group;
	private int length;

	public H260C() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		group = new LinkedList<int[]>();
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int[] element = new int[4];
		element[0] = x;
		element[1] = y;
		element[2] = x;
		element[3] = y;
		group.add(element);
		for (int i = 1; i < length; i++) {
			st = new StringTokenizer(br.readLine());
			element = new int[4];
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			element[0] = x;
			element[1] = y;
			element[2] = x;
			element[3] = y;
			for (int j = 0; j < group.size(); j++) {
				boolean remove = true;
				int[] temp = group.get(j);
				int ex = temp[0];
				int ey = temp[1];
				int fx = temp[2];
				int fy = temp[3];
				if (x >= ex && x <= fx) {
					element[0] = ex;
					element[2] = fx;
					if (y >= ey && y <= fy) {
						element[1] = ey;
						element[3] = fy;
					} else if (y < fy) {
						element[1] = ey;
						element[3] = y;
					} else {
						element[1] = y;
						element[3] = fy;
					}
				} else if (x < ex) {
					element[0] = x;
					element[2] = fx;
					if (y >= ey && y <= fy) {
						element[1] = ey;
						element[3] = fy;
					} else if (y < fy) {
						element[1] = ey;
						element[3] = y;
					} else {
						element[2] = x;
						remove = false;
					}
				} else {
					element[0] = ex;
					element[2] = x;
					if (y >= ey && y <= fy) {
						element[1] = ey;
						element[3] = fy;
					} else if (y > ey) {
						element[1] = y;
						element[3] = fy;
					} else {
						element[0] = x;
						remove = false;
					}
				}
				if (remove) {
					group.remove(j);
					j--;
				}
			}
			group.add(element);
		}
		System.out.println(group.size());
//		for (int[] i : group) {
//			printint(i);
//		}
	}

	private void printint(int[] t) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i : t) {
			sb.append(i);
			sb.append(" ");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append(")");
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		H260C a = new H260C();
	}
}
