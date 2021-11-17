package C46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


public class L1192 {
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
      private int currentLayer=0;
      private List<List<Integer>>bridges=new LinkedList<List<Integer>>();
      //remember to initalize these variables below with proper size when use Tarjan algorithm!
      private int[] layer;
      private int[] minAncestor;
      private boolean[] inStack;
  
      public void Tarjan(AdjacencyList E ,  int start ,int parent) {
          layer[start] = currentLayer;
          minAncestor[start] = currentLayer;
          currentLayer++;
          inStack[start] = true;
          //The dfs part of the algorithm
          for (int i : E.get(start)) {
              // if can continue search (the next node is not searched before)
              if(i==parent){
continue;
              }
              if (layer[i] == 0) {
                  // search the node and update the minAncestor of the currentnode
                  Tarjan(E,i,start);
                  minAncestor[start] = Math.min(minAncestor[i], minAncestor[start]);
                  // if the child node cannot return to the parent node
                  if(minAncestor[i] > layer[start]){
                      // add the edge and bridge
                      LinkedList<Integer> temp=new LinkedList<Integer>();
                      temp.add(start);
                      temp.add(i);
                      bridges.add(temp);
                  }
              }
              // if the node have been searched before (the next node is in the stack)
             else if (inStack[i]) {
                 //update the miniAncestor of childnode and continue searching
                  minAncestor[start] = Math.min(minAncestor[start], layer[i]);
              }
             
          }
          inStack[start] = false;
      }
      public AdjacencyList convertListToAdjacencyList(int n,List<List<Integer>> connections){
          AdjacencyList aList=new AdjacencyList(n);
          for(List<Integer>i :connections){
            aList.add(i.get(0), i.get(1));
            aList.add(i.get(1),i.get(0));
          }
return aList;
      }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        layer=new int[n];
       minAncestor=new int[n];
        inStack=new boolean[n];
        for(int i=0;i<n;i++){
            if (layer[i] == 0) {
        Tarjan(convertListToAdjacencyList(n,connections), i,-1);
            }
        }
        return bridges;
    }
    public void printList(List<List<Integer>> connections){
        StringBuilder sb=new StringBuilder();
        for(List<Integer>i:connections){
            for(int j:i){
                sb.append(j);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public void printList(int[] connections){
        StringBuilder sb=new StringBuilder();
        for(int i:connections){
                sb.append(i);
                sb.append(" ");
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        List<List<Integer>> connections=new LinkedList<List<Integer>>();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            LinkedList<Integer> a=new LinkedList<Integer>();
            a.add(Integer.parseInt(st.nextToken()));
            a.add(Integer.parseInt(st.nextToken()));
            connections.add(a);
        }
        L1192 b=new L1192();
        b.printList(b.criticalConnections(n,connections));
        b.printList(b.bridges);
        b.printList(b.layer);
        b.printList(b.minAncestor);
    }
}
