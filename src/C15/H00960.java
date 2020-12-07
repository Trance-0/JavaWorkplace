package C15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class H00960 {
	private int length;
	private int[][] data;
	private int[] count;
	public H00960() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		data=new int[length][2];
		count=new int[length];
		for (int i=0;i<length;i++) {
			st = new StringTokenizer(br.readLine());
			data[i][0]=Integer.parseInt(st.nextToken());
			data[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(data, new Comparator<int[]>() {
			@Override
			public int compare(int[]o1, int[] o2) {
				return o1[0]-o2[0];
			}
			
		});
		int bestcount=1;
		for(int i=0;i<length;i++) {
			for(int j=i;j>=0;j--) {
				System.out.println(data[j][0]+":"+data[j][1]);
				if(data[i][0]>data[j][0]&& data[i][1]>data[j][1]){
					count[i]=count[j];
					if(bestcount<count[i]+1) {
						bestcount=count[i]+1;
					}
					System.out.println(count[i]);
					break;
				}
			}
			count[i]++;
		}
		System.out.println(bestcount);
	}

	public static void main(String[] args) throws IOException {
		H00960 a = new H00960();
	}
}
