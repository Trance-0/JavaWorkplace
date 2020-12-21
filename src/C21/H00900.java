package C21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H00900 {
	private node[] tree;

	private class node {
		LinkedList<Integer> child;

		private node() {
			child = new LinkedList<Integer>();
		}
	}

	public H00900() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int treelength = Integer.parseInt(st.nextToken());
		tree = new node[treelength];
		for (int i = 0; i < treelength; i++) {
			tree[i] = new node();
		}
		int root = 0;
		int size = Integer.parseInt(st.nextToken());
		node best = new node();
		int max = 0;
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			int father = Integer.parseInt(st.nextToken());
			int son = Integer.parseInt(st.nextToken());
			tree[father].child.add(son);
			if (root == 0 || root == son) {
				root = father;
			}
			if (tree[father].child.size() > best.child.size()) {
				best = tree[father];
				max = father;
			}
		}
		System.out.println(root);
		System.out.println(max);
		StringBuilder sb = new StringBuilder();
		for (int i : tree[max].child) {
			sb.append(i);
			sb.append(" ");
		}
		sb.delete(sb.length() - 1, sb.length());
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		H00900 a = new H00900();
	}
}
