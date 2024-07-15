package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ27433 {
    static int N;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[21];
        dp[0] = 1;
        for(int i=1; i<=20; i++) {
            dp[i] = dp[i-1]*i;
        }
        System.out.println(dp[N]);
    }
}