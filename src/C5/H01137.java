package C5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class H01137 {
	private List<cow> cows = new ArrayList<cow>();
	private List<Integer> illarea = new ArrayList<Integer>();
	private int sum;
	private int R;
	private int length;

	private class cowcomparator implements Comparator<cow> {

		@Override
		public int compare(cow o1, cow o2) {
			// TODO Auto-generated method stub
			return o1.index - o2.index;
		}

	}

	private class cow {
		private int index;
		private boolean isill;

		public cow(int i, int l) {
			index = i;
			if (l == 1) {

				isill = true;
			} else {
				isill = false;
			}
		}
	}

	public H01137() throws IOException {
		sum = 0;
		R = 1000000;
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		length = Integer.parseInt(st.nextToken());
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(f.readLine());
			cow temp = new cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			cows.add(temp);
		}
		cows.sort(new cowcomparator());
		cow fomer = null;
		cow current = null;
		int area = 0;
		for (int i = 0; i < length; i++) {
			cow sub = cows.get(i);
			fomer = current;
			current = sub;
			if (fomer != null && current != null) {
				if (current.isill && !fomer.isill) {
					R = Math.min(R, current.index - fomer.index);
				}
				if (fomer.isill && !current.isill) {
					R = Math.min(R, current.index - fomer.index);
				}

				if (former.&&current.index - fomer.index >= R) {
					sum++;
				}
			}
		}
		System.out.println(sum);
	}

	public static void main(String[] args) throws IOException {
		H01137 a = new H01137();
	}
}
