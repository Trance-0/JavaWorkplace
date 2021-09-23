package C44;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

/*
* 回边（后向边），父子边（前向边），横插边。
* tarjan
*/

public class H01298 {
    public class AdjacencyList {
        public class edge {
            private LinkedList<Integer> nearby;
            public edge(){
                nearby = new LinkedList<>();
            }
        }

        private edge[] edges;

        public AdjacencyList(int V) {
            edges = new edge[V];
        }

        public void add(int from, int to) {
            edges[from].nearby.addLast(to);
        }

        public LinkedList<Integer> get(int from) {
            return edges[from].nearby;
        }

        public String toString(int greatest) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < greatest; i++) {
                LinkedList<Integer> temp = get(i);
                for (int j : temp) {
                    sb.append("[" + i + "," + j + "]" + "\n");
                }
            }
            return sb.toString();
        }

    }

    public void Tarjan(AdjacencyList E, int[] layer, int[] group, int start, Stack<Integer> order, int count) {
        layer[start] = count;
        group[start] = count;
        count++;
        LinkedList<Integer> tempEdge = E.get(start);
        for (int to : tempEdge) {
            if (layer[to] == -1) {
                Tarjan(E, layer, group, to, order, count);
                group[start] = Math.min(group[start], group[to]);
            } else {
                group[start] = Math.min(group[start], group[to]);
            }
        }
        if (layer[start] == group[start]) {
            order.add(start);
        }
    }

    private int V;
    private AdjacencyList E;
    private Stack<Integer> order;

    public H01298() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = new AdjacencyList(V);
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken())-1;
            while (temp != 0) {
                E.add(i, temp);
                temp = Integer.parseInt(st.nextToken())-1;
            }
        }
        int[] layer = new int[V];
        for (int i = 0; i < V; i++) {
            layer[i] = -1;
        }
        int[] group = new int[V];
        int count = 0;
        for (int i = 0; i < V; i++) {
            if (layer[i] == -1) {
                Tarjan(E, layer, group, i, order, count);
            }
        }
        printarr(group);
    }

    public void printarr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        H01298 a = new H01298();
    }
}
