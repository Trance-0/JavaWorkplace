
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

class H01265 {
    private class togo {
        private int start;
        private LinkedList<Integer> gos;
        private LinkedList<Integer> distance;

        public togo(int s) {
            start = s;
            gos = new LinkedList<Integer>();
            distance = new LinkedList<Integer>();
        }

        public void add(int go, int length) {
            gos.add(go);
            distance.add(length);
        }
    }

    private int linelength;
    private int start;
    private boolean[] checked;
    private togo[] paths;
    private int[] optlength;

    public H01265() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        linelength = Integer.parseInt(st.nextToken());
        paths = new togo[1000];
        checked = new boolean[1000];
        for (int i = 0; i < 1000; i++) {
            paths[i] = new togo(i);
        }
        optlength = new int[1000];
        for (int i = 0; i < linelength; i++) {
            st = new StringTokenizer(br.readLine());
            int tempa = Integer.parseInt(st.nextToken());
            int tempb = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            paths[tempa].add(tempb, dis);
            paths[tempb].add(tempa, dis);
        }
        while (check(start)) {
            int tempstartsize = optlength[paths[start].start];
            for (int i = 0; i < paths[start].gos.size(); i++) {
                int templength = paths[start].distance.get(i);
                int tempend = paths[start].gos.get(i);
                optlength[tempend] = Math.min(optlength[tempend], tempstartsize + templength);
            }
            start = findmin();
        }
        System.out.println(optlength[1]);
    }

    private boolean check(int start) {
        if (start < 0) {
            return false;
        }
        for (int i : paths[start].gos) {
            if (!checked[i]) {
                return true;
            }
        }
        return false;
    }

    private int findmin() {
        int smallestele = -1;
        int smallestsize = 2000;
        for (int i = 0; i < 1000; i++) {
            if (optlength[i] != 0 && !checked[i]) {
                if (optlength[i] < smallestsize) {
                    smallestele = i;
                    smallestsize = optlength[i];
                }
            }
        }
        return smallestele;
    }

    public static void main(String[] args) throws IOException {
        H01265 a = new H01265();
    }
}