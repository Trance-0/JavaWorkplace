package C26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class H268C {
    private long n;
    private long m;
    private long k;
    private long x;
    public H268C()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Long.parseLong(st.nextToken());
        m=Long.parseLong(st.nextToken());
        k=Long.parseLong(st.nextToken());
        x=Long.parseLong(st.nextToken());
        System.out.println((x+(m*getmode(10, k, n))%n)%n);
    }
    private long getmode(long base, long index, long mode) {
        // System.out.println(index);
        if(index==0){
            return 1%mode;
        }else if(index==1){
            return base%mode;
        }else if(index%2==1){
            long t=getmode(base, index/2, mode);
            return ((base*t)%mode*t)%mode;
        }else{
            long t=getmode(base, index/2, mode);
            return (t*t)%mode;
        }
    }
    public static void main(String[] args) throws IOException {
        H268C a=new H268C();
    }
}
