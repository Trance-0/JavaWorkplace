package C14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H00959 {
	private int length;
	private int mine[];
	private int bestcount[];
	private int bestrout[];
	public H00959() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length=Integer.parseInt(st.nextToken())+1;
		st = new StringTokenizer(br.readLine());
		mine=new int[length];
		bestrout=new int[length];
		for (int i=1;i<length;i++) {
			mine[i]=Integer.parseInt(st.nextToken());
		}
		bestcount=mine.clone();
		st = new StringTokenizer(br.readLine());
		int in=Integer.parseInt(st.nextToken());
		int out=Integer.parseInt(st.nextToken());
		int greatestindex=0;
		while(in!=0&&out!=0) {
			if(mine[in]+mine[out]>bestcount[out]) {
			bestcount[out]=mine[in]+mine[out];
			if(bestcount[out]>bestcount[greatestindex]) {
				greatestindex=out;
			}
			bestrout[out]=in;
			}
			st = new StringTokenizer(br.readLine());
			in=Integer.parseInt(st.nextToken());
			out=Integer.parseInt(st.nextToken());
		}
		int result=bestcount[greatestindex];
		StringBuilder sb=new StringBuilder();
		sb.append(greatestindex);
		while(bestrout[greatestindex]!=0) {
			greatestindex=bestrout[greatestindex];
			sb.append("-");
			sb.append(greatestindex);
		}
		System.out.println(sb.toString());
		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {
		H00959 a = new H00959();
	}
}
