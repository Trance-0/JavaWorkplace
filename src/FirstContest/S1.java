package FirstContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S1 {

	private ArrayList<int[]> logs;

	private int length;
	private ArrayList<Integer> memory;

	public S1() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		logs = new ArrayList<int[]>();
		StringTokenizer st;
		for (int i = 0; i < length - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int[] temp = new int[2];
			temp[0] = Integer.parseInt(st.nextToken());
			temp[1] = Integer.parseInt(st.nextToken());
			logs.add(temp);
		}
		int pro = 0;
		int tail = 1;
		memory = new ArrayList<Integer>();
		memory.add(1);
		int time = 0;
		while (pro < tail) {
			int childsize = 0;
			for (int i = 0; i < logs.size(); i++) {
				if (i >= 0) {
					int[] templog = logs.get(i);
					if (templog[0] == memory.get(pro)) {
						childsize++;
						memory.add(templog[1]);
						logs.remove(templog);
						i--;
					} else if (templog[1] == memory.get(pro)) {
						childsize++;
						memory.add(templog[0]);
						logs.remove(templog);
						i--;
					}
					tail++;
				}
			}
			time += doublecost(childsize);
			time += childsize;
			pro++;
		}
		System.out.println(time);
	}

	private int doublecost(int size) {
		if (size == 0) {
			return 0;
		} else if (size == 1) {
			return 1;
		} else if (size < 4) {
			return 2;
		} else if (size < 8) {
			return 3;
		} else if (size < 16) {
			return 4;
		} else if (size < 32) {
			return 5;
		} else if (size < 64) {
			return 6;
		} else if (size < 128) {
			return 7;
		} else if (size < 256) {
			return 8;
		} else if (size < 512) {
			return 9;
		} else if (size < 1024) {
			return 10;
		} else if (size < 2048) {
			return 11;
		} else if (size < 4096) {
			return 12;
		} else if (size < 8192) {
			return 13;
		} else if (size < 16384) {
			return 14;
		} else if (size < 32768) {
			return 15;
		} else if (size < 65536) {
			return 16;
		} else {
			return 17;
		}
	}

	private void printlist(ArrayList<Integer> a) {
		StringBuilder sb = new StringBuilder();
		for (int i : a) {
			sb.append(i);
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		S1 a = new S1();
	}
}
