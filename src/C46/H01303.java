package C46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class H01303 {
    // Double connected components
    // 1, find all bridges, then delete them.
    // 2, use union find or DFS to find all the components, then use bridge to build
    // new graph.
    // 3, required new bridge = (leaf size+1)/2.

    /* full version of disjoint set */
public class DisjointSet{
    int[] parentNode;
    public DisjointSet(int size){
       parentNode=new int[size];
       for(int i=0;i<size;i++){
          parentNode[i]=i;
       }
    }
    public int findParent(int i){
       if(parentNode[i]!=i){
          parentNode[i]=findParent(parentNode[i]);
       }
       return parentNode[i];
    }
    public void union(int a, int b){
       parentNode[findParent(a)]=findParent(b);
    }
    public boolean isConnected(int a, int b){
       return findParent(a)==findParent(b);
    } 
 }
    public class ForwardStar {
        public class edge {
            private int to, nexthead, weight, id;
        }

        private int[] head;
        private boolean[] inactive;
        private edge[] stars;
        private boolean directed;
        private int edgeCount;
        private int verticeCount;
        private int inActiveCount;
        private int count;

        public ForwardStar(int V, int E, boolean isDirected) {
            verticeCount=V;
            edgeCount=E;
            directed = isDirected;
            if (isDirected) {
                inactive = new boolean[E];
            } else {
                inactive = new boolean[E * 2];
            }
            head = new int[V];
            for (int i = 0; i < V; i++) {
                head[i] = -1;
            }
            stars = new edge[E];
            inActiveCount=0;
            count = 0;
        }

        public void add(int from, int to, int weight) {
            edge tempEdge = new edge();
            tempEdge.to = to;
            tempEdge.weight = weight;
            tempEdge.id = count;
            tempEdge.nexthead = head[from];
            head[from] = count;
            stars[count] = tempEdge;
            count++;
            if (!directed) {
                edge etempEdge = new edge();
                etempEdge.to = to;
                etempEdge.weight = weight;
                etempEdge.id = count;
                etempEdge.nexthead = head[from];
                head[from] = count;
                stars[count] = tempEdge;
                count++;
            }
        }

        public void add(int from, int to) {
            edge tempEdge = new edge();
            tempEdge.to = to;
            tempEdge.weight = 0;
            tempEdge.id = count;
            tempEdge.nexthead = head[from];
            head[from] = count;
            stars[count] = tempEdge;
            count++;
            if (!directed) {
                edge etempEdge = new edge();
                etempEdge.to = to;
                etempEdge.weight = 0;
                etempEdge.id = count;
                etempEdge.nexthead = head[from];
                head[from] = count;
                stars[count] = tempEdge;
                count++;
            }
        }

        public void deactive(int id) {
            inactive[id] = true;
            if (!directed) {
                inactive[id ^ 1] = true;
                inActiveCount++;
            }
            inActiveCount++;
        }

        public void active(int id) {
            inactive[id] = false;
            if (!directed) {
                inactive[id ^ 1] = false;
                inActiveCount--;
            }
            inActiveCount--;
        }

        public LinkedList<int[]> get(int from) {
            LinkedList<int[]> result = new LinkedList<int[]>();
            for (int i = head[from]; i != -1; i = stars[i].nexthead) {
                int[] temp = new int[4];
                temp[0] = from;
                temp[1] = stars[i].to;
                temp[2] = stars[i].weight;
                temp[3] = stars[i].id;
                result.add(temp);
            }
            return result;
        }

        public ForwardStar removeInactive(){
            ForwardStar result=new ForwardStar(V, E-inActiveCount, directed);
            for(int i=0;i<V;i++){
                LinkedList<int[]>tempEdges=this.get(i);
                for(int[] j:tempEdges){
                    result.add(j[0], j[1], j[2]);
                }
            }
            return result;
        }

        public LinkedList<int[]> getActive(int from) {
            LinkedList<int[]> result = new LinkedList<int[]>();
            for (int i = head[from]; i != -1; i = stars[i].nexthead) {
                if(inactive[stars[i].id]){
                continue;
                }
                int[] temp = new int[4];
                temp[0] = from;
                temp[1] = stars[i].to;
                temp[2] = stars[i].weight;
                temp[3] = stars[i].id;
                result.add(temp);
            }
            return result;
        }
        public String toString(int greatest) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < greatest; i++) {
                LinkedList<int[]> temp = get(i);
                for (int[] j : temp) {
                    sb.append("[" + j[0] + "," + j[1] + "]" + "\n");
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
    private LinkedList<int[]> bridges;
    private int[] layer;
    private int[] minAncestor;

    public void Tarjan(ForwardStar E, int start, int parent) {
        layer[start] = currentLayer;
        minAncestor[start] = currentLayer;
        currentLayer++;
        // The dfs part of the algorithm
        for (int[] i : E.get(start)) {
            // critical step
           if(i[1]==parent){
            continue;
           }
            // if can continue search (the next node is not searched before)
            if (layer[i[1]] == 0) {
                // search the node and update the minAncestor of the currentnode
                Tarjan(E, i[1], start);
                minAncestor[start] = Math.min(minAncestor[i[1]], minAncestor[start]);
                // if the child node cannot return to the parent node
                if (minAncestor[i[1]] > layer[start]) {
                    // add the edge and bridge
                    bridges.add(i);
                    //extra step
                    E.deactive(i[3]);
                }
            }
            // if the node have been searched before (the next node is in the stack)
            else {
                // update the miniAncestor of childnode and continue searching
                minAncestor[start] = Math.min(minAncestor[start], layer[i[1]]);
            }
        }
    }

    private int E;
    private int V;

    public H01303() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        ForwardStar graph = new ForwardStar(V, E, false);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            graph.add(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }
        currentLayer = 0;
        bridges = new LinkedList<int[]>();
        layer = new int[V];
        minAncestor = new int[V];
        for (int i = 0; i < V; i++) {
            if (layer[i] == 0) {
                Tarjan(graph, i, -1);
            }
        }
        DisjointSet set=new DisjointSet(V);
        for (int i = 0; i < V; i++) {
            LinkedList<int[]> temp = graph.getActive(i);
            for (int[] j : temp) {
               set.union(j[1], j[0]);
            }
        }
        
    }

    public static void main(String[] args) throws IOException {
        H01303 a = new H01303();
    }
}
