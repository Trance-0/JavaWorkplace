package C12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H00819 {
	private class fountain{
		private double start;
		private double end;
		public fountain(double s, double e) {
			start = s;
			end = e;
		}
	}
	private class fountainComparator implements Comparator<fountain> {
		@Override
		public int compare(fountain o1, fountain o2) {
			return (int)((o1.start- o2.start)*100000);
		}
	}
	private int n;
	private int L;
	private int W;
	private int length;
	private int result;
	private String  temp;
	private LinkedList<fountain> sets; 
public H00819() throws IOException {
	BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
	temp=bf.readLine();
	StringTokenizer st=new StringTokenizer(temp);
	length=Integer.parseInt(st.nextToken());
	temp=bf.readLine();
	st=new StringTokenizer(temp);
//	System.out.println(temp);
	for(int i=0;i<length;i++) {

		n=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		W=Integer.parseInt(st.nextToken());
		sets=new LinkedList<fountain>();
		result=1;
		for(int j=0;j<n;j++) {
			temp=bf.readLine();
			st=new StringTokenizer(temp);
			int pos=Integer.parseInt(st.nextToken());
			int radius=Integer.parseInt(st.nextToken());
			double cover=getlengthcovered(radius);
			sets.add(new fountain(pos-cover,pos+cover));
		}
		sets.sort(new fountainComparator());
		double current=0;
		double bestend=0;
		for (fountain a : sets) {
			if(a.start>current){
				if(bestend>L) {
					break;
				}
				if(bestend==current) {
					result=-1;
					break;
				}
					current=bestend;
					result++;
			}if(a.start<=current) {
				bestend=Math.max(bestend, a.end);
//				System.out.println(bestend);
			}
//			System.out.println("current: "+current+"[" + a.start +","+ a.end + "]");
		}
		System.out.println(result);
		temp=bf.readLine();
		st=new StringTokenizer(temp);
//		System.out.println("------------------");
	}
}
public double getlengthcovered(int radius) {
	return  Math.sqrt(Math.pow(2*radius,2)-Math.pow(W,2))/2;
}
public static void main (String[]args) throws IOException {
	H00819 a=new H00819();
}
}
