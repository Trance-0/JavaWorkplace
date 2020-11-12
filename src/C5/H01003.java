package C5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class H01003 {
	private int player;
	private int decksize;
	private int suffle;
	private List<Integer> B;
	
	public H01003() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		player=Integer.parseInt(st.nextToken());
		decksize=Integer.parseInt(st.nextToken());
		suffle=Integer.parseInt(st.nextToken());
		Queue<Integer> deck=new LinkedList<Integer>();
		B=new ArrayList<Integer>();
		for(int i=0;i<decksize;i++) {
			deck.add(i+1);
		}
		int index=0;
		while(index<decksize) {
			if((index+1)%player==0) {
				B.add(deck.poll());
			}else {
				deck.poll();
			}
			if(deck.size()>0) {
			for (int i=0;i<suffle;i++) {
				int temp=deck.poll();
				deck.add(temp);
			}
			}
			index++;
		}
		B.sort(null);
		for (int i=0;i<B.size();i++) {
			System.out.println(B.get(i));
		}
	}
	private void printqueue(Queue<Integer> deck){
		for (int i=0;i<deck.size();i++) {
			int temp=deck.poll();
			deck.add(temp);
			System.out.print(temp);
		}
	}
	public static void main (String []args) throws IOException {
		H01003 a =new H01003();
	}

}
