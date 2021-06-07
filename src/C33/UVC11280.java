package C33;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class UVC11280 {
    private int loopSize;
    private int V;
    private int[][] E;
    private int[] cost;
    private int[] depth;
    private HashMap<String, Integer> dictionary;

    public UVC11280() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        loopSize = Integer.parseInt(st.nextToken());
        for (int i = 0; i < loopSize; i++) {
            br.readLine();
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            depth = new int[V];
            cost = new int[V];
            dictionary = new HashMap<String, Integer>();
            for (int j = 0; j < V; j++) {
                st = new StringTokenizer(br.readLine());
                String toput = st.nextToken();
                dictionary.put(toput, dictionary.size());
            }
            int start = dictionary.get("Calgary");
            int end = dictionary.get("Fredericton");
            st = new StringTokenizer(br.readLine());
            int edgeSize = Integer.parseInt(st.nextToken());
            E = new int[edgeSize][3];
            for (int j = 0; j < edgeSize; j++) {
                st = new StringTokenizer(br.readLine());
                E[j][0] = dictionary.get(st.nextToken());
                E[j][1] = dictionary.get(st.nextToken());
                E[j][2] = Integer.parseInt(st.nextToken());
            }
            int[] ans = new int[11];
            for (int j = 1; j < V; j++) {
                cost[j] = Integer.MAX_VALUE;
                depth[j] = Integer.MAX_VALUE;
            }
            depth[start] = 0;
            for (int j = 0; j < 11; j++) {
                for (int[] l : E) {
                    if (depth[l[0]] == j) {
                        if (cost[l[1]] > cost[l[0]] + l[2] && cost[l[0]] != Integer.MAX_VALUE) {
                            cost[l[1]] = cost[l[0]] + l[2];
                            depth[l[1]] = j + 1;
                        }
                    } else if (depth[l[1]] == j) {
                        if (cost[l[1]] > cost[l[0]] + l[2] && cost[l[0]] != Integer.MAX_VALUE) {
                            cost[l[1]] = cost[l[0]] + l[2];
                            depth[l[0]] = j + 1;
                        }
                    }
                }
                ans[j] = cost[end];
            }
            st = new StringTokenizer(br.readLine());
            int stopNumber = Integer.parseInt(st.nextToken());
            System.out.println("Scenario #" + (i + 1));
            for (int j = 0; j < stopNumber; j++) {
                int stop = Integer.parseInt(st.nextToken());
                if (ans[stop] != Integer.MAX_VALUE) {
                    System.out.println("Total cost of flight(s) is $" + ans[stop]);
                } else {
                    System.out.println("No satisfactory flights");
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        UVC11280 a = new UVC11280();
    }
}
