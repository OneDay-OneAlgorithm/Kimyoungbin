package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백트래킹으로 풀기에는 N이 너무 크다. 출석을 묶거나,, DP
// 지각은 1회까지만 허용되므로, 출석 개수만큼 지각 case가 추가될 수 있다.
public class BOJ1563 {
    static int N;
    static int[][][] dp;
    static int DIV = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2][3];
        dp[1][0][0] = 1; // O
        dp[1][0][1] = 1; // A
        dp[1][0][2] = 0;
        dp[1][1][0] = 1; // L
        dp[1][1][1] = 0;
        dp[1][1][2] = 0;

        for(int i=2; i<=N; i++) {
            dp[i][0][0] = (dp[i-1][0][0]+dp[i-1][0][1]+dp[i-1][0][2])%DIV; // OO, AO, AAO
            dp[i][0][1] = dp[i-1][0][0]; // OA
            dp[i][0][2] = dp[i-1][0][1]; // AA
            dp[i][1][0] = (dp[i-1][1][0]+dp[i-1][1][1]+dp[i-1][1][2]+dp[i-1][0][0]+dp[i-1][0][1]+dp[i-1][0][2])%DIV; // OO, AO, AAO, ~L
            dp[i][1][1] = dp[i-1][1][0];
            dp[i][1][2] = dp[i-1][1][1];
        }

        System.out.println((dp[N][0][0]+dp[N][0][1]+dp[N][0][2]+dp[N][1][0]+dp[N][1][1]+dp[N][1][2])%DIV);
    }
}