package C9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class H01067 {
	private String beads;
	private int length;
	private int currentbeads;
	private int lastbeads;
	private int finalans;
	private String temp = "w";
	private String c;
	private int finalindex;

	public H01067() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		beads = br.readLine();
		for (int i = 0; i < length; i++) {
			c = beads.substring(i, i + 1);
			if (temp.compareTo("w") == 0 && c.compareTo("w") != 0) {
				temp = c;
			}
			if (c.compareTo(temp) == 0 || c.compareTo("w") == 0) {
				currentbeads++;
			} else {
				finalans = Math.max(currentbeads + lastbeads, finalans);
				System.out.println(currentbeads + " " + temp + " i= " + i);
				lastbeads = currentbeads;
				while (getlastbeads(i).compareTo("w") == 0) {
					if (i == 0) {
						i = beads.length();
					}
					i--;
				}
				currentbeads = 1;
				temp = c;
			}
			if (finalindex > 2 * beads.length()) {
				break;
			}
			if (i == length - 1) {
				i = 0;
			}
			if (currentbeads == length) {
				finalans = length;
				break;
			}
			finalindex++;
		}
		System.out.println(finalans);
	}

	private String getlastbeads(int index) {
		if (index == 0) {
			index = beads.length();
		}
		return beads.substring(index - 1, index);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		H01067 a = new H01067();
	}
}