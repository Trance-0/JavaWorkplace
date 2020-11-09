package C3;

import java.util.Scanner;

public class H00749 {
	private static int storeline;
	private static int needline;
	private static int[] linehave;
	private static int finecut;
	private static int adrate;
	private static int longestline;

	public static void main(String[] args) {
		H00749 a = new H00749();
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
		adrate = adrate / 2;
		if (comparecutfine(finecut + adrate) > 0) {
			finecut += adrate;
		} else {
			finecut -= adrate;
		}
	}

	private int comparecutfine(int cut) {
		int cutget = 0;
		for (int line : linehave) {
			cutget += line / cut;
		}
		return cutget - needline;
	}

	public H00749() {
		Scanner c = new Scanner(System.in);
		storeline = c.nextInt();
		needline = c.nextInt();
		linehave = new int[storeline];
		longestline = 0;
		for (int i = 0; i < storeline; i++) {
			linehave[i] = (int) c.nextDouble() * 100;
			if (linehave[i] > longestline) {
				longestline = linehave[i];
			}
		}
		finecut = longestline;
		adrate = longestline;
		for (int i = 0; i < log2(longestline); i++) {
			adjust();
			System.out.println(finecut);
		}
		System.out.printf("%.2f", (double) finecut / 100);
	}
}
