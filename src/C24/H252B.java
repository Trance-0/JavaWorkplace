package C24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H252B {
    private long N;
    private long M;
    private long K;
    private long X;

    public H252B() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long i = 1;
        long j = 10000000;
        long X = (i + j) / 2;
        while (i != j) {
            if (works(X)) {
                i = X;
            } else {
                j = X - 1;
            }
            System.out.println("i: " + i + " j: " + j);
            X = (i + j + 1) / 2;
        }
        System.out.println(X);
    }

    private boolean works(long x2) {
        System.out.println("time=" + (Math.log(x2) / Math.log(N - M * x2) + x2));
        if (Math.log(x2) / Math.log(N - M * x2) + x2 <= K) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        H252B a = new H252B();
    }
}
