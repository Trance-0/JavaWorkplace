package C22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class H261C {
    private class node implements Cloneable{
        private int value;
        private ArrayList<Integer> path;
        private node parent;

        public node(int a) {
            value = a;
            path = new ArrayList<Integer>();
        }

        public void add(int i) {
            path.add(i);
        }

        public void setparent(node p) {
            parent = p;
        }
    }

    private node[] nodes;
    private int length;
    private int sum;

    public H261C() throws NumberFormatException, IOException {
        sum=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        nodes=new node[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new node(Integer.parseInt(st.nextToken()));
            System.out.println(nodes[i].value);
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
            root.setparent(null);
            boolean[] isinlist = new boolean[length];
            isinlist[i] = true;
            int rootvalue=root.value;
            ArrayList<Integer> memo = new ArrayList<Integer>();
            memo.add(i);
            int pro = 0;
            int size = 1;
            while (pro < size) {
                root = nodes[memo.get(pro)];
                ArrayList<Integer> child = root.path;
                for (int k : child) {
                    if (!isinlist[k]) {
                        nodes[k].setparent(root);
                        isinlist[k] = true;
                        memo.add(k);
                        size++;
                    }
                }
                pro++;
            }
            for (int f = size- 1; f >0; f--) {
                node temp=nodes[memo.get(f)];
                temp.parent.value=(temp.parent.value+(12-temp.value))%12;
            }
            System.out.println(i+"is ok"+rootvalue+"rootvalue"+memo.get(0)+"equal");
            if(nodes[memo.get(0)].value==0||nodes[memo.get(0)].value==1){
                sum++;
            }
            for(node a:nodes){
                System.out.println(a.value);
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        H261C a = new H261C();
    }
}
