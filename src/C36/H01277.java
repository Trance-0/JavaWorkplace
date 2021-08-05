package C36;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import java.io.InputStreamReader;

public class H01277 {

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

    private int n;
    private int m;
    private int[] neighbor;
    private ForwardStar fs;

    public H01277() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fs = new ForwardStar(m * 2);
        neighbor = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < 6; i++) {
            neighbor[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        neighbor[0] = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            fs.add(from, to, weight);
            fs.add(to, from, weight);
        }
        int[][] dis = new int[6][6];
        for (int i = 0; i < 6; i++) {
            // BFS
            int[] tempDis = new int[n];
            boolean[] checked = new boolean[n];
            for (int j = 0; j < n; j++) {
                tempDis[j] = Integer.MAX_VALUE;
            }
            tempDis[neighbor[i]] = 0;
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(neighbor[i]);
            checked[neighbor[i]] = true;
            while (!queue.isEmpty()) {
                int checking = queue.pollFirst();
                LinkedList<int[]> tempE = fs.get(checking);
                checked[checking]=false;
                for (int[] k : tempE) {
                    if (tempDis[k[1]] > tempDis[checking] + k[2]) {
                        tempDis[k[1]] = tempDis[checking] + k[2];
                        if(!checked[k[1]]){
                        checked[k[1]] = true;
                        queue.addLast(k[1]);
                        }
                    }
                }
            }
            //printarr(tempDis);
            for(int j=0;j<6;j++){
                dis[i][j]=tempDis[neighbor[j]];
            }
        }
        //printarr(dis);

        int result = Integer.MAX_VALUE;
        for (int i = 1; i < 6; i++) {

            for (int j = 1; j < 6; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 1; k < 6; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    for (int l = 1; l < 6; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        for (int o = 1; o < 6; o++) {
                            if (o == i || o == j || o == k || o == l) {
                                continue;
                            }
                            int tempD=dis[0][i]+ dis[i][j] + dis[j][k] + dis[k][l] + dis[l][o];
                            //System.out.println(i+""+j+""+k+""+l+""+o+":"+tempD);
                            
                            if(tempD<result){
                            result = tempD;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    public void printarr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] i : arr) {
            sb.append("[");
            for (int j : i) {
                sb.append(j);
                sb.append(",");
            }
            sb.append("]" + "\n");
        }
        System.out.println(sb.toString());
    }

    public void printarr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    public void printarr(LinkedList<int[]> arr) {
        for (int i = 0; i < arr.size(); i++) {
            printarr(arr.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        H01277 a = new H01277();
    }
}
