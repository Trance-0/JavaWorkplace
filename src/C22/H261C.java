package C22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class H261C {
    private class node {
        private int value;
        private ArrayList<Integer> path;
        private int parent;
        public node(int a) {
            value = a;
            path = new ArrayList<Integer>();
        }

        public void add(int i) {
            path.add(i);
        }
        public void setparent(int p){
parent=p;
        }
    }

    private node[] nodes;
    private int length;

    public H261C() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < length; i++) {
            nodes[i] = new node(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < length - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            nodes[x].add(y);
            nodes[y].add(x);
        }
        for (int i = 0; i < length; i++) {
            node root = nodes[i];
            boolean[] isinlist = new boolean[length];
            isinlist[i] = true;
            ArrayList<node> memo = new ArrayList<node>();
            memo.add(root);
            int pro = 0;
            int size = 1;
            while (pro < size) {
                root = memo.get(pro);
                ArrayList<Integer> child = root.path;
                for (int k : child) {
                    if (!isinlist[k]) {
                        isinlist[k] = true;
                        memo.add(nodes[k]);
                    }
                }
            }
            for(int f=memo.size()-1;f<=0;f--){

            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        H261C a = new H261C();
    }
}
