package C42;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class H01287 {
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

    public ForwardStar E;
    public int Esize;
    public int V;
    public int[] inDegree;

    public H01287() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        Esize = Integer.parseInt(st.nextToken());
        inDegree = new int[V];
        E = new ForwardStar(V, Esize);
        for (int i = 0; i < Esize; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            inDegree[to] += 1;
            E.add(from, to, 1);
        }
        LinkedList<Integer> order = topologicalSortBFS(E, inDegree, V);
        if (order.isEmpty()) {
            System.out.println("Poor Xed");
        } else {
            int[] reward = getLayer(order, E, V);
            int sum = 0;
            for (int i : reward) {
                sum += i;
            }
            sum += V * 100;
            System.out.println(sum);
        }
    }

    public int[] getLayer(LinkedList<Integer> order, ForwardStar E, int Vsize) {
        int[] result = new int[Vsize];
        for (int i : order) {
            LinkedList<int[]> tosearch = E.get(i);
            if (tosearch.isEmpty()) {
                continue;
            }
            for (int[] j : tosearch) {
                result[i] = Math.max(result[i], result[j[1]] + 1);
            }
        }
        return result;
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

    public static void main(String[] args) throws IOException {
        H01287 a = new H01287();
    }
}
