package C18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H257B {

	private int length;
	private String ori;
	private String dis;
	private int count;
	public H257B() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		length=Integer.parseInt(br.readLine());
		ori=br.readLine();
		dis=br.readLine();
		count=0;
		boolean issame=true;
		for (int i=0;i<length;i++) {
			char temp=ori.charAt(i);
			if(temp!=dis.charAt(i)) {
				issame=false;
			}
			else {
				if(!issame) {
					count++;
				}
				issame=true;
			}
		}
		System.out.println(count);
	}
	public static void main(String[] args) throws IOException {
		H257B a=new H257B();
	}
}
