package FirstContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B3 {
	private int length;
	private int[] result;
	private LinkedList<int[]> tonorth;
	private LinkedList<int[]> toeast;

	public B3() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		tonorth = new LinkedList<int[]>();
		toeast = new LinkedList<int[]>();
		result = new int[length];
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(br.readLine());
			int[] temp = new int[3];
			if (st.nextToken().compareTo("E") == 0) {
				temp[0] = Integer.parseInt(st.nextToken());
				temp[1] = Integer.parseInt(st.nextToken());
				temp[2] = i;
				toeast.add(temp);
			} else {
				temp[0] = Integer.parseInt(st.nextToken());
				temp[1] = Integer.parseInt(st.nextToken());
				temp[2] = i;
				tonorth.add(temp);
			}
		}
		tonorth.sort(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}

		});
		toeast.sort(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}

		});
		for (int i = 0; i < tonorth.size(); i++) {
			for (int j = 0; j < toeast.size(); j++) {
				if (i >= 0 && j >= 0) {
					int[] tempn = tonorth.get(i);
					int[] tempe = toeast.get(j);
//					System.out.println(tempn[0] + "," + tempn[1]);
					if (tempe[0] <= tempn[0] && tempe[1] >= tempn[1]) {
						int xlen = tempn[0] - tempe[0];
						int ylen = tempe[1] - tempn[1];
						if (xlen > ylen) {
							result[tempe[2]] = xlen;
							toeast.remove(tempe);
							j--;
//							System.out.println(tempe[0] + "," + tempe[1] + " is removed collide with " + tempn[0] + ","
//									+ tempn[1]);
						}
						if (xlen < ylen) {
							result[tempn[2]] = ylen;
							tonorth.remove(tempn);
//							System.out.println(tempn[0] + "," + tempn[1] + " is removed collide with " + tempe[0] + ","
//									+ tempe[1]);
							i--;
						}
					}
				}
			}
		}
		for (int i : result) {
			if (i != 0) {
				System.out.println(i);
			} else {

				System.out.println("Infinity");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		B3 a = new B3();
	}
}
