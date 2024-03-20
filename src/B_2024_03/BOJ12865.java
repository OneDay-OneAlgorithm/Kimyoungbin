package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12865
{
    static int N, K; // 물건수, 최대무게
    static int[] W; // 물건 무게
    static int[] V; // 물건 가치
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        dp = new int[N][K+1]; // 1~i물건까지 고려하고 무게제한이 j일때 가질수있는 최대가치
        W = new int[N];
        V = new int[N];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            W[i] = Integer.parseInt(line_N[0]);
            V[i] = Integer.parseInt(line_N[1]);
        }

        // 초기화
        for(int i=0; i<K+1; i++) {
            dp[0][i] = i>=W[0]?V[0]:0;
        }
        for(int i=1; i<N; i++) {
            for(int j=0; j<=K; j++) {
                if(j-W[i]>=0) {
                    dp[i][j] = Math.max(dp[i-1][j-W[i]]+V[i], dp[i-1][j]); // 이번 물건을 넣는 경우, 넣지 않는 경우
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N-1][K]);
    }
}