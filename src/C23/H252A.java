package C23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H252A {
    private int K;
    private int[] B;
    private int N;

    public H252A() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = new int[N];
        st = new StringTokenizer(br.readLine());
        int biggest = 0;
        for (int i = 0; i < K; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            if (B[i] > biggest) {
                biggest = B[i];
            }
        }
        int bestcount = 0;
        for (int i = 1; i < biggest; i++) {
            int count = getcount(i);
            if (count > bestcount) {
                bestcount = count;
            }
            // System.out.println("cut= " + i + " count= " + count);
        }
        System.out.println(bestcount);
    }

    private int getcount(int cut) {
        int count = 0;
        int num = 0;
        int[] TB = new int[N];
        for (int i = 0; i < N; i++) {
            TB[i] = B[i];
        }
        while (num != K) {
            boolean hascut = false;
            for (int i = 0; i < N; i++) {
                if (TB[i] >= cut) {
                    TB[i] = TB[i] - cut;
                    hascut = true;
                    break;
                }
            }
            // printarray(B);
            if (hascut) {
                if (num * 2 >= K) {
                    count += cut;
                }
                num++;
            } else {
                cut--;
            }
        }
        return count;
    }

    private void printarray(int[] b2) {
        for (int i : b2) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        H252A a = new H252A();
    }
}
