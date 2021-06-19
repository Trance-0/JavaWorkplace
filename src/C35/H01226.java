package C35;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class H01226 {
    public class DisjointSet {
        int[] parentNode;

        public DisjointSet(int size) {
            parentNode = new int[size];
            for (int i = 0; i < size; i++) {
                parentNode[i] = i;
            }
        }

        public int findParent(int i) {
            if (parentNode[i] != i) {
                parentNode[i] = findParent(parentNode[i]);
            }
            return parentNode[i];
        }

        public void union(int a, int b) {
            parentNode[findParent(a)] = findParent(b);
        }

        public boolean isConnected(int a, int b) {
            return findParent(a) == findParent(b);
        }
    }

    public int[][] E;
    public int Esize;
    public int Vsize;

    public H01226() throws IOException {
        // M is the number of the edges
        // N is the number of nodes
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Vsize = Integer.parseInt(st.nextToken());
        Esize = Integer.parseInt(st.nextToken());
        E = new int[Esize][3];
        DisjointSet isChecked = new DisjointSet(Vsize);
        for (int i = 0; i < Esize; i++) {
            st = new StringTokenizer(br.readLine());
            E[i][0] = Integer.parseInt(st.nextToken()) - 1;
            E[i][1] = Integer.parseInt(st.nextToken()) - 1;
            E[i][2] = Integer.parseInt(st.nextToken());
        }
        int[][] treePath = new int[Vsize - 1][3];
        Arrays.sort(E, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int indexOfTree = 0;
        for (int[] i : E) {
            if (!isChecked.isConnected(i[0], i[1])) {
                treePath[indexOfTree] = i;
                isChecked.union(i[0], i[1]);
                indexOfTree++;
            }
        }
        int secondShortestPath = Integer.MAX_VALUE;
        int result = 0;
        for (int i[] : treePath) {
            result += i[2];
        }
        for (int i[] : treePath) {
            int path = secondShortestSpanningTree(treePath, i);
            if (path != result) {
                secondShortestPath = Math.min(path, secondShortestPath);
            }
        }
        System.out.println(secondShortestPath);
    }

    public int secondShortestSpanningTree(int[][] treePath, int[] delete) {
        DisjointSet isChecked = new DisjointSet(Vsize);
        int[][] newTreePath = new int[Vsize - 1][3];
        int index = 0;
        for (int[] i : treePath) {
            if (!Arrays.equals(i, delete)) {
                isChecked.union(i[0], i[1]);
                newTreePath[index] = i;
                index++;
            }
        }
        for (int[] i : E) {
            if (!isChecked.isConnected(i[0], i[1]) && i[2] > delete[2]) {
                newTreePath[Vsize - 2] = i;
            }
        }
        int result = 0;
        for (int i[] : newTreePath) {
            result += i[2];
        }
        return result;
    }

    public boolean equals(int[] i, int[] j) {
        return i[0] == j[0] && i[1] == j[1] && i[2] == j[2];
    }

    public static void main(String[] args) throws IOException {
        H01226 a = new H01226();
    }
}
