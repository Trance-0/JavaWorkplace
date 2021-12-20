//package ThirdContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2 {
    public int[] answer;
    public int[][] bound;
    public int[] lastState;//1 for Hi 2 for low
    public int N;
    public G2()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        answer=new int[N+1];
        lastState=new int[N+1];
        bound=new int[N+1][2];
        for(int i=0;i<N+1;i++){
            bound[i][1]=N+1;
        }
        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
        int splitPoint=Integer.parseInt(st.nextToken());
            for(int x=0;x<N+1;x++){
               if(splitPoint>bound[x][0]&&splitPoint<bound[x][1]){
                if(x+0.5<splitPoint){
                    bound[x][1]=splitPoint;
                    lastState[x]=1;
                }else{
                    bound[x][0]=splitPoint;
                    if(lastState[x]==1){
answer[x]++;
                    }
                    lastState[x]=2;
                }
               }
             }
        }
        for(int i :answer){
            System.out.println(i);
        }
    }
   
    public static void main(String[] args) throws IOException {
        G2 a=new G2();
    }
}
