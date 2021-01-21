package C26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H264C {
    private long p;
    private long q;
    private int n;

    public H264C() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p = div(Long.parseLong(st.nextToken()));
            q = div(Long.parseLong(st.nextToken()));
            if (p % q == 0) {
                System.out.println("Finite");
            } else {
                System.out.println("Infinite");
            }
        }
    }

    private long div(long l) {
        while (l % 2 == 0) {
            l /= 2;
        }
        while (l % 5 == 0) {
            l /= 5;
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        H264C a = new H264C();
    }
}
