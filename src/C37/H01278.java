package C37;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class H01278 {
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

    }

    private int[] forward;
    private int[] backward;
    private int[] price;
    private ForwardStar fs;
    private ForwardStar bs;
    private int V;
    private int E;

    public H01278() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        price=new int[V];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<V;i++){
            price[i]=Integer.parseInt(st.nextToken());
        }
        int[][] edgeTemp=new int[E][3];
        int size=0;
        for(int i=0;i<E;i++){
            int from=Integer.parseInt(st.nextToken())-1;
            int to=Integer.parseInt(st.nextToken())-1;
            int isdouble=Integer.parseInt(st.nextToken());
            size+=isdouble;
        }
        fs=new ForwardStar(size);
        for(int i[]:edgeTemp){
            if(i[2]==2){
                fs.add(i[0], i[1], i[2]);
                fs.add(i[1], i[0], i[2]);
                bs.add(i[0], i[1], i[2]);
                bs.add(i[1], i[0], i[2]);
            }else{
                fs.add(i[0], i[1], i[2]);
                bs.add(i[1], i[0], i[2]);
            }
        }
        //BFS
        LinkedList<Integer>queue=new LinkedList<Integer>();
        boolean[] ischecked=new boolean[V];
        queue.add(0);
        ischecked[0]=true;
        forward[0]=price[0];
        for(int i=0;i<V;i++){
            forward[i]=1000;
        }
        while(!queue.isEmpty()){
            int checking=queue.pollFirst();
            ischecked[checking]=false;
            LinkedList<int[]> tocheck=fs.get(checking);
            for(int[] i:tocheck){
                forward[]
            }
        }
    }

    public static void main(String[] args) throws IOException{
        H01278 a = new H01278();
    }
}
