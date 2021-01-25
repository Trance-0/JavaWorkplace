package SecondContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class S1 {
    private class path {
        private ArrayList<Integer> passed;

        public path(int init) {
            passed = new ArrayList<Integer>();
            passed.add(init);
        }
    }

    private int numofcows;
    private int numoflogs;
    private int[] cows;
    private path[] paths;

    public S1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numofcows = Integer.parseInt(st.nextToken());
        numoflogs = Integer.parseInt(st.nextToken());
        cows = new int[numofcows];
        paths=new path[numofcows];
        for (int i = 0; i < numofcows; i++) {
            cows[i] = i;
            paths[i]=new path(i);
        }
        for(int i=0;i<numoflogs;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken())-1;
            int b=Integer.parseInt(st.nextToken())-1;
            swap(a,b);
        }
        int[] alreadycounted=new int[numofcows];
        for(int i=0;i<numofcows;i++){
            if(alreadycounted[i]!=0){
                continue;
            }else{
                ArrayList<Integer>cango=new ArrayList<Integer>();
                cango.add(i);
                int egt=cows[i];
                while(i!=egt){
                    cango.add(egt);
                    egt=cows[egt];
                }
                boolean[] uniquepos=new boolean[numofcows];
                for(int pos:cango){
                    for(int subpos:paths[pos].passed){
                        uniquepos[subpos]=true;
                    }
                }
                int unique=countoftrue(uniquepos);
                for(int pos:cango){
                    alreadycounted[pos]=unique;
                }
            }
        }
        for(int i:alreadycounted){
            System.out.println(i);
        }
    }

    private int countoftrue(boolean[] uniquepos) {
        int result = 0;
        for (boolean i : uniquepos) {
            if (i) {
                result++;
            }
        }
        return result;
    }

    private void swap(int a, int b) {
        int temp = cows[a];
        cows[a] = cows[b];
        cows[b] = temp;
        paths[cows[a]].passed.add(b);
        paths[cows[b]].passed.add(a);
    }

    public static void main(String[] args) throws IOException {
        S1 a = new S1();
    }
}
