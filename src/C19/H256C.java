package C19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H256C {
	private int trail;
	private int length;
	private static long cnt;

	public H256C() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		trail = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(st.nextToken());
		for (int i = 0; i < length; i++) {
			int maxspeed = Integer.parseInt(br.readLine());
			System.out.println(gettime(maxspeed));
			cnt++;
		}
		System.out.println(cnt);
	}

	private int gettime(int maxspeed) {
		// TODO Auto-generated method stub
		int time = 1;
		int estimate = 1;
		while (trail > estimate) {
			time++;
			int sequencelength = (time + maxspeed - 1);
			if (sequencelength % 2 == 1) {
				estimate = (1 + sequencelength / 2) * sequencelength / 2 + sequencelength / 2;
			} else {
				estimate = (1 + sequencelength / 2) * sequencelength / 2;
			}

//			System.out.println("time " + time + " " + estimate);
			estimate -= (sequencelength - time + 1) * (sequencelength - time) / 2;
//			System.out.println("time " + time + " " + estimate);
			cnt++;
		}
		return time;
	}

	public static void main(String[] args) throws IOException {
		H256C a = new H256C();
	}
}
