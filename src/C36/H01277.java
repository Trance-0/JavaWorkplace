package C36;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import java.io.InputStreamReader;

public class H01277 {

public class ForwardStar{
    public class edge {
        private int to, weight, nexthead;
    }

    private int[] head;
    private edge[] stars;
    private int count;

    public ForwardStar(int size){
        head = new int[size];
        for (int i = 0; i < size; i++) {
            head[i] = -1;
        }
        stars = new edge[size];
        count = 0;
    }

    public void add(int from, int to, int weight) {
        edge tempEdge = new edge();
        tempEdge.to = to;
        tempEdge.weight = weight;
        tempEdge.nexthead = head[from];
        head[from] = count;
        stars[count] = tempEdge;
        count++;
    }

    public LinkedList<int[]> get(int from) {
        LinkedList<int[]> result = new LinkedList<int[]>();
        for (int i = head[from]; i != -1; i = stars[i].nexthead) {
            int[] temp = new int[3];
            temp[0] = from;
            temp[1] = stars[i].to;
            temp[2] = stars[i].weight;
            result.add(temp);
        }
        return result;
    }

}

    private int n;
    private int m;
    private int[] neighbor;
    private ForwardStar fs;
    public H01277() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        fs=new ForwardStar(m);
        neighbor=new int[5];
        st=new StringTokenizer(br.readLine());
        for (int i=0;i<5;i++){
            neighbor[i]=Integer.parseInt(st.nextToken())-1;
        }
        for (int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            fs.add(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
        }
        int[][]dis=new int[5][5];
        for (int i=0;i<5;i++){
            int[]temp=BellmanFord(neighbor[i], fs, n);
            // printarr(temp);
            for (int j=0;j<5;j++){
                dis[i][j]=temp[neighbor[j]];
            }
        } 
        // printarr(dis);
        
        int result=Integer.MAX_VALUE;
        for(int i=0;i<5;i++){
           
            for(int j=0;j<5;j++){
                if(j==i){
                    continue;
                }
                for(int k=0;k<5;k++){
                    if(k==i||k==j){
                        continue;
                    }
                    for(int l=0;l<5;l++){
                        if(l==i||l==j||l==k){
                            continue;
                        }
                        for(int o=0;o<5;o++){
                            if(o==i||o==j||o==k||o==l){
                                continue;
                            }
                            // System.out.println(i+""+j+""+k+""+l+""+o+":"+dis[i][j]+dis[j][k]+dis[k][l]+dis[l][o]);
                            result=Math.min(result,dis[i][j]+dis[j][k]+dis[k][l]+dis[l][o]);
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    public void printarr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] i : arr) {
            sb.append("[");
            for (int j : i) {
                sb.append(j);
                sb.append(",");
            }
            sb.append("]"+"\n");
        }
        System.out.println( sb.toString());
    }
    public void printarr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
    public void printarr(LinkedList<int[]> arr) {
        for (int i=0;i<arr.size();i++) {
            printarr(arr.get(i));
        }
    }
    public int[] BellmanFord(int start, ForwardStar E, int V) {
        int dis[] = new int[V];
        for (int i = 0; i < V; i++) {
            dis[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < V - 1; i++) {
            LinkedList<int[]>tempE=E.get(i);
            for (int j=0;j<tempE.size();j++) {
               int[] c=tempE.get(j);
                if (dis[c[0]] > dis[c[1]] + c[2] && dis[c[1]] != Integer.MAX_VALUE) {
                    dis[c[0]] = dis[c[1]] + c[2];
                }
            }
        }
        return dis;
    }
    public static void main(String[] args) throws IOException {
        H01277 a = new H01277();
    }
}
