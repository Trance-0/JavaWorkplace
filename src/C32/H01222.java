package C32;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

import java.io.InputStreamReader;

public class H01222 {
    private int[][] path;
    private int[] shortestPath;
    private boolean[] isChecked;
    private int roomnumber;
    private int roadnumber;

    public H01222() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        roomnumber = Integer.parseInt(st.nextToken());
        roadnumber = Integer.parseInt(st.nextToken());
        path = new int[roomnumber][roomnumber];
        shortestPath = new int[roomnumber];
        isChecked = new boolean[roomnumber];
        for (int i = 0; i < roomnumber; i++) {
            shortestPath[i] = Integer.MAX_VALUE;
            for (int j = 0; j < roomnumber; j++) {
                path[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < roadnumber; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            if (path[x][y] > l) {
                path[x][y] = l;
                path[y][x] = l;
            }
        }
        isChecked[0] = true;
        shortestPath[0] = 0;
        for (int t = 1; t < roomnumber; t++) {
            int tempi = 0;
            int temps = Integer.MAX_VALUE;
            for (int i = 0; i < roomnumber; i++) {
                // find unchecked value
                if (isChecked[i]) {
                    // i is the start point
                    for (int j = 0; j < roomnumber; j++) {
                        // possible reach
                        if (path[i][j] + shortestPath[i] < temps && !isChecked[j]) {
                            tempi = j;
                            temps = path[i][j] + shortestPath[i];
                        }
                    }
                }
            }
            shortestPath[tempi] = temps;
            isChecked[tempi] = true;
        }
        System.out.println(printarr(shortestPath));
        long result = 1;
        long factor = (long) Integer.MAX_VALUE;
        for (int i = 1; i < roomnumber; i++) {
            long possibleReach = 0;
            for (int j = 0; j < roomnumber; j++) {
                if (shortestPath[i] == shortestPath[j] + path[i][j]) {
                    possibleReach++;
                }
            }
            result = (result * possibleReach) % factor;
        }
        // System.out.println(printarr(possibleReach));
        System.out.println(Long.toString(result));
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
        H01222 a = new H01222();
    }
}