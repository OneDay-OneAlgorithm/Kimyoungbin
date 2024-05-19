package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// LIS dp랑 조금 다르게 N^2 반복해서 최대값 구해주면 됨
public class BOJ11055 {
    static int N, max = Integer.MIN_VALUE;
    static int[] arr, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        dp = arr.clone();

        for(int i=0; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(arr[i]>arr[j]) {
                    dp[i] = Math.max(dp[j]+arr[i], dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}