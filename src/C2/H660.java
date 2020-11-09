package C2;

import java.util.Scanner;

public class H660 {
	public static void main(String[] args) {
		Scanner c = new Scanner(System.in);
		int n = c.nextInt();
		c.close();
		int k = 2;
		int px = n / 2;
		int py = 0;
		int[][] square = new int[n][n];
		square[py][px] = 1;
		while (k <= n * n) {
//		1.若(𝐾 − 1)在第一行但不在最后一列，则将𝐾填在最后一行，(𝐾 − 1)所在列的右一列；
			if (py == 0 && px != n - 1) {
				py = n - 1;
				px = px + 1;
				square[py][px] = k;
			}
//		2.若(𝐾 − 1)在最后一列但不在第一行，则将𝐾填在第一列，(𝐾 − 1)所在行的上一行；
			else if (py != 0 && px == n - 1) {
				px = 0;
				py--;
				square[py][px] = k;
			}
//		3.若(𝐾 − 1)在第一行最后一列，则将𝐾填在(𝐾 − 1)的正下方；
			else if (py == 0 && px == n - 1) {
				py++;
				square[py][px] = k;
			}
//		4.若(𝐾 − 1)既不在第一行，也不在最后一列，如果(𝐾 − 1)的右上方还未填数，则将K填在(𝐾 − 1)的右上方，否则将𝐾填在(𝐾 − 1)的正下方。
			else {
				if (square[py - 1][px + 1] == 0) {
					px++;
					py--;
				} else {
					py++;
				}
				square[py][px] = k;
			}
			k++;
		}
		printSquare(square, n);
	}

	private static void printSquare(int[][] square, int n) {
		for (int i = 0; i < n; i++) {
			StringBuilder temp = new StringBuilder();
			for (int j = 0; j < n; j++) {
				temp.append(square[i][j]);
				if (j != n - 1) {
					temp.append(" ");
				}
			}
			System.out.println(temp.toString());
		}
	}
}