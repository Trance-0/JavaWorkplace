package C25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class H01187 {
    private int[] root;
    private int n;
    private int m;

    public H01187() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        while (n+m!=0) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int size = Integer.parseInt(st.nextToken());
                int lastone = Integer.parseInt(st.nextToken());
                for (int j = 1; j < size; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                    root[findroot(lastone)] = root[findroot(temp)];
                    lastone = temp;
                }
            }
            int zero=findroot(0);
            int sum=0;
            for(int i=0;i<n;i++){
                if (findroot(i)==zero){
                    sum++;
                }
            // }System.out.println("=========================");
            System.out.println(sum);
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
        }
    }

    private int findroot(int element) {
        // System.out.println(element);
        if (element != root[element]) {
            root[element] = findroot(root[element]);
        }
        return root[element];
    }

    public static void main(String[] args) throws IOException {
        H01187 a = new H01187();
    }
}
