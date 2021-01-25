package C26;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H264D {
    private int n;
    private int[] city;
    private int[] tele;

    public H264D() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        city = new int[n];
        tele = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            city[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        // printlist(city);
        for (int i = 0; i < n; i++) {
            int step = 0;
            LinkedList<Integer> d = new LinkedList<Integer>();
            d.add(0);
            int layersize = 1;
            boolean end = false;
            while (true) {
                step++;
                for (int j = 0; j < layersize; j++) {

                }
                layersize = d.size();
            }
            tele[i] = step;
        }
        printlist(tele);
    }

    private void printlist(int[] t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t[0]);
        for (int i = 1; i < t.length; i++) {
            sb.append(" ");
            sb.append(t[i]);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        H264D a = new H264D();
    }
}
