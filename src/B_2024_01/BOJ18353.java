package B_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ18353
{
    static int N;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        dp = new int[N];

        for(int i=0; i<N; i++) {
            int maxIdx = -1;
            int max = Integer.MIN_VALUE;
            for(int j=i-1; j>=0; j--) {
                if(arr[j]>arr[i] && dp[j]>max) {
                    maxIdx = j;
                    max = dp[j];
                }
            }
            if(maxIdx==-1) {
                dp[i] = 1;
            } else {
                dp[i] = dp[maxIdx]+1;
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(N-max);
    }
}