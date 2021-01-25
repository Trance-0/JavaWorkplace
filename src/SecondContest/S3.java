package SecondContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3 {
    private int maxbeauty;
    private int[][] pasture;
    private int[][] px;
    private int[][] py;
    private int size;

    public S3() throws IOException {
        maxbeauty = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        pasture = new int[size][size];
        py = new int[size][2];
        px = new int[size][2];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            boolean t;
            for (int j = 0; j < size; j++) {
                int temp = Integer.parseInt(st.nextToken());
                pasture[i][j] = temp;
                px[i][j % 2] += temp;
                py[j][i % 2] += temp;
            }
        }
        int metha = 0;
        int methb=0;
        for (int i = 0; i < size; i++) {
            metha+=Math.max(py[i][0], py[i][1]);
            methb+=Math.max(px[i][0], px[i][1]);
        }  
        System.out.println(Math.max(metha, methb));

    }

    public static void main(String[] args) throws IOException {
        S3 a = new S3();
    }
}
