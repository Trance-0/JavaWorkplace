package C25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class H00699 {
    private int[] x = { 14, 1, 2, 4, 7, 8, 11, 13 };
    private int N;

    public H00699() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int r = N / 8;
        // System.out.println("r=" + r);
        int base = r * 15;
        // System.out.println("base=" + base);
        int add = x[N % 8];
        // System.out.println("add=" + add);
        System.out.println(base + add);
    }

    public static void main(String[] args) throws IOException {
        H00699 a = new H00699();
    }
}
