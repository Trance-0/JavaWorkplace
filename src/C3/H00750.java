package C3;

import java.util.Arrays;
import java.util.Scanner;

public class H00750 {
	private int length;
	private int numofcow;
	private int[] data;
	private int biggestelement;
	private int step;
	private int dis;

	public static void main(String[] args) {
		H00750 a = new H00750();
	}

	public H00750() {
		Scanner c = new Scanner(System.in);
		length = c.nextInt();
		numofcow = c.nextInt();
		data = new int[length];
		biggestelement = 0;
		for (int i = 0; i < length; i++) {
			int temp = c.nextInt();
			data[i] = temp;
			if (temp > biggestelement) {
				biggestelement = temp;
			}
		}
		c.close();
		step = biggestelement;
		dis = biggestelement;
		Arrays.sort(data);
		for (int i = 0; i <= log2(biggestelement); i++) {
			adjust();
		}
		System.out.println(dis);
	}

	private int log2(int input) {
		int two = 2;
		int index = 1;
		while (two < input) {
			two *= 2;
			index++;
		}
		return index;
	}

	private void adjust() {
		step = (step + 1) / 2;
		if (comparedis(dis) > 0 || comparedis(dis + step) == 0) {
			dis += step;
		}
		if (comparedis(dis) < 0) {
			dis -= step;
		}
	}

	private int comparedis(int x) {
		int avalablecow = 1;
		int lastelement = data[0];
		for (int i : data) {
			if (i - lastelement >= x) {
				lastelement = i;
				avalablecow++;
			}
		}
		return avalablecow - numofcow;
	}
}
