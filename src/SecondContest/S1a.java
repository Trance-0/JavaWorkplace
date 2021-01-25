package SecondContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S1a {
    private int numofcows;
    private int numoflogs;
    private int[] cows;
    private boolean[][] path;

    public S1a() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numofcows = Integer.parseInt(st.nextToken());
        numoflogs = Integer.parseInt(st.nextToken());
        cows = new int[numofcows];
        path = new boolean[numofcows][numofcows];
        for (int i = 0; i < numofcows; i++) {
            cows[i] = i;
            path[i][i] = true;
        }
        for (int i = 0; i < numoflogs; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            swap(a, b);
        }
        int[] alreadycounted = new int[numofcows];
        for (int i = 0; i < numofcows; i++) {
            if (alreadycounted[i] != 0) {
                continue;
            } else {
                ArrayList<Integer> cango = new ArrayList<Integer>();
                cango.add(i);
                int egt = cows[i];
                while (i != egt) {
                    cango.add(egt);
                    egt = cows[egt];
                }
                boolean[] uniquepos = new boolean[numofcows];
                for (int pos : cango) {
                    for (int j = 0; j < numofcows; j++) {
                        if (path[pos][j]) {
                            uniquepos[j] = true;
                        }
                    }
                }
                    int unique = countoftrue(uniquepos);
                    for (int k : cango) {
                        alreadycounted[k] = unique;
                    }
            }
        }
        for (int i : alreadycounted) {
            System.out.println(i);
        }
    }

    private int countoftrue(boolean[] uniquepos) {
        int result = 0;
        for (boolean i : uniquepos) {
            if (i) {
                result++;
            }
        }
        return result;
    }

    private void swap(int a, int b) {
        int temp = cows[a];
        cows[a] = cows[b];
        cows[b] = temp;
        path[a][b] = true;
        path[b][a] = true;
    }

    public static void main(String[] args) throws IOException {
        S1a a = new S1a();
    }
}
