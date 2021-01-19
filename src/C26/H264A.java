package C26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H264A {
    private int t;
    private int s;
    private int x;

    public H264A() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        if ((x - t) % s == 0 || ((x - t) % s == 1 && x != t + 1)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) throws IOException {
        H264A a = new H264A();
    }
}
