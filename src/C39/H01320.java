package C39;

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
    /*
     * 算法描述：
     * 由于这是一张边权要么为0、要么为1的图。在这样的图上，我们可以通过双端队列广搜来计算。算法的整体框架与一般的广搜类似，只是在每个节点沿分支拓展时稍作改变。
     * 如果这条分支边权为0，则从队首入队，否则从队尾入队。这样我们能保证，任意时刻广搜队列中节点对应的距离值都有“两端性”和“单调性”，每个节点第一次被访问时，
     * 就能得到从左上角到该节点的最短距离。
     * 
     * 效率分析： 我们可以知道该算法中每个节点仅被访问一次，因为这是BFS的特性，所以该算法的效率为O(N)。
     * 
     * 正确性证明：
     * 由于我们最终目标是求路径权值和，而权值为0的边无论走多少条权值和依旧是+0，因此我们可以优先走权值为0的边，更新与这些边相连的点x的d[x]（d[i]
     * 为从s到i最小权值和），此时d[x]一定是最小的，因为它是由尽可能多的权值为0的边更新而来。所以在队列中取出的节点同时满足“连通性”和“权值最小”，
     * 因此每个节点仅需被更新一次。 ———————————————— 版权声明：本文为CSDN博主「迷亭1213」的原创文章，遵循CC 4.0
     * BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/weixin_41162823/article/details/90449211
     */

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

    private int trial;
    private ForwardStar fs;
    private int R;
    private int C;
    private int[] ans;

    public H01320() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        trial = Integer.parseInt(st.nextToken());
        ans = new int[trial];
        for (int k = 0; k < trial; k++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if ((R + C) % 2 != 0) {
                ans[k] = -1;
                for (int i = 0; i < R; i++) {
                    br.readLine();
                }
                continue;
            }
            fs = new ForwardStar((R + 1) * (C + 1), R * C * 4);
            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    char temp = line.charAt(j);
                    int ul = j + (C + 1) * i;
                    int ur = j + (C + 1) * i + 1;
                    int dl = j + (C + 1) * (i + 1);
                    int dr = j + (C + 1) * (i + 1) + 1;
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
            int[] result = DequeBFS(0, fs, (R + 1) * (C + 1));
            ans[k] = result[(R + 1) * (C + 1) - 1];
        }
        for (int i : ans) {
            if (i == -1) {
                System.out.println("NO SOLUTION");
            } else {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        H01320 a = new H01320();
    }
}
