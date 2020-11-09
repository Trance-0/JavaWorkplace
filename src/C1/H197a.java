package C1;

import java.util.ArrayList;
import java.util.Scanner;

public class H197a {
	private static ArrayList<Integer> findcommonelement(ArrayList<Integer> a, ArrayList<Integer> b) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < a.size(); i++) {
			if (b.contains(a.get(i))) {
				result.add(a.get(i));
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		int answer = 0;
		int line = a.nextInt();
		int length = a.nextInt();
		int[][] data = new int[line][length];
		boolean lock = false;
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = a.nextInt();
			}
		}
		a.close();
		for (int i = 1; i <= length; i++) {
			ArrayList<Integer> smaller = new ArrayList<Integer>();
			for (int j = 0; j < line; j++) {
				ArrayList<Integer> smallerelements = new ArrayList<Integer>();
				lock = false;
				for (int k = 0; k < length; k++) {
					int temp = data[j][k];
					if (lock) {
						smallerelements.add(temp);
					}
					if (temp == i) {
						lock = true;
					}

				}
				if (j == 0) {
					smaller = smallerelements;
				} else {
					smaller = findcommonelement(smaller, smallerelements);
				}
			}
			answer += smaller.size();
		}
		System.out.println(answer);
	}
}