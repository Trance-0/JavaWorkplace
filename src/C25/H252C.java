package C25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class H252C {
    private int[][] log;
    private int[] root;
    private int[] data;
    private int length;
    private int loglength;
    public H252C() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length=Integer.parseInt(st.nextToken());
        loglength=Integer.parseInt(st.nextToken());
        root=new int[length];
        data=new int[length];
        log=new int[loglength][3];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<length;i++){
            root[i]=i;
            data[i]=Integer.parseInt(st.nextToken())-1;
        }
        for(int i=0;i<loglength;i++){
            st = new StringTokenizer(br.readLine());
            log[i][0]=Integer.parseInt(st.nextToken())-1;
            log[i][1]=Integer.parseInt(st.nextToken())-1;
            log[i][2]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(log,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                // TODO Auto-generated method stub
                return o2[2]-o1[2];
            }
        });

        int indexoflog=0;
        for(int i=0;i<length;i++){
            int start=i;
            int end=data[i];
            if(findroot(start)!=findroot(end)){
                while(findroot(start)!=findroot(end)){
                    addlog(log[indexoflog]);
                    indexoflog++;
                }
            }
        }
        if(indexoflog==0){
            System.out.println(-1);
        }else{
            System.out.println(log[indexoflog-1][2]);
        }
    }
    
    private void addlog(int[] lg) {
        int in=lg[0];
        int out=lg[1];
        root[findroot(in)]=findroot(out);
    }

    private int findroot(int element) {
        // System.out.println(element);
        if(element!=root[element]){
            root[element]=findroot(root[element]);
        }
        return root[element];
    }

    public static void main(String[] args) throws IOException {
        H252C a = new H252C();
    }
}
