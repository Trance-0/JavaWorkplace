package C40;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 5 7 1 3 2 3
*前缀和 5 12 13 16 18 21
*差分序列 5 2 -6 2 -1 1
*做群体操作的时候改首和尾就好啦~
*/
public class H01091 {
    public int number,index,height,record;
    public boolean[][]recordRepete;
    public int[] heights;
    public H01091()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer( br.readLine());
        number=Integer.parseInt(st.nextToken());
        index=Integer.parseInt(st.nextToken());
        height=Integer.parseInt(st.nextToken());
        record=Integer.parseInt(st.nextToken());
        recordRepete=new boolean[number][number];
        heights=new int[number];
        for(int i=0;i<record;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken())-1;
            int b=Integer.parseInt(st.nextToken())-1;
            if(!recordRepete[a][b]||a-b==1||a-b==-1){
            if(a<b){
                heights[a+1]=heights[a+1]-1;
                heights[b]=heights[b]+1;
            }else{
                heights[a]=heights[a]+1;
                heights[b+1]=heights[b+1]-1;
            }
                recordRepete[a][b]=true;
                recordRepete[b][a]=true;
            }
        }
        int factor=height-heights[index-1];
        for(int i=0;i<number;i++){
            factor+=heights[i];
            System.out.println(factor);
        }
    }
     public static void main(String[] args) throws IOException{
         H01091 a=new H01091();
     }
}
