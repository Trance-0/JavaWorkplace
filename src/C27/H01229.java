package C27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class H01229 {
    private int[][] grid;
    private int n;
    private ArrayList<Integer> noconnected;
    private ArrayList<Integer> connected;
    private int[] shortestPath;
    private boolean[] isconnected;

    public H01229() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        grid = new int[n][n];
        noconnected = new ArrayList<Integer>();
        connected = new ArrayList<Integer>();
        shortestPath = new int[n];
        int fa = 0;
        int fb = 0;
        isconnected = new boolean[n];
        int smallestelement = 100000;
        for (int i = 0; i < n; i++) {
            shortestPath[i] = 100000;
            noconnected.add(i);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int temp = Integer.parseInt(st.nextToken());
                grid[i][j] = temp;
                if (temp < smallestelement && i != j) {
                    fa = i;
                    fb = j;
                    smallestelement = temp;
                }
            }
        }
        connected.add(fa);
        connected.add(fb);
        noconnected.remove(Math.max(fa, fb));
        noconnected.remove(Math.min(fa, fb));
        shortestPath[fa] = 0;
        shortestPath[fb] = smallestelement;
        isconnected[fa] = true;
        isconnected[fb] = true;
        update(fb);
        update(fa);
        for (int k = 0; k < n - 2; k++) {
            int f =findmin();
            isconnected[f]=true;
            update(f);
        }
        System.out.println(sum(shortestPath));
    }

    private int findmin() {
        int stp=100000;
        int nice=-1;
        for (int i = 0; i < n; i++) {
            if (isconnected[i]) {
                continue;
            }
            if(shortestPath[i]<stp){
                stp=shortestPath[i];
                nice=i;
            }
        }
        return nice;
    }

    private int sum(int[] sp) {
        int result = 0;
        for (int i : sp) {
            result += i;
        }
        return result;
    }

    private void update(int f) {
        for (int i = 0; i < n; i++) {
            if (isconnected[i]) {
                continue;
            }
            shortestPath[i] = Math.min(shortestPath[i], grid[i][f]);
        }
    }

    public static void main(String[] args) throws IOException {
        H01229 a = new H01229();
    }
}
