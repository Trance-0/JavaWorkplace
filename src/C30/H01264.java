package C30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H01264 {
    private double[][] shortestPath;
    private int[][] points;
    private double[] finalpath;
    private int size;

    public H01264() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        shortestPath = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    shortestPath[i][j] = Double.POSITIVE_INFINITY;
                } else {
                    shortestPath[i][j] = 0;
                }
            }
        }
        points = new int[size][2];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }
        String temp;
        for (int i = 0; i < size; i++) {
            temp = br.readLine();
            for (int j = 0; j < size; j++) {
                int tempvalue = Integer.parseInt(temp.substring(j, j + 1));
                if (tempvalue == 1) {
                    shortestPath[i][j] = Math
                            .sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (shortestPath[i][k] > shortestPath[i][j] + shortestPath[j][k]) {
                        shortestPath[i][k] = shortestPath[i][j] + shortestPath[j][k];
                    }
                }
            }
        }
        printshortestpath();
        finalpath = new double[size];

        double maxingrid = 0;
        for (int i = 0; i < size; i++) {
            double templargest = 0;
            for (int j = 0; j < size; j++) {
                if (i != j && shortestPath[i][j] > templargest && shortestPath[i][j] != Double.POSITIVE_INFINITY) {
                    templargest = shortestPath[i][j];
                }
            }
            finalpath[i] = templargest;
            maxingrid = Math.max(templargest, maxingrid);
        }
        // for (int i = 0; i < size; i++) {
        // System.out.println(finalpath[i]);
        // }
        double ans = Double.POSITIVE_INFINITY;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (shortestPath[i][j] == Double.POSITIVE_INFINITY) {
                    ans = Math.min(ans, finalpath[i] + finalpath[j] + Math
                            .sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2)));
                }
            }
        }

        String result = String.format("%.6f", Math.max(ans, maxingrid));
        System.out.println(result);
    }

    private void printshortestpath() {
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                sb.append(shortestPath[i][j]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        H01264 a = new H01264();
    }
}
