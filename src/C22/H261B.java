package C22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class H261B {
	private int length;

	private class line {
		private ArrayList<Integer> element;

		public line() {
			element = new ArrayList<Integer>();
		}
	}

	private int[] hx = new int[20001];
	private int[] hy = new int[20001];
	private ArrayList<line> x;
	private ArrayList<line> y;

public H261B() throws NumberFormatException, IOException {
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	length=Integer.parseInt(br.readLine());
	for(int i=0;i<length;i++) {
		st=new StringTokenizer(br.readLine());
		int tx=Integer.parseInt(st.nextToken())+10001;
		int ty=Integer.parseInt(st.nextToken())+10001;
		if(hx[tx]!=0) {
			x.get(hx[tx]).element.add(ty);
		}else {
		hx[tx]=x.size();
		lijne
		x.add(e);
		}
		if(hy[ty]!=0){
			y.get(hy[ty]).element.add(tx);
		}else {
			
		}
	}
}

	public static void main(String[] args) throws NumberFormatException, IOException {
		H261B a = new H261B();
	}
}
