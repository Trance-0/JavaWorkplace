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

    private int[] forward;
    private int[] backward;
    private int[] price;
    private ForwardStar fs;
    private ForwardStar bs;
    private int V;
    private int E;

    public H01278() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        price = new int[V];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
        int[][] edgeTemp = new int[E][3];
        int size = 0;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edgeTemp[i][0] = Integer.parseInt(st.nextToken()) - 1;
            edgeTemp[i][1] = Integer.parseInt(st.nextToken()) - 1;
            int isdouble = Integer.parseInt(st.nextToken());
            edgeTemp[i][2] = isdouble;
            size += isdouble;
        }
        fs = new ForwardStar(size);
        bs = new ForwardStar(size);
        for (int i[] : edgeTemp) {
            if (i[2] == 2) {
                fs.add(i[0], i[1], i[2]);
                fs.add(i[1], i[0], i[2]);
                bs.add(i[0], i[1], i[2]);
                bs.add(i[1], i[0], i[2]);
            } else {
                fs.add(i[0], i[1], i[2]);
                bs.add(i[1], i[0], i[2]);
            }
        }
        // BFS forward
        System.out.println(fs.toString(V));
        
        System.out.println(bs.toString(V));
        forward = price.clone();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] ischecked = new boolean[V];
        queue.add(0);
        ischecked[0] = true;
        forward[0] = price[0];
        for (int i = 1; i < V; i++) {
            forward[i] = 1000;
        }
        while (!queue.isEmpty()) {
            int checking = queue.pollFirst();
            ischecked[checking] = false;
            LinkedList<int[]> tocheck = fs.get(checking);
            System.out.println("checking: "+checking);
            for (int[] i : tocheck) {
                if (forward[checking] < forward[i[1]] || forward[i[1]] > price[i[1]]) {
                    forward[i[1]] = Math.min(forward[checking],price[i[1]]);
                    if(!ischecked[i[1]]){
                    ischecked[i[1]] = true;
                    queue.addLast(i[1]);
                    }
                }
            }
        }
        backward = price.clone();
        queue = new LinkedList<Integer>();
        ischecked = new boolean[V];
        queue.add(V-1);
        ischecked[V-1] = true;
        backward[V-1] = price[V-1];
        while (!queue.isEmpty()) {
            int checking = queue.pollFirst();
            ischecked[checking] = false;
            LinkedList<int[]> tocheck = bs.get(checking);
            System.out.println("checking: "+checking);
            for (int[] i : tocheck) {
                if (backward[checking] > backward[i[1]] || backward[i[1]] < price[i[1]]) {
                    backward[i[1]] =Math.max(backward[checking],price[i[1]]);
                    if(!ischecked[i[1]]){
                    ischecked[i[1]] = true;
                    queue.addLast(i[1]);
                    }
                }
            }
        }
        int maxprice = 0;
        for (int i = 0; i < V; i++) {
            maxprice = Math.max(maxprice, backward[i] - forward[i]);
        }
        System.out.println(maxprice);
    }
    
    public void printarr(int[][] arr){
        StringBuilder sb=new StringBuilder();
        for (int i[]:arr){
            sb.append(i[0]+""+i[1]+""+i[2]);
            sb.append(",");
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) throws IOException {
        H01278 a = new H01278();
    }
}
