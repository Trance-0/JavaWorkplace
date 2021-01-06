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
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        long i = 1;
        long j = 100000000000L;
        long X = (i + j) / 2;
        while (i < j) {
            long tk=works(X);
            if (tk<K) {
                i = X;
            } else {
                j = X - 1;
            }
            System.out.println("i: " + i + " j: " + j+" time="+tk);
            X = (i + j + 1) / 2;
        }
        System.out.println(X);
    }

    private long works(long x2) {
        long p=N;
        long time=0;
        long factor=0;
        while(p/x2>M){
            factor=p/x2;
            long down=factor*x2;
            long tt=(p-down)/factor+1;
            p=p-tt*factor;
            time+=tt;
        }
        time+=p/M;
        if(p%M!=0){
time++;
        }
        return time;
    }

    public static void main(String[] args) throws IOException {
        H252B a = new H252B();
    }
}
