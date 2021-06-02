package C33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

//http://oj.leaplearner.com/problem/01281
public class H01281 {
    private int N;
    private int V;
    private int edgeSize;
    private int[] cowPos;
    private int[][] E;
    private int seed;

    public H01281() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        edgeSize = Integer.parseInt(st.nextToken());
        cowPos = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cowPos[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        System.out.println(printarr(cowPos));
        E = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    E[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < edgeSize; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            if(E[from][to]>weight){
                E[from][to] = weight;
                E[to][from] = weight;
                }
        }
        // I love floy-warshall
        for (int i = 0; i < V; i++) {
            for (int j = i; j < V; j++) {
                for (int k = 0; k < V; k++) {
                    if (E[i][j] > E[i][k] + E[k][j] && E[i][k] + E[k][j] > 0) {
                        E[i][j] = E[i][k] + E[k][j];
                        E[j][i] = E[i][k] + E[k][j];
                    }
                }
            }
        }
        System.out.println(printarr(E));
        int sweetspot = Integer.MAX_VALUE;
        for (int i[] : E) {
            int temp = 0;
            for (int j : cowPos) {
                temp += i[j];
            }
            System.out.println(i+" : "+temp);
            if (temp < sweetspot&&temp>0) {
                sweetspot = temp;
            }
        }
        System.out.println(sweetspot);
    }

    public String printarr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] i : arr) {
            sb.append("[");

            for (int j : i) {
                sb.append(j);
                sb.append(",");
            }
            sb.append("]" + "\n");
        }
        return sb.toString();
    }

    public String printarr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
            sb.append(" ");
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        H01281 a = new H01281();
    }
}
