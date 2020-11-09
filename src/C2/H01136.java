package C2;

/*
ID: your_id_here
LANG: JAVA
TASK: test
*/
import java.util.Scanner;

public class H01136 {
	public static int[] findd(String data, int length) {

		int[] result = new int[2];
		int indexofd = 0;
		int tempindexofd = 0;
		int d = 0;
		int tempd = 0;
		for (int i = 0; i < length; i++) {
			String temp = data.substring(i, i + 1);
			if (temp.compareTo("0") == 0) {
				tempd++;

				if (tempd == 1) {
					tempindexofd = i;
				}
			}
			if (tempd > d) {
				d = tempd;
				indexofd = tempindexofd;
			}
			if (i == length - 1 || temp.compareTo("1") == 0) {
				tempd = 0;
			}
		}
		result[0] = d;
		result[1] = indexofd;
		return result;
	}

	public static void main(String[] args) {
		Scanner c = new Scanner(System.in);
		int length = c.nextInt();
		String data = c.next();
		c.close();
		int[] d = findd(data, length);
		StringBuilder temp = new StringBuilder();
		temp.append(data.substring(0, d[1] + d[0] / 2));
		temp.append("1");
		temp.append(data.substring(d[1] + d[0] / 2 + 1));
		data = temp.toString();
		d = findd(data, length);
		temp = new StringBuilder();
		temp.append(data.substring(0, d[1] + d[0] / 2));
		temp.append("1");
		temp.append(data.substring(d[1] + d[0] / 2 + 1));
		data = temp.toString();
		d = findd(data, length);
		System.out.println(d[0]);
	}
}
