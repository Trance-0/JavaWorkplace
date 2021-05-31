package C33;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVC558 {
    private int loopSize;
    private int V;
    private int[][] E;
    private int[] distance;

    public UVC558() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        loopSize = Integer.parseInt(st.nextToken());
        for (int i = 0; i < loopSize; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            int edgeSize = Integer.parseInt(st.nextToken());
            E = new int[edgeSize][3];
            for (int j = 0; j < edgeSize; j++) {
                st = new StringTokenizer(br.readLine());
                E[j][0] = Integer.parseInt(st.nextToken());
                E[j][1] = Integer.parseInt(st.nextToken());
                E[j][2] = Integer.parseInt(st.nextToken());
            }
            distance = new int[V];
            for (int j = 1; j < V; j++) {
                distance[j] = Integer.MAX_VALUE;
            }
            for (int j = 0; j < V - 1; j++) {
                for (int[] l : E) {
                    if (distance[l[1]] > distance[l[0]] + l[2]) {
                        distance[l[1]] = distance[l[0]] + l[2];
                    }
                }
            }
            boolean canCheat = false;
            for (int[] l : E) {
                if (distance[l[1]] > distance[l[0]] + l[2]) {
                    canCheat = true;
                }
            }
            if (canCheat) {
                System.out.println("possible");
            } else {
                System.out.println("not possible");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        UVC558 a = new UVC558();
    }
}
