package C38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H01320 {
    public class ForwardStar {
        public class edge {
            private int to, weight, nexthead;
        }

        private int[] head;
        private edge[] stars;
        private int count;

        public ForwardStar(int V,int E) {
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
                if ( dis[i[1]] > dis[checking]+ i[2]||dis[i[1]]==Integer.MAX_VALUE) {
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

    private int trial;
    private ForwardStar fs;
    private int R;
    private int C;

    public H01320() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        trial = Integer.parseInt(st.nextToken());
        for (int k = 0; k < trial; k++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if ((R + C) % 2 != 0) {
                System.out.println("NO SOLUTION");
                continue;
            }
            fs = new ForwardStar((R + 1) * (C + 1),R * C * 4);
            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    char temp = line.charAt(j);
                    int ul = j + (C  + 1) * i;
                    int ur = j + (C+ 1) * i + 1;
                    int dl = j+ (C + 1) * (i+ 1);
                    int dr = j+ (C + 1) * (i + 1) + 1;
                    // System.out.println(ul+","+ur+","+dl+","+dr);
                    if (temp == '/') {
                        fs.add(ul, dr, 1);
                        fs.add(dl, ur, 0);
                        fs.add(dr, ul, 1);
                        fs.add(ur, dl, 0);
                    } else {
                        fs.add(ul, dr, 0);
                        fs.add(dl, ur, 1);   
                        fs.add(dr, ul, 0);
                        fs.add(ur, dl, 1);
                    }
                    // System.out.println(fs.toString((R + 1) * (C + 1)-1));
                }
            }
            int[] result = SPFA(0, fs, (R + 1) * (C + 1));
            System.out.println(result[(R + 1) * (C + 1) - 1]);
        }

    }

    public static void main(String[] args) throws IOException {
        H01320 a = new H01320();
    }
}
