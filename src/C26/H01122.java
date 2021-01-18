package C26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H01122 {
    private long a;
    private long b;
    private long p;

    public H01122() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        System.out.println(getmode(a,b,p));
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
        H01122 a=new H01122();
    }
}
