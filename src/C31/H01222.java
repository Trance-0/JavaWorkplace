package C31;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class H01222 {
    private int N;// number of rooms;
    private int M;// number of routes
    private int[] S;
    private int[][] route;
    private List<Integer>[] finalRoute;

    public H01222() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        route = new int[N][N];
        // finalRoute = new ArrayList[N];

        for (int i = 0; i < N; i++) {// y value
            for (int j = 0; j < N; j++) {// x value
                route[i][j] = 1000;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            if (d < route[a][b]) {
                route[a][b] = d;
                route[b][a] = d;
            }
        }
        // for (int i = 0; i < N; i++) {
        // ArrayList<Integer> temp = new ArrayList<Integer>();
        // for (int j = 0; j < N; j++) {
        // if (route[i][j] <= 200) {
        // temp.add(route[i][j]);
        // }
        // }
        // finalRoute[i] = temp;
        // }
        printArr(route);
        boolean[] note = new boolean[N];
        note[0] = true;
        S = new int[N];
        for (int t = 0; t < N; t++) {
            int tempi = 0;
            int temps = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {// find non zero value
                if (note[i]) {
                    // i is the starter
                    for (int j = 0; j < N; j++) {// possible reach
                        if (route[i][j] + S[i] < temps && !note[j]) {
                            tempi = j;
                            temps = route[j][j] + S[i];
                        }
                    }
                }
            }
            S[tempi] = temps;
        }
        for (int i = 0; i < N; i++) {
            System.out.println(S[i]);
        }
    }

    private void printArr(int[][] route2) {
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(route2[i][j]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        H01222 a = new H01222();
    }
}
