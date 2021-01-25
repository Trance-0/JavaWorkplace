package C26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H264B {
    private int n;
    private int[] addsum;
    private int q;

    public H264B() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        addsum = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            addsum[i + 1] = addsum[i] + Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken());
            System.out.println(addsum[end] - addsum[start]);
        }
    }

    public static void main(String[] args) throws IOException {
        H264B a = new H264B();
    }
}
