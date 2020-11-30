package C12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H00817 {
	private class activity {
		private int start;
		private int end;

		public activity(int s, int e) {
			start = s;
			end = e;
		}
	}

	private class ActivityComparator implements Comparator<activity> {
		@Override
		public int compare(activity o1, activity o2) {
			return o1.end- o2.end;
		}
	}

	private int time;
	private int length;
	private LinkedList<activity> test;
	private int result;

	public H00817() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		length = Integer.parseInt(st.nextToken());
		test = new LinkedList<activity>();
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(bf.readLine());
			test.add(new activity(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		test.sort(new ActivityComparator());
		time=0;
		result=0;
		for (activity a : test) {
			System.out.println("[" + a.start +","+ a.end + "]");
			if(a.start>=time) {
				System.out.println("-"+result+"-[" + a.start +","+ a.end + "]-"+result+"-");
				result++;
				time=a.end;
			}
		}
		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {
		H00817 a = new H00817();
	}
}
