package C39;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class H01289 {
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
        public ForwardStar clone(){
            ForwardStar fs=new ForwardStar(V, E);
            fs.count=this.count;
            fs.head=this.head.clone();
            fs.stars=this.stars.clone();
            return fs;
        }
        public ForwardStar binary(int k){
            ForwardStar fs=this.clone();
            for(edge i :stars){
                if(i.weight>k){
                    i.weight=1;
                }else{
                    i.weight=0;
                }
            }
            return fs;
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
    public int[] DequeBFS(int inital, ForwardStar fs, int V) {
        int[] dis = new int[V];
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
            // ischecked[checking] = false;
            LinkedList<int[]> tocheck = fs.get(checking);
            // System.out.println("checking: " + checking);
            // System.out.println(dis);
            for (int[] i : tocheck) {
                if (dis[i[1]] > dis[checking] + i[2] || dis[i[1]] == Integer.MAX_VALUE) {
                    dis[i[1]] = dis[checking] + i[2];

                    if (i[2] == 0) {
                        queue.addFirst(i[1]);
                    } else {
                        queue.addLast(i[1]);

                    }
                }
            }
        }
        return dis;
    }
    private int V,E,K;
    private ForwardStar fs;
    public H01289() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fs=new ForwardStar(V,E*2);
int maxValue=0;
//record value
    for(int i=0;i<E;i++){
        st=new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken())-1;
        int to= Integer.parseInt(st.nextToken())-1;
        int weight = Integer.parseInt(st.nextToken());
        if(weight>maxValue){
maxValue=weight;
        }
        fs.add(from, to, weight);
        fs.add(to, from, weight);
    }
    //split
    int i=maxValue;
    int j=0;
    while (i!=j){
        int mid=Math.round((i+j)/2);
        ForwardStar temp=fs.binary(mid);
        if(DequeBFS(1, temp, V)[V-1]<K){
            i=mid;
        }else{
            j=mid;
        }
    }
    System.out.println(j);
    }
    
    public static void main(String[] args)throws IOException {
        H01289 a=new H01289();
    }
}
