package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9656
{
    static int N;
    static int[] dp; // 가져가는 횟수 dp[n] = dp[n-1] || dp[n-3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        for(int i=1; i<=N; i++) {
            dp[i] = i;
        }
        for(int i=4; i<=N; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-3])+1;
        }
        String ans = dp[N]%2==0?"SK":"CY";
        System.out.println(ans);
    }
}

