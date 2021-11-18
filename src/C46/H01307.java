package C46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
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
// This method is prove to be stable.
    // remember to initalize these variables below with proper size when use Tarjan
    // algorithm!
    private int currentLayer;
    private List<List<Integer>> bridges;
    private int[] layer;
    private int[] minAncestor;

    public void Tarjan(AdjacencyList E, int start, int parent) {
        layer[start] = currentLayer;
        minAncestor[start] = currentLayer;
        currentLayer++;
        // The dfs part of the algorithm
        for (int i : E.get(start)) {
            // critical step
            if (i == parent) {
                continue;
            }
            // if can continue search (the next node is not searched before)
            if (layer[i] == 0) {
                // search the node and update the minAncestor of the currentnode
                Tarjan(E, i, start);
                minAncestor[start] = Math.min(minAncestor[i], minAncestor[start]);
                // if the child node cannot return to the parent node
                if (minAncestor[i] > layer[start]) {
                    // add the edge and bridge
                    LinkedList<Integer> temp = new LinkedList<Integer>();
                    temp.add(start);
                    temp.add(i);
                    bridges.add(temp);
                }
            }
            // if the node have been searched before (the next node is in the stack)
            else{
                // update the miniAncestor of childnode and continue searching
                minAncestor[start] = Math.min(minAncestor[start], layer[i]);
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
        E = Integer.parseInt(st.nextToken());
        map = new AdjacencyList(N);
        // loop of trials
        while (N != 0 && E != 0) {
            currentLayer = 0;
            bridges = new LinkedList<List<Integer>>();
            for (int i = 0; i < E; i++) {
                // loop of start points
                st = new StringTokenizer(br.readLine());
                int startpoint = Integer.parseInt(st.nextToken()) - 1;
                int endpoint = Integer.parseInt(st.nextToken()) - 1;
                map.add(startpoint, endpoint);
                map.add(endpoint, startpoint);
            }
            layer = new int[N];
            minAncestor = new int[N];
            for (int i = 0; i < N; i++) {
                if (layer[i] == 0) {
                    Tarjan(map, i, -1);
                }
            }
            // printarr(layer);
            // printarr(minAncestor);
            // System.out.println(bridges.size());
            System.out.println(bridges.size());
            st = new StringTokenizer(br.readLine());
            if (!st.hasMoreTokens()) {
                continue;
            }
            N = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            map = new AdjacencyList(N);
        }
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
