package C21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H261AA {
	private LinkedList<LinkedList<Integer>> pat;
	private int[][] contain;
	private int[] tra;
	private int N;
	private int M;
	private int K;

	public H261AA() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		tra = new int[N];
		pat = new LinkedList<LinkedList<Integer>>();
		contain = new int[N][2];
		for (int i = 0; i < N; i++) {
			tra[i] = i + 1;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			for (int j = 0; j <= (to - from) / 2; j++) {
				int temp = tra[from + j];
				tra[from + j] = tra[to - j];
				tra[to - j] = temp;
			}
//printarray(replacement);
		}
		int indexoflist = 0;
		for (int i = 0; i < N; i++) {
			int element = i + 1;
			if (contain[element - 1][0] != 0) {
				continue;
			} else {
				LinkedList<Integer> temp = new LinkedList<Integer>();
				indexoflist++;
				int indexofnum = 0;
				temp.add(element);
				contain[element - 1][0] = indexoflist;
				contain[element - 1][1] = indexofnum;
				element = tra[element - 1];
				while (element != temp.get(0)) {
					indexofnum++;
					temp.add(element);
					contain[element - 1][0] = indexoflist;
					contain[element - 1][1] = indexofnum;
					element = tra[element - 1];
				}
				pat.add(temp);
			}
		}
//		for (LinkedList<Integer> j : pat) {
//			for (int k : j) {
//				System.out.print(k);
//			}
//			System.out.println();
//		}
		for (int i = 0; i < N; i++) {
			LinkedList<Integer> temp = pat.get(contain[i][0] - 1);
			System.out.println(temp.get((K + contain[i][1]) % temp.size()));
		}
	}

	private void printarray(int[] array) {
		for (int i : array) {
			System.out.println(i);
		}
	}

	public static void main(String[] args) throws IOException {
		H261AA a = new H261AA();
	}
}
