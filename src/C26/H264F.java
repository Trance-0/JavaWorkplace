package C26;

import java.util.Arrays;
import java.util.Comparator;

public class H264F {
    public static void main(String[] args) {
        int[][] arr = new int[3][3];
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        arr[1]={1,23,3};
    }
}
