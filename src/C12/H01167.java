package C12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class H01167 {
	private int N;
	private int B;
	private int D;
	private Queue<Integer> result;

	public H01167() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		result = new LinkedList<Integer>();
		result.add(0);
		int maxnum = (int) Math.pow(2, B);
		for (int i = 0; i < maxnum; i++) {
			if (hamming(i)) {
				result.add(i);
			}
			if (result.size() == N) {
				break;
			}
		}
		int count = 0;
		while (!result.isEmpty()) {
			System.out.print(result.poll());
			count++;
			if (count == 10) {
				System.out.println();
				count = 0;
			} else if (result.size() == 0) {
				System.out.println();
			} else {
				System.out.print(" ");
			}
		}
	}

	private boolean hamming(int i) {
		for (int k : result) {
			int dif = i ^ k;
			if (getones(dif) < D) {
				return false;
			}
		}
		return true;
	}

	private int getones(int dif) {
		int ones = 0;
		for (int i = 0; i < 32; i++) {
			ones += dif & 1;
			dif = dif >> 1;
		}
		return ones;
	}

	public static void main(String[] args) throws IOException {
		H01167 a = new H01167();
	}
}
