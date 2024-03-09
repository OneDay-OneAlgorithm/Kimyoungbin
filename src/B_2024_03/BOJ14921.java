package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ14921
{
    static int N;
    static int[] arr;
    static int l, r;
    static int minabs = Integer.MAX_VALUE;
    static boolean isPlus;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
//        Arrays.sort(arr);
        l = 0; r = N-1;

        while(l<r) {
            int sum = arr[l]+arr[r];
            if(sum==0) {
                minabs = 0;
                break;
            } else if(sum<0) {
                l++;
                if(minabs > Math.abs(sum)) {
                    minabs = Math.abs(sum);
                    isPlus = false;
                }
            } else {
                r--;
                if(minabs > Math.abs(sum)) {
                    minabs = Math.abs(sum);
                    isPlus = true;
                }
            }
        }

        System.out.println(isPlus?minabs:-minabs);
    }
}

