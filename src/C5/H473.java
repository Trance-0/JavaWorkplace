package C5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class H473 {

	private int sum;
	private static int[] nationnality = new int[100001];
	private static Queue<Boat> boat = new LinkedList<Boat>();
	private static int datalength;

	private class Boat {
		private int time;
		private int length;
		private int[] nation;

		public Boat(int t, int l, int[] n) {
			time = t;
			length = l;
			nation = n;
		}

		private int gettime() {
			return time;
		}

		private int getlength() {
			return length;
		}

		private int[] getnation() {
			return nation;
		}
	}

	private H473() throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		datalength = Integer.parseInt(st.nextToken());
		for (int i = 0; i < datalength; i++) {
			st = new StringTokenizer(f.readLine());
			int time = Integer.parseInt(st.nextToken());
			int piecelength = Integer.parseInt(st.nextToken());
			int[] passanger = new int[piecelength];
			for (int j = 0; j < piecelength; j++) {

				int temp = Integer.parseInt(st.nextToken());
				passanger[j] = temp;
				nationnality[temp] += 1;
				if (nationnality[temp] == 1) {
					sum++;
				}
			}
			Boat a = new Boat(time, piecelength, passanger);
			boat.add(a);
			while (time - boat.peek().gettime() >= 86400) {
				Boat temp = boat.poll();
				for (int j : temp.getnation()) {
					nationnality[j]--;
					if (nationnality[j] == 0) {
						sum--;
					}
				}

			}
			System.out.println(sum);
		}
	}

	public static void main(String[] args) throws IOException {
		H473 a = new H473();
	}
}
