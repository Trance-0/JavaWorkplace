package C8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H01098 {
	private int length;
	private int m;
	private int base;
	private int temp;
	private int index;
	private LinkedList<Integer> l=new LinkedList<Integer>();
public H01098() throws IOException {
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st=new StringTokenizer(br.readLine());
	length=Integer.parseInt(st.nextToken());
	m=Integer.parseInt(st.nextToken());
	for (int i=0;i<length;i++) {
		l.add(i+1);
	}
	StringBuilder sb=new StringBuilder();
	for (int i=0;i<length;i++) {
		index=(m-1+base)%l.size();
		temp=l.remove(index);
		sb.append(temp);
		base=index;
		sb.append(" ");
	}
	System.out.println(sb.toString());
}
public static void main(String[] args) throws IOException {
	H01098 a =new H01098();
}
}
