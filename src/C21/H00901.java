package C21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class H00901 {
	private class node {
		private node parent;
		private String content;
		private LinkedList<node> child;

		public node(String c) {
			content = c;
			child = new LinkedList<node>();
		}

		public node(String c, node p) {
			parent = p;
			content = c;
			child = new LinkedList<node>();
		}

		public boolean haschild(String t) {
			for (node i : child) {
				if (i.content.compareTo(t) == 0) {
					return true;
				}
			}
			return false;
		}

		public node getchild(String t) {
			for (node i : child) {
				if (i.content.compareTo(t) == 0) {
					return i;
				}
			}
			return null;
		}

	}

	private node root = new node("root");

	public H00901() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = br.readLine();
		int size = 1;
		while (!temp.isEmpty()) {
			node current = root;
			for (int i = 0; i < temp.length(); i++) {
				String ch = temp.substring(i, i + 1);
				node newc = new node(ch, current);
				if (!current.haschild(ch)) {
					current.child.add(new node(ch, current));
					size++;
				}
				current = current.getchild(ch);
			}
			temp = br.readLine();
		}
		System.out.println(size);
	}

	public static void main(String[] args) throws IOException {
		H00901 a = new H00901();
	}
}
