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

            public edge() {
                nearby = new LinkedList<>();
            }
        }

        private edge[] edges;

        public AdjacencyList(int V) {
            edges = new edge[V];
            for (int i = 0; i < V; i++) {
                edges[i] = new edge();
            }
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
    
    //this method is proven to be usable.
    //Global variable required for Tarjan method.
    private int[] group;
private int[] layer;
private int[] minAncestor;
private boolean[] inStack;
private Stack<Integer> order;
private int currentLayer;
private int groupCount;
    public void Tarjan(AdjacencyList E ,  int start ) {
        layer[start] = currentLayer;
        minAncestor[start] = currentLayer;
        currentLayer++;
        inStack[start] = true;
        order.add(start);
        for (int i : E.get(start)) {
            // if can continue search (the next node is not searched before)
            if (layer[i] == 0) {
                Tarjan(E,i);
                minAncestor[start] = Math.min(minAncestor[i], minAncestor[start]);
            }
            // if the node have beed searched before (the next node is in the stack)
            else if (inStack[i]) {
                minAncestor[start] = Math.min(minAncestor[start], layer[i]);
            }
        }
            // if the node is a storongly connected
            if (layer[start] == minAncestor[start]) {
                while (order.peek() != start) {
                    int temp = order.pop();
                    inStack[temp] = false;
                    group[temp] = groupCount;
                }
                order.pop();
                inStack[start] = false;
                group[start] = groupCount;
                groupCount++;
        }
    }

    private int V;
    private AdjacencyList E;
    private int[] indegree;

    public H01298() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = new AdjacencyList(V);
        indegree = new int[V];
        // problem A
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken()) - 1;
            while (temp != -1) {
                E.add(i, temp);
                indegree[temp]++;
                temp = Integer.parseInt(st.nextToken()) - 1;
            }
        }
        int indegreeZeroCount = 0;
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                indegreeZeroCount++;
            }
        }
        if (indegreeZeroCount == 0) {
            System.out.println(1);
        } else {
            System.out.println(indegreeZeroCount);
        }
        // problem B
        order = new Stack<Integer>();
         layer = new int[V];
         group = new int[V];
         minAncestor=new int[V];
        inStack=new boolean[V];
        groupCount = 0;
        currentLayer=0;
        for (int i = 0; i < V; i++) {
            if(minAncestor[i]==0){
                Tarjan(E,i);
            }
        }
        int maxCount = 0;
        for (int i : group) {
            maxCount = Math.max(i, maxCount);
        }
        maxCount++;
        int[] indegree = new int[maxCount];
        int[] outdegree = new int[maxCount];
        printarr(group);
        for (int i = 0; i < V; i++) {
            LinkedList<Integer> temp = E.get(i);
            for (int j : temp) {
                if(group[i]==group[j]){
continue;
                }
                outdegree[group[i]]++;
                indegree[group[j]]++;
            }
        }
        indegreeZeroCount = 0;
        int outdegreeZeroCount = 0;
        for (int i : indegree) {
            if (i == 0) {
                indegreeZeroCount++;
            }
        }
        for (int i : outdegree) {
            if (i == 0) {
                outdegreeZeroCount++;
            }
        }
        System.out.println(Math.max(indegreeZeroCount, outdegreeZeroCount));
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
