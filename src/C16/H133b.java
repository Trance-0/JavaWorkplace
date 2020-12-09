package C16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H133b {
	private int cola;
	public H133b() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		cola=Integer.parseInt(st.nextToken());
		System.out.println(cola/20*13*1.4+cola%20/3*2*1.4+cola%20%3*1.4);
	}
	public static void main (String[] args) throws IOException {
		H133b a=new H133b();
	}

}
