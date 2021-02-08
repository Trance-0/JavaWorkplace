package C27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H01231 {
    private int[] data;
    private int[][] grid;
    private int n;
    private int[] longestPath;
    private boolean[] isconnected;
    public H01231()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data=new int[n];
        grid = new int[n][n];
        longestPath = new int[n];
        int fa = 0;
        int fb = 0;
        isconnected = new boolean[n];
        int longestelement = 0;
        for(int i=0;i<n;i++){
            data[i]=Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp=i^j;
                grid[i][j] = temp;
                if (temp > longestelement && i != j) {
                    fa = i;
                    fb = j;
                    longestelement = temp;
                }
            }
        }
        longestPath[fa] = 0;
        longestPath[fb] = longestelement;
        isconnected[fa] = true;
        isconnected[fb] = true;
        update(fb);
        update(fa);
        for (int k = 0; k < n - 2; k++) {
            int f =findmax();
            isconnected[f]=true;
            update(f);
        }
        System.out.println(sum(longestPath));
    }
    private int findmax() {
        int stp=0;
        int nice=-1;
        for (int i = 0; i < n; i++) {
            if (isconnected[i]) {
                continue;
            }
            if(longestPath[i]>stp){
                stp=longestPath[i];
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
            longestPath[i] = Math.max(longestPath[i], grid[i][f]);
        }
    }
    public static void main(String[] args) throws IOException{
        H01231 a=new H01231();
    }
}
