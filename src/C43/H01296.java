package C43;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class H01296 {
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
     * https://www.cnblogs.com/shadowland/p/5876307.html
     * https://redspider110.github.io/2018/08/22/0093-algorithms-scc-kosaraju/
     */

    public LinkedList<Integer> topologicalSortDFS(ForwardStar E, int start, int Vsize) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(start);
        boolean[] checked = new boolean[Vsize];
        checked[start] = true;
        while (!stack.isEmpty()) {
            LinkedList<int[]> tempEdge = E.get(stack.peek());
            boolean addnew = false;
            for (int[] i : tempEdge) {
                if (!checked[i[1]]) {
                    stack.add(i[1]);
                    checked[i[1]] = true;
                    addnew = true;
                    break;
                }
                if (!addnew) {
                    result.addFirst(stack.pop());
                }
            }
        }
        return result;
    }

    public int[] Kosaraju(ForwardStar g, ForwardStar gt, int Vsize) {
        Stack<Integer> order = new Stack<Integer>();
        int[] group = new int[Vsize];
        int count = 0;
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
        return group;
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

    private int V;
    private ForwardStar g;
    private ForwardStar gt;
    private int[] group;
    private int count;
    private int Esize;

    public H01296() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        Esize = Integer.parseInt(st.nextToken());
        g = new ForwardStar(V, Esize);
        gt = new ForwardStar(V, Esize);
        for (int i = 0; i < Esize; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            g.add(a, b, 0);
            gt.add(b, a, 0);
        }
        KosarajuModified(g, gt, V);
        boolean[] outdegree = new boolean[count];
        for (int i = 0; i < V; i++) {
            LinkedList<int[]> temp = g.get(i);
            for (int[] j : temp) {
                int a = j[0];
                int b = j[1];
                if (!outdegree[group[a]] && group[a] != group[b]) {
                    outdegree[group[a]] = true;
                }
            }
        }
        boolean failed = false;
        int toSearch = -1;
        for (int i = 0; i < count; i++) {
            if (!outdegree[i]) {
                if (toSearch != -1) {
                    failed = true;
                }
                toSearch = i;
            }
        }
        if (failed) {
            System.out.println(0);
        } else {
            int sum = 0;
            for (int i : group) {
                if (i == toSearch) {
                    sum++;
                }
            }
            System.out.println(sum);
        }
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
        H01296 a = new H01296();
    }
}
