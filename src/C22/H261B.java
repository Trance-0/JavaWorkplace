package C22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class H261B {
	private int length;

	private class line {
		private ArrayList<Integer> element;
		private int myname;
		private int[] index = new int[200001];
		private int[] addups;

		public line(int xory) {
			myname = xory;
			element = new ArrayList<Integer>();
		}

		public void sort() {
			element.sort(null);
			addups = new int[element.size()];
			int sum = 0;
			for (int i = 0; i < element.size(); i++) {
				index[element.get(i)] = i + 1;// the index of needed element
				addups[i] = sum;// where to find adddups
				sum += element.get(i);
			}
		}
	}

	private int[] hx = new int[20001];
	private int[] hy = new int[20001];
	private ArrayList<line> x;
	private ArrayList<line> y;
	private int sum;

	public H261B() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sum = 0;
		length = Integer.parseInt(br.readLine());
		x = new ArrayList<line>();
		y = new ArrayList<line>();
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(br.readLine());
			int tx = Integer.parseInt(st.nextToken()) + 10001;
			int ty = Integer.parseInt(st.nextToken()) + 10001;
			if (hx[tx] != 0) {
				x.get(hx[tx] - 1).element.add(ty);
			} else {
				hx[tx] = x.size() + 1;
				line temp = new line(tx);
				temp.element.add(ty);
				x.add(temp);
			}
			if (hy[ty] != 0) {
				y.get(hy[ty] - 1).element.add(tx);
			} else {
				hy[ty] = y.size() + 1;
				line temp = new line(ty);
				temp.element.add(tx);
				y.add(temp);
			}
		}
		for (line i : x) {
			i.sort();
		}
		for (line i : y) {
			i.sort();
		}
		for (line i : x) {
			if (i.element.size() > 1) {
				for (int ty : i.element) {
					if (hy[ty] != 0) {
						line j = y.get(hy[ty] - 1);
						if (j.element.size() > 1) {
							int indexofx = i.index[i.myname];
							int xaddup = i.addups[i.element.size() - 1] - 2 * j.addups[indexofx] - indexofx * i.myname;
							int indexofy = j.index[j.myname];
							int yaddup = j.addups[j.element.size() - 1] - 2 * j.addups[indexofy] - indexofy * j.myname;
						}
					}
				}
			}
		}
		System.out.println(sum);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		H261B a = new H261B();
	}
}
