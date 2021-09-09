package C42;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class H01321 {
public boolean[][]E;
    public int V;
    public int[] inDegree;

    public H01321()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        V=Integer.parseInt(st.nextToken());
        E=new boolean[V][V];
        inDegree=new int[V];
        int max=0;
        for(int i=0;i<V;i++){
            st=new StringTokenizer(br.readLine());
            int temp=Integer.parseInt(st.nextToken());
            while(temp!=0){
                E[i][temp-1]=true;
                inDegree[temp-1]+=1;
                if(inDegree[temp-1]>max){
max=inDegree[temp-1];
                }
                temp=Integer.parseInt(st.nextToken());
            }

        }
        boolean[]isChecked=new boolean[V];
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<=max;i++){
           for(int j=0;j<V;j++){
            if(!isChecked[j]&&inDegree[j]==i){
                isChecked[j]=true;
                sb.append(j+1);
                sb.append(" ");
            }
           } 
        }
        printarr(inDegree);
        System.out.println(sb.toString());
    }
    public void printarr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args)throws IOException {
        H01321 a=new H01321();
    }

}
