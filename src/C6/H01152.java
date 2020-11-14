package C6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class H01152 {
	private final boolean[] one = { false, false, true, false, false, true, false };
	private final boolean[] two = { true, false, true, true, true, false, true };
	private final boolean[] thr = { true, false, true, true, false, true, true };
	private final boolean[] fou = { false, true, true, true, false, true, false };
	private final boolean[] fiv = { true, true, false, true, false, true, true };
	private final boolean[] six = { true, true, false, true, true, true, true, true };
	private final boolean[] sev = { true, false, true, false, false, true, false };
	private final boolean[] eig = { true, true, true, true, true, true, true };
	private final boolean[] nin = { true, true, true, true, false, true, true };
	private final boolean[] zer = { true, true, true, false, true, true, true };
	private boolean[][] data;
	private int length;
	private String ori;

	public H01152() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		ori = st.nextToken();
		while (length > 0) {
			data = new boolean[ori.length()][8];
			for (int i = 0; i < ori.length(); i++) {
				String temp = ori.substring(i, i + 1);
//				System.out.print(temp);
				if (temp.compareTo("1") == 0) {
					data[i] = one;
				} else if (temp.compareTo("2") == 0) {
					data[i] = two;
				} else if (temp.compareTo("3") == 0) {
					data[i] = thr;
				} else if (temp.compareTo("4") == 0) {
					data[i] = fou;
				} else if (temp.compareTo("5") == 0) {
					data[i] = fiv;
				} else if (temp.compareTo("6") == 0) {
					data[i] = six;
				} else if (temp.compareTo("7") == 0) {
					data[i] = sev;
				} else if (temp.compareTo("8") == 0) {
					data[i] = eig;
				} else if (temp.compareTo("9") == 0) {
					data[i] = nin;
				} else if (temp.compareTo("0") == 0) {
					data[i] = zer;
				}
			}
			printh1();
			printv1();
			printh2();
			printv2();
			printh3();
			System.out.println();
			st = new StringTokenizer(br.readLine());
			length = Integer.parseInt(st.nextToken());
			ori = st.nextToken();
		}
	}

	private void printh3() {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			if (i != 0) {
				temp.append(" ");
			}
			temp.append(" ");
			if (data[i][6]) {
				for (int j = 0; j < length; j++) {
					temp.append("-");
				}
			} else {
				for (int j = 0; j < length; j++) {
					temp.append(" ");
				}
			}
			temp.append(" ");
		}
		System.out.println(temp.toString());
	}

	private void printv2() {
		for (int x = 0; x < length; x++) {
			StringBuilder temp = new StringBuilder();
			for (int i = 0; i < data.length; i++) {
				if (i != 0) {
					temp.append(" ");
				}
				if (data[i][4]) {
					temp.append("|");
				} else {
					temp.append(" ");
				}
				for (int j = 0; j < length; j++) {
					temp.append(" ");
				}
				if (data[i][5]) {
					temp.append("|");
				} else {
					temp.append(" ");
				}
			}
			System.out.println(temp.toString());
		}
	}

	private void printh2() {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			if (i != 0) {
				temp.append(" ");
			}
			temp.append(" ");
			if (data[i][3]) {
				for (int j = 0; j < length; j++) {
					temp.append("-");
				}
			} else {
				for (int j = 0; j < length; j++) {
					temp.append(" ");
				}
			}
			temp.append(" ");
		}
		System.out.println(temp.toString());
	}

	private void printv1() {
		for (int x = 0; x < length; x++) {
			StringBuilder temp = new StringBuilder();
			for (int i = 0; i < data.length; i++) {
				if (i != 0) {
					temp.append(" ");
				}
				if (data[i][1]) {
					temp.append("|");
				} else {
					temp.append(" ");
				}
				for (int j = 0; j < length; j++) {
					temp.append(" ");
				}
				if (data[i][2]) {
					temp.append("|");
				} else {
					temp.append(" ");
				}
			}
			System.out.println(temp.toString());
		}
	}

	private void printh1() {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			if (i != 0) {
				temp.append(" ");
			}
			temp.append(" ");
			if (data[i][0]) {
				for (int j = 0; j < length; j++) {
					temp.append("-");
				}
			} else {
				for (int j = 0; j < length; j++) {
					temp.append(" ");
				}
			}
			temp.append(" ");
		}
		System.out.println(temp.toString());
	}

	public static void main(String[] args) throws IOException {
		H01152 a = new H01152();
	}
}