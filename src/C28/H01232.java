package C28;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H01232 {
    public class line {
        private int length;
        private int a;
        private int b;

        public line(int c, int d, int l) {
            length = l;
            a = c;
            b = d;
        }
    }

    private int[] point;
    private LinkedList<line> fence;
    public int xlength;
    public int ylength;
    public int xsize;
    public int ysize;

    public H01232() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        xlength = Integer.parseInt(st.nextToken());
        ylength = Integer.parseInt(st.nextToken());
        xsize = Integer.parseInt(st.nextToken()) + 2;
        ysize = Integer.parseInt(st.nextToken()) + 2;
        point = new int[(ysize) * (xsize)];
        for (int i = 0; i < (ysize) * (xsize); i++) {
            if (i <= ysize || i % ysize == 0 || (i + 1) % ysize == 0 || i >= ysize * xsize - ysize) {
                point[i] = 0;
                System.out.println(i);
            }else{
            point[i] = i;
            }
        }
        int[] dxpos = new int[xsize-2];
        int[] dypos = new int[ysize-2];
        fence = new LinkedList<line>();
        for (int i = 0; i < xsize-2; i++) {
            dxpos[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < ysize-2; i++) {
            dypos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(dxpos);// 2,4,5,6,10
        int[] dxlength = tolength(dxpos, xlength);// 2,2,1,1,4,5
        Arrays.sort(dypos);
        int[] dylength = tolength(dypos, ylength);
        for (int i=0; i< xsize ;i++){
			for( int j=i*ysize,k=0; k<ysize-1;j++,k++){
				fence.add(new line(j,j+1,dylength[k]));
			}
		}
		for (int i=0; i< ysize ;i++){
			for( int j=i,k=0; k<xsize-1;j+=ysize,k++){
				fence.add(new line(j,j+ysize,dxlength[k]));
			}
		}
        fence.sort(new Comparator<line>() {

            @Override
            public int compare(line o1, line o2) {
                // TODO Auto-generated method stub
                return o1.length - o2.length;
            }

        });
        int needtocut = (ysize-2) * (xsize-2);
        long cnt=0;
        while (needtocut > 0) {
            line temp = fence.pollLast();
            if (findfather(temp.a) == findfather(temp.b)) {
                fence.addFirst(temp);
            } else {
                merge(temp.a, temp.b);
                cnt+=temp.length;
                // System.out.println(" "+temp.a+" "+temp.b+" "+temp.length);
                needtocut--;
            }
        }
        long sum=(long)(xsize-2)*xlength+(long)(ysize-2)*ylength;
        System.out.println(sum-cnt);
    }

    private int findfather(int b) {
        if (b != point[b]) {
            point[b] = findfather(point[b]);
        }
        return point[b];
    }

    private void merge(int a, int b) {
        int tempa=findfather(a);
        int tempb=findfather(b);
        point[tempa]=tempb;
    }

    private int[] tolength(int[] dxpos, int flength) {
        int[] result = new int[dxpos.length + 1];
        result[0] = dxpos[0];
        for (int i = 1; i < dxpos.length; i++) {
            result[i] = dxpos[i] - dxpos[i - 1];
        }
        result[result.length - 1] = flength - dxpos[dxpos.length - 1];
        return result;
    }

    public static void main(String[] args) throws IOException {
        H01232 a = new H01232();
    }
}
