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
            if (i <= xsize || i % xsize == 0 || i % xsize == 1 || i >= xsize * ysize - xsize) {
                continue;
            }
            point[i] = i;
        }
        xsize -= 2;
        ysize -= 2;
        int[] dxpos = new int[xsize];
        int[] dypos = new int[ysize];
        fence = new LinkedList<line>();
        for (int i = 0; i < xsize; i++) {
            dxpos[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < ysize; i++) {
            dypos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(dxpos);// 2,4,5,6,10
        int[] dxlength = tolength(dxpos, xlength);// 2,2,1,1,4,5
        Arrays.sort(dypos);
        int[] dylength = tolength(dxpos, ylength);
        for (int i = 0; i < xsize; i++) { // parallel line
            for (int j = 0; j < ysize + 1; j++) {
                fence.add(new line((i + 1) * xsize + j - 1, (i + 1) * xsize + j, dylength[j]));
            }
        }
        for (int i = 0; i < ysize; i++) {
            for (int j = 0; j < xsize + 1; j++) {
                fence.add(new line(i + 1 + (ysize + 2) * j, i + 1 + (ysize + 2) * (j + 1), dxlength[j]));
            }
        }
        fence.sort(new Comparator<line>() {

            @Override
            public int compare(line o1, line o2) {
                // TODO Auto-generated method stub
                return o1.length - o2.length;
            }

        });
        int needtocut = ysize*xsize;
        while (needtocut > 0) {
            line temp = fence.pollLast();
            if (findfather(temp.a) == findfather(temp.b)) {
                fence.addFirst(temp);
            } else {
                merge(temp.a, temp.b);
                needtocut--;
            }
            System.out.println(needtocut);
        }
        int sum = 0;
        for (line x : fence) {
            sum += x.length;
        }
        System.out.println(sum);
    }


    private int findfather(int b) {
        if (point[b] == b) {
            return b;
        }
        point[b] = findfather(point[b]);
        return point[b];
    }

    private void merge(int a, int b) {
        point[findfather(a)] = point[findfather(b)];
    }

    private int[] tolength(int[] dxpos, int flength) {
        int[] result = new int[dxpos.length + 1];
        result[0] = dxpos[0];
        for (int i = 1; i < dxpos.length; i++) {
            result[i] = dxpos[i] - dxpos[i - 1];
        }
        result[result.length-1] = flength - dxpos[dxpos.length - 1];
        return result;
    }

    public static void main(String[] args) throws IOException {
        H01232 a = new H01232();
    }
}
