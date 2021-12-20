import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public int[] answer;
    public int[] lastState;//1 for Hi 2 for low
    public int N;
    public Main()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        answer=new int[N+1];
        lastState=new int[N+1];
        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
        int splitPoint=Integer.parseInt(st.nextToken());
            for(int j=0;j<N+1;j++){
                if(j<splitPoint){
                    lastState[j]=1;
                }else{
                    if(lastState[j]==1){
                        answer[j]++;
                    }
                    lastState[j]=2;
                }
             }
        }
        for(int i :answer){
            System.out.println(i);
        }
    }
   
    public static void main(String[] args) throws IOException {
        Main a=new Main();
    }
}
