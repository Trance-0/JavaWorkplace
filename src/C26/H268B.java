package C26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;


public class H268B {
    private int size;
    private long a;
    private long b;
    private long c;
    private long k;
    private static int m=200907;
    public H268B()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        size=Integer.parseInt(st.nextToken());
        for(int i=0;i<size;i++){
            st=new StringTokenizer(br.readLine());
            a=Long.parseLong(st.nextToken());
            b=Long.parseLong(st.nextToken());
            c=Long.parseLong(st.nextToken());
            k=Long.parseLong(st.nextToken());
            if(b-a==c-b){
                long dif=b-a;
                System.out.println(a%m+((k-1)%m*dif)%m);
            }else{
                long dif=b/a;
                System.out.println((a*getmode(dif, k-1, m))%m);
            }
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
    public static void main (String [] args) throws IOException{
H268B a=new H268B();
    }
}
