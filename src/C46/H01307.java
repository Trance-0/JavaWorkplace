package C46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class H01307 {
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

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < edges.length; i++) {
                LinkedList<Integer> temp = get(i);
                for (int j : temp) {
                    sb.append("[" + i + "," + j + "]" + "\n");
                }
            }
            return sb.toString();
        }

    }

    // Tarjan to get bridge
    private Stack<Integer> order=new Stack<Integer>();
    private int currentLayer=0;
    private LinkedList<int[]>bridges=new LinkedList<int[]>();
    //remember to initalize these variables below with proper size when use Tarjan algorithm!
    private int[] layer;
    private int[] minAncestor;
    private boolean[] inStack;

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
            if(minAncestor[i] > layer[start]){
                  bridges.add(new int[]{start,i});
            }
        }
    }


    private int N;
    private int E;
    private AdjacencyList map;

    public H01307() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E= Integer.parseInt(st.nextToken());
        map = new AdjacencyList(N);
       
        // loop of trials
        for (int i=0;i<E;i++) {
            // loop of start points
            st = new StringTokenizer(br.readLine());
            int startpoint = Integer.parseInt(st.nextToken()) - 1;
            int endpoint = Integer.parseInt(st.nextToken()) - 1;
             map.add(startpoint, endpoint);
            map.add(endpoint, startpoint);
        }
        st = new StringTokenizer(br.readLine());
        layer = new int[N];
        minAncestor = new int[N];
        inStack = new boolean[N];
        Tarjan(map, 0);
        //printarr(layer);
        //printarr(minAncestor);
        //System.out.println(bridges.size());
        System.out.println(E-bridges.size());
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
        H01307 a = new H01307();
    }
}
