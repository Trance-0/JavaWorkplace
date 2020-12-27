
package FirstContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S11 {
	private class node {
		private ArrayList<Integer> child;
		private int size;

		public node() {
			size = 0;
			child = new ArrayList<Integer>();
		}

		private void add(int i) {
			child.add(i);
			size++;
		}
	}

	private node[] logs;
	private boolean[] issick;
	private int length;
	private ArrayList<Integer> memory;

	public S11() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		logs = new node[length];
		issick = new boolean[length];
		StringTokenizer st;
		for (int i = 0; i < length; i++) {
			logs[i] = new node();
		}
		for (int i = 0; i < length - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken()) - 1;
			int p = Integer.parseInt(st.nextToken()) - 1;
			logs[o].add(p);
			logs[p].add(o);
		}
		int time = 0;
		issick[0] = true;
		memory = new ArrayList<Integer>();
		memory.add(0);
		int pro = 0;
		int tail = 1;
		while (pro < tail) {
			int childsize = logs[memory.get(pro)].size;
			int nonsick = 0;
			for (int i = 0; i < childsize; i++) {
				int child = logs[memory.get(pro)].child.get(i);
				if (!issick[child]) {
					issick[child] = true;
					memory.add(child);
					nonsick++;
					tail++;
				}
			}
			time += doublecost(nonsick);
			time += nonsick;
			pro++;
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
