package C42;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class H01287 {
    public class ForwardStar {
        public class edge {
            private int to, weight, nexthead;
        }

        private int[] head;
        private edge[] stars;
        private int count;

        public ForwardStar(int V, int E) {
            head = new int[V];
            for (int i = 0; i < V; i++) {
                head[i] = -1;
            }
            stars = new edge[E];
            count = 0;
        }

        public void add(int from, int to, int weight) {
            edge tempEdge = new edge();
            tempEdge.to = to;
            tempEdge.weight = weight;
            tempEdge.nexthead = head[from];
            head[from] = count;
            stars[count] = tempEdge;
            count++;
        }

        public LinkedList<int[]> get(int from) {
            LinkedList<int[]> result = new LinkedList<int[]>();
            for (int i = head[from]; i != -1; i = stars[i].nexthead) {
                int[] temp = new int[3];
                temp[0] = from;
                temp[1] = stars[i].to;
                temp[2] = stars[i].weight;
                result.add(temp);
            }
            return result;
        }

        public String toString(int greatest) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < greatest; i++) {
                LinkedList<int[]> temp = get(i);
                for (int[] j : temp) {
                    sb.append("[" + j[0] + "," + j[1] + "," + j[2] + "]" + "\n");
                }
            }
            return sb.toString();
        }

    }
    public ForwardStar E;
    public int Esize;
    public int V;
    public int[] inDegree;
    public H01287 ()throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader( System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        V=Integer.parseInt(st.nextToken());
        Esize=Integer.parseInt(st.nextToken());
        inDegree=new int[V];
    E=new ForwardStar(V, Esize);
        for(int i=0;i<Esize;i++){
            st=new StringTokenizer(br.readLine());
            int to=Integer.parseInt(st.nextToken())-1;
            int from=Integer.parseInt(st.nextToken())-1;
            inDegree[to]+=1;

            E.add(from, to, 1);
        }
        int[] reward=new int[V];
        //find till you get the maximum
        for(int i=0;i<V;i++){
            if(reward[i]==0&&inDegree[i]==0){

            }
        }
    }
    public static void main(String[] args) throws IOException{
        H01287 a=new H01287();
    }
}
