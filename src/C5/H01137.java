package C5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class H01137 {
private int[]data=new int [1000000];

public H01137() throws IOException {
	BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st=new StringTokenizer(f.readLine());
	int length=Integer.parseInt(st.nextToken());
	for (int i=0;i<length;i++) {
		st=new StringTokenizer(f.readLine());
		data[Integer.parseInt(st.nextToken())]=Integer.parseInt(st.nextToken())+1;
	}
	for (int i=0;i<1000000;i++) {
		
	}
}
}
