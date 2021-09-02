package C41;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
public class H01292 {
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
public int[] SPFA(int inital, ForwardStar fs, int V) {
    int[] dis = new int[V];
    LinkedList<Integer> queue = new LinkedList<Integer>();
    boolean[] ischecked = new boolean[V];
    queue.add(inital);
    ischecked[inital] = true;
    for (int j = 0; j < V; j++) {
        dis[j] = Integer.MIN_VALUE;
    }
    dis[inital] = 0;
    while (!queue.isEmpty()) {
        int checking = queue.pollFirst();
        // ischecked[checking] = false;
        LinkedList<int[]> tocheck = fs.get(checking);
        // System.out.println("checking: " + checking);
        // System.out.println(dis);
        for (int[] i : tocheck) {
            if ( dis[i[1]] <= dis[checking]+ i[2]) {
                dis[i[1]] =dis[checking] + i[2];
                if (!ischecked[i[1]]) {
                    ischecked[i[1]] = true;
                    queue.addLast(i[1]);
                }
            }
        }
    }
    return dis;
}

private int n;
    public H01292()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
      
        ForwardStar fs=new ForwardStar(50000, 100000);
        int max=0;
        int min=50000;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int a,b,c;
            a=Integer.parseInt(st.nextToken())-1;
            b=Integer.parseInt(st.nextToken());
            c=Integer.parseInt(st.nextToken());
            if(a<min){
                min=a;
            }
            if(b>max){
max=b;
            }
            fs.add(a, b, c);
        }
        for(int i=min;i<max;i++){
            fs.add(i, i+1, 0);
            fs.add(i+1, i, -1);
        }
        int[] dis=SPFA(min, fs, max+1);
printarr(dis);
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
        H01292 a=new H01292();
    }
}
