package C9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class H01067 {
	private String[] beads;
	private int length;
	private int finalans;
	private String lbead;
	private String cbead;
	private int b;
	private int f;

	public H01067() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		beads = new String[length];
		String temp = br.readLine();
		for (int i = 0; i < length; i++) {
			beads[i] = temp.substring(i, i + 1);
		}
		lbead = beads[length - 1];
		for (int i = 0; i < length; i++) {
			if (beads[i].compareTo("w") != 0) {
				cbead = beads[i];
				if (isdifferent(lbead, cbead)) {
//					System.out.println(
//							"i=" + i + " forward " + countforward(i, cbead) + " backward " + countbackward(i, lbead));
					finalans = Math.max(countforward(i, cbead) + countbackward(i, lbead), finalans);
				}
				lbead = cbead;
			}
		}
		if (finalans == 0 || finalans > length) {
			System.out.println(length);
		} else {
			System.out.println(finalans);
		}
	}

	private int countforward(int i, String color) {
		int count = 0;
		while (beads[i].compareTo(color) == 0 || beads[i].compareTo("w") == 0) {
			i++;
			if (i == length) {
				i = 0;
			}
			if (count > length) {
				return -1;
			}
			count++;
		}
		return count;
	}

	private int countbackward(int i, String color) {
		int count = 0;
		if (i == 0) {
			i = length;
		}
		i--;
		while (beads[i].compareTo(color) == 0 || beads[i].compareTo("w") == 0) {
			if (i == 0) {
				i = length;
			}
			if (count > length) {
				return -1;
			}
			i--;
			count++;
		}
		return count;
	}

	private boolean isdifferent(String lbead2, String cbead2) {
		return (lbead.compareTo("r") == 0 && cbead.compareTo("b") == 0)
				|| (lbead.compareTo("b") == 0 && cbead.compareTo("r") == 0);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		H01067 a = new H01067();
	}
}