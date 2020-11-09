package C4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class H473 {
	private static int datalength;
	private static int[] time;
	private static int[] piecelength;
	private static List<Integer> passanger;
	private static int end;
	private static int begin;
	private static int index;

	public static void main(String[] args) throws IOException {
		end = 0;
		index = 0;
		passanger = new ArrayList<Integer>();
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		datalength = Integer.parseInt(st.nextToken());
		time = new int[datalength];
		piecelength = new int[datalength];
		for (int i = 0; i < datalength; i++) {
			time[i] = Integer.parseInt(st.nextToken());
			piecelength[i] = Integer.parseInt(st.nextToken());
			end += piecelength[i];
			for (int j = 0; j < piecelength[i]; j++) {
				int temp = Integer.parseInt(st.nextToken());
				passanger.add(temp);
			}
			while (time[i] - time[index] >= 86400) {
				begin += piecelength[index];
				index++;

			}
			if (begin < 0) {
				begin = 0;
			}
			List<Integer> temp = (List<Integer>) passanger.subList(begin, end);
			if (begin == 0) {
				begin = -1;
			}
//			System.out.println(passanger);
//			System.out.println(temp);
//			System.out.println("begin " + begin);
//			System.out.println("index " + index);
//			System.out.println("end " + end);
			System.out.println(countelements(temp));
		}
	}

	private static int countelements(List<Integer> temp) {
		// TODO Auto-generated method stub
		int result = 0;
		temp.sort(null);
		int lastelement = 0;

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i) != lastelement) {
				result++;
			}
			lastelement = temp.get(i);
		}
		return result;
	}

}
