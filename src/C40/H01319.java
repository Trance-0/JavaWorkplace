package C40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H01319 {
    private int n;
    private int[] array;
    private int first,last;
    public H01319() throws NumberFormatException, IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        int lastElement=0;
        array=new int[n];
        for(int i=0;i<n;i++){
            int newElement=Integer.parseInt(br.readLine());
            array[i]=newElement-lastElement;
            lastElement=newElement;
            if(i==0){
                first=newElement;
            }if(i==n-1){
last=newElement;
        }

        }
        printarr(array);
        long negative=GetNegative(array);
        long positive=GetPositive(array);
        System.out.println(Math.max(negative, positive));
        System.out.println(Math.abs(first-last)+1);
    }
    private long GetPositive(int[] array2) {
        long result=0;
       for(int i =1;i<array2.length;i++){
        if(array2[i]>0){
result+=array2[i];
        }
        
       }
       return result;
    }
    private long GetNegative(int[] array2) {
        long result=0;
        for(int i =1;i<array2.length;i++){
            if(array2[i]<0){
    result-=array2[i];
            }
            
           }
        return result;
    }
    public void printarr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args)  throws NumberFormatException, IOException{
        H01319 a=new H01319();
    }
}
