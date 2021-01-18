package C26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.util.StringTokenizer;


public class H268A {
    private long length;
    private long M;
    private long size;

    public H268A()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        length=Long.parseLong(st.nextToken());
        for(long i=0;i<length;i++){
            st=new StringTokenizer(br.readLine());
            M=Long.parseLong(st.nextToken());
            st=new StringTokenizer(br.readLine());
            size=Long.parseLong(st.nextToken());
            long sum=0;
            long a=0;
            long b=0;
            for(long j=0;j<size;j++){
                st=new StringTokenizer(br.readLine());
                a=Long.parseLong(st.nextToken());
                b=Long.parseLong(st.nextToken());
                sum=(sum+getmode(a, b, M))%M;
            }
            System.out.println(sum);
        }
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

    public static void main(String [] args) throws IOException{
        H268A a=new H268A();
    }
}
