package C22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H01176 {

	private class node{
		private LinkedList<Integer>child;
		public node() {
			child=new LinkedList<Integer>();
		}
	}
	private node[] logs;
	private int length;
	private LinkedList<Integer> memory;

	public H01176() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		logs = new node[length];
		StringTokenizer st;
		for(int i=0;i<length;i++) {
			logs[i]=new node();
		}
		for (int i = 0; i < length - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken())-1;
			int l = Integer.parseInt(st.nextToken())-1;
			logs[k].child.add(l);
			logs[l].child.add(k);
		}
		int pro = 0;
		memory = new LinkedList<Integer>();
		memory.add(1);
		int time = 0;
		boolean isfirst=true;
		while (pro < memory.size()) {
			LinkedList<Integer> child= logs[memory.get(pro)].child;
			for(int i:child){
				if(!memory.contains(i)){
					memory.add(i);
				}
			} 
			if(isfirst) {
				isfirst=false;
				time += doublecost(child.size());
			}else {
			time += doublecost(child.size()-1);
			}
			time += child.size()-1;
			pro++;
		}
		System.out.println(time);
	}

	private int doublecost(int size) {
		if(size<1) {
			return 0;
		} else if (size <2) {
			return 1;
		} else if (size < 4) {
			return 2;
		} else if (size < 8) {
			return 3;
		} else if (size < 16) {
			return 4;
		} else if (size < 32) {
			return 5;
		} else if (size < 64) {
			return 6;
		} else if (size < 128) {
			return 7;
		} else if (size < 256) {
			return 8;
		} else if (size < 512) {
			return 9;
		} else if (size < 1024) {
			return 10;
		} else if (size < 2048) {
			return 11;
		} else if (size < 4096) {
			return 12;
		} else if (size < 8192) {
			return 13;
		} else if (size < 16384) {
			return 14;
		} else if (size < 32768) {
			return 15;
		} else if (size < 65536) {
			return 16;
		} else {
			return 17;
		}
	}

	private void printlist(LinkedList<Integer> a) {
		StringBuilder sb = new StringBuilder();
		for (int i : a) {
			sb.append(i);
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		H01176 a = new H01176();
	}
}
