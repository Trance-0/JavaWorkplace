package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H00957 {
	private int[] data;
	private int[] l;

	public H00957() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int length = Integer.parseInt(st.nextToken());
		data = new int[length];
		l= new int[length];
		st = new StringTokenizer(br.readLine());
		int max=1;
		int maxindex=0;
		for (int i = 0; i < length; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			for (int j = i;j>=0;j--) {
				if (data[j] < data[i]) {
					l[i]=l[j];
					if(l[i]>=max) {
						max=l[i]+1;
						maxindex=i;
					}
					break;
				}
			}
			l[i]++;
		}
	LinkedList<Integer>temp=new LinkedList<Integer>();
	temp.addLast(data[maxindex]);
	int lastindex=max;
	System.out.println("max=" + max);
		for (int i=maxindex-1;i>=0;i--) {
//			printlist(i);
//			System.out.println(l[i]+1);
			if (l[i]+1==lastindex && data[i]<data[maxindex]) {
				temp.addLast(data[i]);
				max=data[i];
				lastindex--;
				if(lastindex<0) {
					break;
				}
			}
		}
	printlist(temp);
}

	private void printlist(LinkedList<Integer> tempbest) {
		StringBuilder sb = new StringBuilder();
		for (int i = tempbest.size()-1; i>=0; i--) {
			if (i != tempbest.size()-1) {
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