package C1;

import java.util.Scanner;

public class H201a {
	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		int bx = 0;
		int by = 0;
		int rx = 0;
		int ry = 0;
		int lx = 0;
		int ly = 0;
		for (int i = 0; i < 10; i++) {
			String temp = a.nextLine();
			if (temp.contains("B")) {
				bx = temp.indexOf("B");
				by = i;
			}
			if (temp.contains("R")) {
				rx = temp.indexOf("R");
				ry = i;
			}
			if (temp.contains("L")) {
				lx = temp.indexOf("L");
				ly = i;
			}
		}
		a.close();
		if ((bx == rx) && (bx == lx) && ((by - ry) * (ry - ly)) > 0
				|| (by == ry) && (by == ly) && ((bx - rx) * (rx - lx)) > 0) {
			System.out.println(Math.abs(bx - lx) + Math.abs(by - ly) + 1);
		} else {
			System.out.println(Math.abs(bx - lx) + Math.abs(by - ly) - 1);
		}
	}
}
