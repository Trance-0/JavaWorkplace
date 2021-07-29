package C36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H01274 {
    private int V;
    private int[][] E;
    private int Esize;
    private int Ori;

    public H01274() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        Esize = Integer.parseInt(st.nextToken());
        E = new int[Esize][3];
        Ori = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < Esize; i++) {
            st = new StringTokenizer(br.readLine());
            E[i][0] = Integer.parseInt(st.nextToken()) - 1;
            E[i][1] = Integer.parseInt(st.nextToken()) - 1;
            E[i][2] = Integer.parseInt(st.nextToken());
        }
        // return from ori
        int[] R = new int[V];

        // to the ori
        int[] T = new int[V];
        for (int i = 0; i < V; i++) {
            R[i] = Integer.MAX_VALUE;
            T[i] = Integer.MAX_VALUE;
        }
        R[Ori] = 0;
        T[Ori] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (int[] j : E) {
                if (R[j[1]] > R[j[0]] + j[2] && R[j[0]] != Integer.MAX_VALUE) {
                    R[j[1]] = R[j[0]] + j[2];
                }
            }
        }
        for (int i = 0; i < V - 1; i++) {
            for (int[] j : E) {
                if (T[j[0]] > T[j[1]] + j[2] && T[j[1]] != Integer.MAX_VALUE) {
                    T[j[0]] = T[j[1]] + j[2];
                }
            }
        }

        int largest = 0;
        for (int i = 0; i < V; i++) {
            if (R[i] + T[i] > largest) {
                largest = R[i] + T[i];
            }
        }
        System.out.println(largest);
    }

    public static void main(String[] args) throws IOException {
        H01274 a = new H01274();
    }

    public int[] BellmanFord(int start, int[][] E, int V) {
        int dis[] = new int[V];
        for (int i = 0; i < V; i++) {
            dis[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < V - 1; i++) {
            for (int[] j : E) {
                if (dis[j[0]] > dis[j[1]] + j[2] && dis[j[1]] != Integer.MAX_VALUE) {
                    dis[j[0]] = dis[j[1]] + j[2];
                }
            }
        }
        return dis;
    }
}
