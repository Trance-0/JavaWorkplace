package C21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H261A {
	public int N;
	public int M;
	public int K;
	public int[] replacement;
	public int[] simulation;
	public H261A() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		replacement=new int[N];
		simulation=new int[N];
		for (int i=0;i<N;i++) {
				replacement[i]=i;
				simulation[i]=i+1;
		}
		for (int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken())-1;
			int to=Integer.parseInt(st.nextToken())-1;
			for (int j=0;j<=(to-from)/2;j++) {
				int temp=replacement[from+j];
				replacement[from+j]=replacement[to-j];
				replacement[to-j]=temp;
			}
//			printarray(replacement);
		}
		for(int i=0;i<K;i++) {
			int[] temp=new int[N];
			for(int j=0;j<N;j++) {
				temp[j]=simulation[replacement[j]];
			}
			simulation=temp;
			if (issame(simulation)) {
				K=K%i;
				i=0;
			}
		}
		printarray(simulation);
	}
	private boolean issame(int[] s) {
		for(int i=0;i<N;i++) {
			if(s[i]!=i) {
				return false;
			}
		}
		return true;
	}
	private void printarray(int[] array) {
for(int i:array) {
			System.out.println(i);
		}
	}
public static void main(String[] args) throws IOException {
	H261A a=new H261A();
}
}
