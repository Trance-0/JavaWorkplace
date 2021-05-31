package C33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class UVC11280 {
    private int loopSize;
    private int V;
    private int[][] E;
    private int[][] cost;
    private HashMap<String, Integer> dictionary;

    public UVC11280() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        loopSize = Integer.parseInt(st.nextToken());
        for (int i = 0; i < loopSize; i++) {
            // br.readLine();
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            dictionary = new HashMap<String, Integer>();
            dictionary.put("Calgary", 0);
            dictionary.put("Fredericton", 1);
            for (int j = 0; j < V; j++) {
                st = new StringTokenizer(br.readLine());
                String toput = st.nextToken();
                if (toput.compareTo("Calgary") != 0 && toput.compareTo("Fredericton") != 0) {
                    dictionary.put(toput, dictionary.size());
                }
            }
            st = new StringTokenizer(br.readLine());
            int edgeSize = Integer.parseInt(st.nextToken());
            E = new int[edgeSize][3];
            for (int j = 0; j < edgeSize; j++) {
                st = new StringTokenizer(br.readLine());
                E[j][0] = dictionary.get(st.nextToken());
                E[j][1] = dictionary.get(st.nextToken());
                E[j][2] = Integer.parseInt(st.nextToken());
            }
            cost = new int[11][V];
            for (int j = 1; j < V; j++) {
                cost[0][j] = Integer.MAX_VALUE;
            }
            for (int j = 0; j < V - 1; j++) {
                for (int[] l : E) {
                    if (cost[j][l[1]] > cost[j][l[0]] + l[2]) {
                        cost[j][l[1]] = cost[j][l[0]] + l[2];
                    }
                }
                for (int l = 0; l < V; l++) {
                    cost[j + 1][l] = cost[j][l];
                }
            }
            st = new StringTokenizer(br.readLine());
            int stopNumber = Integer.parseInt(st.nextToken());
            System.out.println("Scenario #" + i + 1);
            for (int j = 0; j < stopNumber; j++) {
                int stop = Integer.parseInt(st.nextToken());
                if (stop != Integer.MAX_VALUE) {
                    System.out.println("Total cost of flight(s) is $" + cost[stop][1]);
                } else {
                    System.out.println("No satisfactory flights");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        UVC11280 a = new UVC11280();
    }
}
