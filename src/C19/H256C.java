package C19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H256C {
	private int trail;
	private int length;

	public H256C() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		trail=Integer.parseInt(st.nextToken());
		length=Integer.parseInt(st.nextToken());
		for(int i=0;i<length;i++) {
			int maxspeed=Integer.parseInt(br.readLine());
			System.out.println(gettime(maxspeed));
		}
		}
private int gettime(int maxspeed) {
		// TODO Auto-generated method stub
	int time=0;
	int speed=1;
	int temptrail=trail;
	while(temptrail>0) {
		if(speed<maxspeed){
			temptrail-=speed;
		}else if(temptrail-speed*2>=0) {
			temptrail-=2*speed;
			time+=1;
		}else if(temptrail-speed>=0) {
			temptrail-=speed;
		}else {
			temptrail-=maxspeed;
		}
		speed++;
		time+=1;
//		System.out.print("-"+speed+"with "+temptrail+" left"+"-");
	}
		return time;
	}
public static void main (String[] args) throws IOException {
	H256C a=new H256C();
}
}
