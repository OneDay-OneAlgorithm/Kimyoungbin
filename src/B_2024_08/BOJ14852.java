package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// search) https://bayabashut.tistory.com/69
// WA) 베열 자체를 long으로 해야 했음
public class BOJ14852 {
    static int N;
    static int[] dp; // dp[i] = dp[i-2]*3+dp[i-1]*2+2;
    static int[] sum;
    static final int STD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N==1) {
            System.out.println(2);
            return;
        } else if(N==2) {
            System.out.println(7);
            return;
        }
        dp = new int[N+1];
        sum = new int[N+1];

        dp[0] = 1;
        sum[0] = 1;

        dp[1] = dp[0]*2;
        sum[1] = sum[0]+dp[1];

        dp[2] = dp[1]*2+dp[0]*3;
        sum[2] = sum[1]+dp[2];

        // N=3부터 특이케이스 존재
        dp[3] = dp[2]*2+dp[1]*3+dp[0]*2;
        sum[3] = sum[2]+dp[3];

        for(int i=4; i<=N; i++) {
            dp[i] = ((dp[i-1]*2)%STD+(dp[i-2]*3)%STD+(sum[i-3]*2)%STD)%STD;
            sum[i] = (sum[i-1]+dp[i])%STD;
        }
        System.out.println(dp[N]);
    }
}