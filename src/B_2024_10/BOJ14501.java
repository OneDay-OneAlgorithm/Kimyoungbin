package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501 {
    static int N;
    static int[] T, P, dp; // dp[i] i일차에 최대한 벌수있는 돈
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        dp = new int[N+1];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            if(i+T[i]<=N) {
                dp[i+T[i]] = Math.max(dp[i+T[i]], dp[i]+P[i]);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
        }

        System.out.println(dp[N]);
    }
}