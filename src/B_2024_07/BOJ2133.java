package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// dp[i] = dp[i-2]*3+dp[i-4]*2
// WA1) dp[6]짜리도 존재
// WA2) 짝수마다 추가적인 테케가 있는듯? 2개씩
public class BOJ2133 {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[31];

        dp[2] = 3;
        for(int i=4; i<=30; i+=2) {
            dp[i] = dp[i-2]*3;
            for(int j=4; i-j>0; j++) {
                dp[i]+=dp[i-j]*2;
            }
            dp[i]+=2;
        }

        System.out.println(dp[N]);
    }
}