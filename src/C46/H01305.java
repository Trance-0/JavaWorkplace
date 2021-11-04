package C46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class H01305 {
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

    // Tarjan to get split points
    private Stack<Integer> order = new Stack<Integer>();
    private int currentLayer = 0;
    private LinkedList<Integer> splitPoints = new LinkedList<Integer>();
    // remember to initalize these variables below with proper size when use Tarjan
    // algorithm!
    private int[] layer;
    private int[] minAncestor;
    private boolean[] inStack;
    private int root;

    // Tarjan is used for directed graph
    // (i) DFS the adjacenvylist recursively, update layer and mininalancestor.
    // (ii) if is root node, if have more than 1 child, then it is split point.
    // if is child node, if all the child's minAncestor is greater or equal than
    // it's own layer, then it is split point.

    public void Tarjan(AdjacencyList E, int start) {
        if (start == root) {
            order = new Stack<Integer>();
            currentLayer = 0;
            splitPoints = new LinkedList<Integer>();
        }
        layer[start] = currentLayer;
        minAncestor[start] = currentLayer;
        currentLayer++;
        inStack[start] = true;
        order.add(start);
        boolean canChildReturn = false;
        //only check recursive child number
        int childNumber=0;
        for (int i : E.get(start)) {
            // if can continue search (the next node is not searched before)
            if (layer[i] == 0) {
                childNumber++;
                Tarjan(E, i);
                if (minAncestor[i] >= layer[start]) {
                    canChildReturn = true;
                }
                minAncestor[start] = Math.min(minAncestor[i], minAncestor[start]);
            }
            // if the node have beed searched before (the next node is in the stack)
            else if (inStack[i]) {
                minAncestor[start] = Math.min(minAncestor[start], layer[i]);
            }
        }
        if ((start == root && childNumber > 1) || !canChildReturn) {
            splitPoints.add(start);
        }
    }

    private int N;
    private AdjacencyList map;

    public H01305() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        // loop of trials
        while (N != 0) {
            map = new AdjacencyList(N);

            st = new StringTokenizer(br.readLine());
            int startpoint = Integer.parseInt(st.nextToken()) - 1;
            // loop of start points
            while (startpoint != -1) {
                // loop of connceted places
                while (st.hasMoreTokens()) {
                    int endpoint = Integer.parseInt(st.nextToken()) - 1;
                    map.add(startpoint, endpoint);
                    map.add(endpoint, startpoint);
                }
                st = new StringTokenizer(br.readLine());
                startpoint = Integer.parseInt(st.nextToken()) - 1;
            }
            layer = new int[N];
            minAncestor = new int[N];
            inStack = new boolean[N];
            root = 0;
            Tarjan(map, root);
            System.out.println(splitPoints);
            // update N
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        H01305 a = new H01305();
    }
}
