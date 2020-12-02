package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H00957 {
	private LinkedList<LinkedList<Integer>> data;

	public H00957() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int length = Integer.parseInt(st.nextToken());
		data = new LinkedList<LinkedList<Integer>>();
		st = new StringTokenizer(br.readLine());
		data.add(new LinkedList<Integer>());
		data.getLast().add( Integer.parseInt(st.nextToken()));
		for (int i = 1; i < length; i++) {
			int next = Integer.parseInt(st.nextToken());
			boolean canbeplaced = false;
			for (int j = 0; j < data.size(); j++) {
				if (next > data.get(j).peekLast()) {
//					System.out.println(j);
					data.get(j).add(next);
					canbeplaced = true;
				}
			}
			if (!canbeplaced) {
				LinkedList<Integer> branch=new LinkedList<Integer>();
				LinkedList<Integer> master=data.getLast();
				for(int element:master) {
					if (element<next) {
						branch.add(element);
					}
				}
				branch.add(next);
				data.add(branch);
			}
		}
		LinkedList<Integer> tempbest = new LinkedList<Integer>();
		for (LinkedList<Integer> i : data) {
			if (i.size() > tempbest.size()) {
				tempbest = i;
			}
		}
		System.out.println("max=" + tempbest.size());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tempbest.size(); i++) {
			if (i != 0) {
				sb.append(" ");
			}
			sb.append(tempbest.get(i));
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		H00957 a = new H00957();
	}
}
