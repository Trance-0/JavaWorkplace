package C24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
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
        for (int i = 0; i < N; i++) {
            int in = Integer.parseInt(st.nextToken());
            if (in > biggest) {
                biggest = in;
            }
            B[i] = in;
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
        PriorityQueue<Integer> TB = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i : B) {
            TB.offer(i);
        }
        for (int i = 0; i < K; i++) {
            if (!TB.isEmpty()) {
                if (TB.peek() > cut) {
                    TB.add(TB.poll() - cut);
                    if(i*2>=K){
                    count += cut;
                    }
                } else {
                    if(i*2>=K){
                    count += TB.poll();
                    }else{
                        TB.poll();
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        H252A a = new H252A();
    }
}
