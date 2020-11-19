package C9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H258a {
	private String line;
	private int ledge;
	private int redge;
	private int firdis;
	private int secdis;
	private int length;

	public H258a() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		line = br.readLine();
		int templength = 0;
		for (int i = 0; i < length; i++) {
			if (line.substring(i, i + 1).compareTo("1") == 0) {
				if (templength == i) {
					ledge = templength;
				}
				if (firdis <= templength) {
					secdis = firdis;
					firdis = templength;
				}
				if (i == length - 1) {
					redge = templength;
				}
			} else {
				templength++;
			}
		}
		// in edge
		int opt1 = ledge - 1;
		int opt2 = redge - 1;
		// in first dis
		int opt3 = firdis / 3;
		// in second first dis
		int opt4 = (secdis - 1) / 2;
		int opt5 = (firdis - 1) / 2;
		int result = 0;
		if (ledge == length) {
			result = length - 2;
		} else if (ledge > 3 * firdis + 1) {
			result = (ledge - 1) / 2;
//		}else if() {

		}

		System.out.println(result);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		H258a a = new H258a();
	}
}
