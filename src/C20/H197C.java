package C20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;

public class H197C {
	private String[] cows= {"Beatrice","Belinda","Bella","Bessie","Betsy","Blue","Buttercup","Sue"};
private LinkedList<LinkedList<String>> group;
private int length;

public H197C() throws NumberFormatException, IOException {
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	group=new LinkedList<LinkedList<String>>();
	for (String i:cows) {
		 LinkedList<String> temp=new LinkedList<String>();
		 temp.add(i);
		group.add(temp);
	}
	length=Integer.parseInt(br.readLine());
	for(int i=0;i<length;i++) {
		String order=br.readLine();
		LinkedList<Integer> element=new LinkedList<Integer>();
		for (int j=0;j<group.size();j++) {
			if(order.contains(group.get(j).peekFirst())) {
				element.add(j);
				element.add(0);
			}
			else if(order.contains(group.get(j).peekLast())) {
				element.add(j);
				element.add(1);
			}
			if (element.size()>=4) {
				break;
			}
		}
		merge(element);
	}
	for (int k=0;k<group.size();k++) {
		if(group.get(k).peekLast().compareTo(group.get(k).peekFirst())>0) {
			LinkedList<String> temp=new LinkedList<String>();
			for (int l=0;l<group.get(k).size();l++) {
				temp.add(group.get(k).get(l));
			}
			group.set(k, temp);
		}
	}
	group.sort(new Comparator<LinkedList<String>>() {
		@Override
		public int compare(LinkedList<String> o1, LinkedList<String> o2) {
			// TODO Auto-generated method stub
			return o1.peekFirst().compareTo(o2.peekFirst());
		}
		
	});
	for (LinkedList<String> p:group) {
		for (String f:p) {
			System.out.println(f);
		}
	}
}

private void test() {
	StringBuilder sb=new StringBuilder();
	sb.append("{");
	for (LinkedList<String> p:group) {
		sb.append("{");
		for (String f:p) {
			sb.append(f);
			sb.append(",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("}");
	}
	sb.append("}");
	System.out.println(sb.toString());
}
private void merge(LinkedList<Integer> element) {
	// TODO Auto-generated method stub
	LinkedList<String> g1=group.get(element.get(0));
	LinkedList<String> g2=group.get(element.get(2));
	LinkedList<String> sum=new LinkedList<String>();
	int o1=element.get(1);
	int o2=element.get(3);
	if (o1==1&& o2==0) {
		sum=g1;
		for (int i=0;i<g2.size();i++) {
			sum.addLast(g2.pollFirst());
		}
	}else if(o1==1&& o2==1) {
		sum=g2;
		for (int i=0;i<g1.size();i++) {
			sum.addLast(g1.pollFirst());
		}
	}else if(o1==0&& o2==0) {
		if(g1.size()>g2.size()){
		sum=g1;
		for (int i=0;i<g2.size();i++) {
			sum.addFirst(g2.pollFirst());
		}
		}else {
			sum=g2;
			for (int i=0;i<g1.size();i++) {
				sum.addFirst(g1.pollFirst());
			}
		}
	}else {
		if(g1.size()>g2.size()){
			sum=g1;
			for (int i=0;i<g2.size();i++) {
				sum.addLast(g2.pollLast());
			}
			}else {
				sum=g2;
				for (int i=0;i<g1.size();i++) {
					sum.addLast(g1.pollLast());
				}
			}
	}
	group.set(element.get(0), sum);
	group.remove(g1);
	group.remove(g2);
}


public static void main (String[] args) throws NumberFormatException, IOException {
	H197C a=new H197C();
}
}
