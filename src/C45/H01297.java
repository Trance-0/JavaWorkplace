package C45;

import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H01297 {
    public class ForwardStar {
        public class edge {
            private int to, nexthead;
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

        public void add(int from, int to) {
            edge tempEdge = new edge();
            tempEdge.to = to;
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
                result.add(temp);
            }
            return result;
        }

        public String toString(int greatest) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < greatest; i++) {
                LinkedList<int[]> temp = get(i);
                for (int[] j : temp) {
                    sb.append("[" + j[0] + "," + j[1] + "]" + "\n");
                }
            }
            return sb.toString();
        }

    }

    public void KosarajuModified(ForwardStar g, ForwardStar gt, int Vsize) {
        Stack<Integer> order = new Stack<Integer>();
        group = new int[Vsize];
        count = 0;
        boolean[] checked = new boolean[Vsize];
        for (int i = 0; i < Vsize; i++) {
            if (!checked[i]) {
                KosarajuDFSa(g, i, checked, order);
            }
        }
        checked = new boolean[Vsize];
        for (int i = 0; i < Vsize; i++) {
            int temp = order.pop();
            if (!checked[temp]) {
                KosarajuDFSb(gt, temp, checked, count, group);
                count++;
            }
        }
    }

    private void KosarajuDFSa(ForwardStar E, int start, boolean[] checked, Stack<Integer> order) {
        checked[start] = true;
        LinkedList<int[]> tempEdge = E.get(start);
        for (int[] i : tempEdge) {
            if (!checked[i[1]]) {
                KosarajuDFSa(E, i[1], checked, order);
            }
        }
        order.push(start);
    }

    private void KosarajuDFSb(ForwardStar E, int start, boolean[] checked, int count, int[] group) {
        checked[start] = true;
        group[start] = count;
        LinkedList<int[]> tempEdge = E.get(start);
        for (int[] i : tempEdge) {
            if (!checked[i[1]]) {
                KosarajuDFSb(E, i[1], checked, count, group);
            }
        }
    }

    public LinkedList<Integer> topologicalSortBFS(ForwardStar E, int[] inDegree, int Vsize) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < Vsize; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int toSearch = queue.pollFirst();
            result.addLast(toSearch);
            LinkedList<int[]> tempedge = E.get(toSearch);
            for (int[] i : tempedge) {
                inDegree[i[1]] -= 1;
                if (inDegree[i[1]] == 0) {
                    queue.addLast(i[1]);
                }
            }
        }
        if (result.size() == Vsize) {
            return result;
        }
        return new LinkedList<Integer>();
    }

    private ForwardStar G;
    private ForwardStar Gt;
    private ForwardStar Ga;
    private int[][] rawE;
    private int V;
    private int E;
    private int X;
    private int[] group;
    private int count;

    public H01297() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        G = new ForwardStar(V, E);
        Gt = new ForwardStar(V, E);
        Ga = new ForwardStar(V, E);
        rawE = new int[E][2];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            rawE[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rawE[i][1] = Integer.parseInt(st.nextToken()) - 1;
            G.add(rawE[i][0], rawE[i][1]);
            Gt.add(rawE[i][1], rawE[i][0]);
        }
        // find out all storng connected componet and view them as a signle node.
        // the final goal is (i) to find the chain with highest weight.(ii) to find the
        // number of chains with highest weight.
        KosarajuModified(G, Gt, V);
        int[] inDegree = new int[count];
        int[] chainSize = ColorCount(group, count);
        for (int[] i : rawE) {
            // to eliminate mono color connection
            if (group[i[0]] == group[i[1]]) {
                continue;
            }
            // Topological sort BFS need indegree to process.
            inDegree[group[i[1]]]++;
            Ga.add(group[i[0]], group[i[1]]);
            // the node in the graph represent the color of the node.
        }
        // printarr(group);
        LinkedList<Integer> order = topologicalSortBFS(Ga, inDegree, count);
        // use Dynamic processing by order made in topological sort.
        int[] accumulateChainSize = chainSize.clone();
        // longest branch update count represent the numder of update when proceessing
        // the array in order. Don't change if you don't know the funciton of it.
        int[] longestChainUpdateCount = new int[count];
        long largestChainSizeCount = 0;
        for (int i = 0; i < count; i++) {
            longestChainUpdateCount[i] = 1;
        }
        int longestChainSize = 0;
        for (int i : order) {
            // System.out.println(i);
            // traverse all the node where node i can go.
            LinkedList<int[]> tempEdge = Ga.get(i);
            for (int[] j : tempEdge) {
                // j is the edge, cosist of group of the node
                int from = i;
                int to = j[1];
                if (accumulateChainSize[to] < accumulateChainSize[from] + chainSize[to]) {
                    accumulateChainSize[to] = accumulateChainSize[from] + chainSize[to];
                    // System.out.println(String.format( "Updating "+to+"" +from));
                    // System.out.println("recount"+to);
                    longestChainUpdateCount[to] = 0;
                    longestChainUpdateCount[to] = longestChainUpdateCount[to] + longestChainUpdateCount[from];
                    // find largeest value
                } else if (accumulateChainSize[to] == accumulateChainSize[from] + chainSize[to]) {
                    // System.out.println(String.format( "Updating "+to+"" +from));
                    longestChainUpdateCount[to] = longestChainUpdateCount[to] + longestChainUpdateCount[from];
                    // find largeest value
               
                }
            }
            if (accumulateChainSize[i] >= longestChainSize) {
                longestChainSize = accumulateChainSize[i];
            }
        }
        for (int i = 0; i < count; i++) {
            if (accumulateChainSize[i] == longestChainSize) {
                largestChainSizeCount = (longestChainUpdateCount[i] + largestChainSizeCount) % X;
            }
        }
        printarr(chainSize);
         printarr(accumulateChainSize);
         printarr(longestChainUpdateCount);
        System.out.println(longestChainSize);
        System.out.println(largestChainSizeCount);
    }

    // to count the weight of each color.
    private int[] ColorCount(int[] g, int c) {
        int[] result = new int[c];
        for (int i : g) {
            result[i]++;
        }
        return result;
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
        H01297 a = new H01297();
    }
}
