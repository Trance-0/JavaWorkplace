package C33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

//http://oj.leaplearner.com/problem/01281
public class H01281 {
    private int N;
    private int V;
    private int edgeSize;
    private LinkedList<int[]>[]tree;
    private boolean[]cowPos;
    private int[][] E;
    private int seed;
    public H01281() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());
        edgeSize=Integer.parseInt(st.nextToken());
        cowPos=new boolean[V];
        
        st=new StringTokenizer(br.readLine());
        seed=Integer.parseInt(st.nextToken());
        cowPos[seed]=true;

        for(int i=1;i<N;i++){
            st=new StringTokenizer(br.readLine());
            cowPos[Integer.parseInt(st.nextToken())-1]=true;
        }
        E=new int[edgeSize][3];
        for(int i=0;i<edgeSize;i++){
            st=new StringTokenizer(br.readLine());
            E[i][0]=Integer.parseInt(st.nextToken())-1;
            E[i][1]=Integer.parseInt(st.nextToken())-1;
            E[i][2]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(E,new Comparator<int[]>(){

            @Override
            public int compare(int[] o1, int[] o2) {
                // TODO Auto-generated method stub
                return o1[2]-o2[2];
            }
            
        });
        tree=new LinkedList[V];
        for (int j=0; j<V; j++){
    tree[j]=new LinkedList<int[]>();
    }
        tree[E[0][0]].add(E[0]);
        for(int i=0;i<V-1;i++){
            for(int[] j:E){
                if((tree[j[0]].isEmpty()&&!tree[j[1]].isEmpty())||(!tree[j[0]].isEmpty()&&tree[j[1]].isEmpty())){
                    tree[j[0]].add(j);
                    tree[j[1]].add(j);
                    break;
                }
            }
        }
        int sum=0;
        int distance=0;
        boolean[] isIngroup=new boolean[V];
        isIngroup[seed]=true;
        LinkedList<Integer>stack=new LinkedList<Integer>();
        stack.add(seed);
        //deep first search, return the value when reached an end and the end is a cow pos;
        while(!stack.isEmpty()){
            boolean isEnd=false;
            for(int[] i:tree[stack.peekLast()]){
                if(isIngroup[i[0]]==false||isIngroup[i[1]]==true){
                    stack.add(i[0]);
                    isIngroup[i[0]]=true;
                    distance+=i[2];
                }else if(isIngroup[i[0]]==true||isIngroup[i[1]]==false){
                    stack.add(i[1]);
                    isIngroup[i[1]]=true;
                    distance+=i[2];
                }
            }
            if(isEnd){
                if(cowPos[stack.peekLast()]){
                    sum+=distance;
                }
                stack.pollLast();
                distance=0;
            }
        }
    }
    public static void main(String[] args)throws IOException {
        H01281 a=new H01281();
    }
}
