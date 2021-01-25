package SecondContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2 {
    private int[] last;
    private int[] sub;
    private int[] pre;
    private String paint;
    private int Q;
    private int N;

    public S2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        paint = br.readLine();
        sub = new int[N + 1];
        pre = new int[N + 1];
        last = new int[26];
        int current = 0;
        for (int i = 0; i < N; i++) {
            char temp = paint.charAt(i);
            int searchfrom = temp - 'A';
            if (stroke(searchfrom, i)) {
                current++;
            }
            last[searchfrom] = i + 1;
            pre[i + 1] = current;
        }
        // printArray(pre);
        last = new int[26];
        for (int i = 0; i < 26; i++) {
            last[i] = N;
        }
        current = 0;
        for (int i = N - 1; i >= 0; i--) {
            char temp = paint.charAt(i);
            int searchfrom = temp - 'A';
            if (restroke(searchfrom, i)) {
                current++;
            }
            last[searchfrom] = i;
            sub[i] = current;
        }
        // printArray(sub);
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(pre[a - 1] + sub[b]);
        }
    }

    private boolean stroke(int index, int newpos) {

        int lastpos = last[index];
        if (lastpos == 0) {
            return true;
        }
        for (int i = 0; i < index; i++) {
            if (last[i] > lastpos && last[i] < newpos + 1) {
                return true;
            }
        }
        return false;
    }

    private boolean restroke(int index, int newpos) {

        int lastpos = last[index];
        if (lastpos == N) {
            return true;
        }
        for (int i = 0; i < index; i++) {
            if (last[i] < lastpos && last[i] > newpos) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        S2 a = new S2();
    }

    private void printArray(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i : a) {
            sb.append(i);
            sb.append(",");
        }
        System.out.println(sb.toString());
    }
}
