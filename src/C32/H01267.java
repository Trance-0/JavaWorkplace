//http://oj.leaplearner.com/problem/01267
package C32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class H01267 {
    public int N;

    public H01267() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int numOfTest=1;
        while (N != 0) {
            boolean canGetMoney=false;
            double[][] path = new double[N][N];
            HashMap<String,Integer> hm=new HashMap<String,Integer>();
            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine());
                hm.put(st.nextToken(), i);
            }
            st = new StringTokenizer(br.readLine());
           int numOfPath = Integer.parseInt(st.nextToken());
           for(int i=0;i<numOfPath;i++){
               st=new StringTokenizer(br.readLine());
            String a=st.nextToken();
             double tempd=Double.parseDouble(st.nextToken());
             String b=st.nextToken();
            path[hm.get(a)][hm.get(b)]=tempd;
           }
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    for (int c = 0; c < N; c++) {
                        if (path[a][b] <path[a][c] * path[c][b]) {
                           path[a][b]=path[a][c]*path[c][b];
                        }
                    }
                }
            }
            for(int i=0;i<N;i++){
                if(path[i][i]>1){
                    canGetMoney=true;
                }
            }
            if(canGetMoney){
            System.out.println("Case "+numOfTest+" : Yes");
            }else{
                System.out.println("Case "+numOfTest+" : No");
            }
        numOfTest++;
        br.readLine();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        H01267 a = new H01267();
    }
}