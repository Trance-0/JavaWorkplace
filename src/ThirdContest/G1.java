package ThirdContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1 {
    
    public int T; //state. 1 for smallest sum and 2 for greatest sum of unpaired cows;
    public int N; //number of cows
    public int K; //maximum distance

    public G1()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        T=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        if(T==1){
        }else{
        }
    }
    public static void main(String[] args) throws IOException {
        G1 a=new G1();
    }
}
