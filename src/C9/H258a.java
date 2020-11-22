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
	private int result;
	private boolean allzero = true;

	public H258a() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		line = br.readLine();
		int templength = 0;
		for (int i = 0; i < length; i++) {
			if (line.substring(i, i + 1).compareTo("1") == 0) {
				allzero = false;
				if (templength == i) {
					ledge = templength;
				} else if (firdis <= templength) {
					secdis = firdis;
					firdis = templength;
				}
				templength = 0;
			} else {
				templength++;
			}
			if (i == length - 1) {
				redge = templength;
			}
		}
//		System.out.println(firdis);
//		System.out.println(secdis);
//		System.out.println(ledge);
		System.out.println(redge);
		// fs
		int fs = 0;
		// ff
		int ff = 0;
		// ll
		int ll = 0;
		// rr
		int rr = 0;
		// fl
		int fl = 0;
		// fr
		int fr = 0;
		// lr
		int lr = 0;
		if (ledge != 0 && redge != 0) {
			if (firdis != 0) {
				if (secdis != 0) {
					fs = insert1(secdis);
				}
				ff = insert2(firdis);
				fl = Math.min(insert1(firdis), inserte1(ledge));
				fr = Math.min(insert1(firdis), inserte1(redge));
			}
			ll = inserte2(ledge);
			rr = inserte2(redge);
			lr = Math.min(inserte1(redge), inserte1(ledge));
		} else if (ledge != 0 || redge != 0) {
			if (ledge != 0) {
				if (firdis != 0) {
					if (secdis != 0) {
						fs = insert1(secdis);
					}
					ff = insert2(firdis);
					fl = Math.min(insert1(firdis), inserte1(ledge));
				}
				ll = inserte2(ledge);
			} else {
				if (firdis != 0) {
					if (secdis != 0) {
						fs = insert1(secdis);
					}
					ff = insert2(firdis);
					fr = Math.min(insert1(firdis), inserte1(redge));
				}
				rr = inserte2(redge);
			}
		} else {
			if (firdis != 0) {

				if (secdis != 0) {
					fs = insert1(secdis);
				}
				ff = insert2(firdis);
			}
		}
		result = Math.max(fs, Math.max(ff, Math.max(ll, Math.max(rr, Math.max(fl, Math.max(fr, lr))))));
		System.out.println(fs + " fs");
		System.out.println(ff + " ff");
		System.out.println(ll + " ll");
		System.out.println(rr + " rr");
		System.out.println(fl + " fl");
		System.out.println(fr + " fr");
		System.out.println(lr + " lr");
		if (allzero) {
			result = line.length() - 1;
		}
		System.out.println(result);
	}

	private int insert1(int dis) {
		return (dis + 1) / 2;
	}

	private int inserte1(int dis) {
		return dis;
	}

	private int inserte2(int dis) {
		return (dis - 1) / 2 + 1;
	}

	private int insert2(int dis) {
		return (dis + 1) / 3;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		H258a a = new H258a();
	}
}
