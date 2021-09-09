package C41;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
public class H01295 {
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
    int[] checktime=new int[V];
    boolean[] ischecked = new boolean[V];
    queue.add(inital);
    ischecked[inital] = true;
    for (int j = 0; j < V; j++) {
        dis[j] = Integer.MAX_VALUE;
    }
    dis[inital] = 0;
    while (!queue.isEmpty()) {
        //printarr(dis);
        int checking = queue.pollFirst();
        checktime[checking]+=1;
        if(checktime[checking]>V){
            for(int i=0;i<V;i++){
                dis[i]=-1;
            }
            return dis;
        }
        ischecked[checking] = false;
        LinkedList<int[]> tocheck = fs.get(checking);
        // System.out.println("checking: " + checking);
        // System.out.println(dis);
        for (int[] i : tocheck) {
            if ( dis[i[1]] > dis[checking]+ i[2]) {
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
private int ml;
private int md;
    public H01295()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        ml=Integer.parseInt(st.nextToken());
        md=Integer.parseInt(st.nextToken());
      
        ForwardStar fs=new ForwardStar(n, ml+md+2*n);

        for(int i=0;i<ml;i++){
            st=new StringTokenizer(br.readLine());
            int a,b,c;
            a=Integer.parseInt(st.nextToken())-1;
            b=Integer.parseInt(st.nextToken())-1;
            c=Integer.parseInt(st.nextToken());
            fs.add(a,b, c);
        }
        for(int i=0;i<md;i++){
            st=new StringTokenizer(br.readLine());
            int a,b,c;
            a=Integer.parseInt(st.nextToken())-1;
            b=Integer.parseInt(st.nextToken())-1;
            c=Integer.parseInt(st.nextToken());
            fs.add(b,a,-c);
        }
        // for(int i=0;i<n;i++){
        //     fs.add(i, i+1, 0);
        //     fs.add(i+1, i, -1);
        // }
        int[] dis=SPFA(0, fs, n);
        if(dis[n-1]==Integer.MAX_VALUE){
            System.out.println(-2);
        }else{
            System.out.println(dis[n-1]);
        }
    }


    public static void main(String[] args) throws IOException {
        H01295 a=new H01295();
    }
}
