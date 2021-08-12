package C38;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

import jdk.internal.org.jline.utils.InputStreamReader;

public class H01320 {
    public class ForwardStar {
        public class edge {
            private int to, weight, nexthead;
        }

        private int[] head;
        private edge[] stars;
        private int count;

        public ForwardStar(int size) {
            head = new int[size];
            for (int i = 0; i < size; i++) {
                head[i] = -1;
            }
            stars = new edge[size];
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
        public String toString(int greatest){
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<greatest;i++){
                LinkedList<int[]> temp=get(i);
                for(int[] j:temp){
                    sb.append("["+j[0]+","+j[1]+""+j[2]+"]"+"\n");
                }
            }
            return sb.toString();
        }

    }
    public int[] SPFA(int inital,ForwardStar fs,int V){
        int[] dis=new int[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] ischecked = new boolean[V];
        queue.add(inital);
        ischecked[inital] = true;
        for (int j = 0; j < V; j++) {
            dis[j] = Integer.MAX_VALUE;
        }
        dis[inital] = 0;
        while (!queue.isEmpty()) {
            int checking = queue.pollFirst();
            ischecked[checking] = false;
            LinkedList<int[]> tocheck = fs.get(checking);
            System.out.println("checking: "+checking);
            for (int[] i : tocheck) {
                if (dis[checking] > dis[i[1]]+dis[i[2]]) {
                    dis[checking]= dis[i[1]]+dis[i[2]];
                    if(!ischecked[i[1]]){
                    ischecked[i[1]] = true;
                    queue.addLast(i[1]);
                    }
                }
            }
        }
        return dis;
    }
    private int trial;
    private boolean[][] graph;
    
    public H01320(){
        BufferedReader br=new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        trial=Integer.parseInt(st.nextToken());
        for(){

        }
    }
    public static void main(String[] args) {
        H01320 a=new H01320();
    }
}
