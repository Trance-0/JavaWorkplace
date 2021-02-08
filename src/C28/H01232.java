package C28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class H01232 {
    private long h;
    private long w;
    private long[] root;
    private long[] xlength;
    private long[] ylength;
    private long sumoffence;
    private long staticfence;
    private int xi;
    private int yi;
    private int xm;
    private int ym;
    public H01232()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        w=Long.parseLong(st.nextToken());
        h=Long.parseLong(st.nextToken());
        int tw=Integer.parseInt(st.nextToken());
        int th=Integer.parseInt(st.nextToken());
        xlength=new long[tw];
        ylength=new long[th];
        int size=(tw+1)*(th+1);
        root=new long[size];
        long[]rawx=new long[tw];
        for(int i=0;i<tw;i++){
            rawx[i]=Long.parseLong(br.readLine());
        }
        Arrays.sort(rawx);
        xlength[0]=rawx[0];
        for(int i=1;i<tw;i++){
            xlength[i]=rawx[i]-xlength[i-1];
        }
        Arrays.sort(xlength);
        long[]rawy=new long[th];
        for(int i=0;i<th;i++){
            rawy[i]=Long.parseLong(br.readLine());
        }
        Arrays.sort(rawx);
        ylength[0]=rawy[0];
        for(int i=1;i<th;i++){
            ylength[i]=rawy[i]-ylength[i-1];
        }
        Arrays.sort(ylength);
        int xtop=tw+1;
        int xdown=th*xtop;
        for(int i=0;i<size;i++){
            if(i<=xtop||i>xdown||i%xtop==0||(i+1)%xtop==0){
                continue;
            }
            root[i]=i;
        }
        xi=tw;
        yi=th;
        for(int i=0;i<tw*th;i++){
            staticfence+=findlongestlegalfence();
        }
        sumoffence=h*(tw-1)+w*(th-1);
        System.out.println(sumoffence-staticfence);
    }
    
    private long findlongestlegalfence() {
        long nicex=xlength[xi];
        long nicey=ylength[xi];
        for(){
            
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        H01232 a=new H01232();
    }
}
