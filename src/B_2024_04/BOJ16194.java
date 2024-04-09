package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16194 {
    static int N;
    static int[] P; // 카드 i개가 묶여있는 세트 가격
    static int[] dp; // 카드 i개를 샀을때 최소 가격
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N+1];
        String[] line_2 = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            P[i] = Integer.parseInt(line_2[i-1]);
        }
        dp = new int[N+1];

        dp[0] = 0;
        for(int i=1; i<=N; i++) {
            dp[i] = P[i];
            for(int j=1; j<=N; j++) {
                if(i-j>=0)
                    dp[i] = Math.min(dp[i], dp[i-j]+P[j]);
            }
        }

        System.out.println(dp[N]);
    }
}