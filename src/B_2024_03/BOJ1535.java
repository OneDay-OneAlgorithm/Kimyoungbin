package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1535
{
    static int N;
    static int[] L, J; // 피로도, 기쁨
    static int[][] dp; // dp[i][j]: 0~i번째 사람까지 고려했을 때 피로도가 j일때 얻을 수 있는 최대 기쁨
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        L = new int[N+1];
        J = new int[N+1];
        String[] 피로도 = br.readLine().split(" ");
        String[] 기쁨 = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            L[i] = Integer.parseInt(피로도[i-1]);
            J[i] = Integer.parseInt(기쁨[i-1]);
        }
        dp = new int[N+1][100];

        for(int i=1; i<=N; i++) { // 1~i번째 사람까지 고려했을 때
            for(int j=0; j<100; j++) { // 피로도가 j일때
                if(j+L[i]<100) { // 피로도 조건을 만족
                    dp[i][j] = Math.max(dp[i-1][j+L[i]]+J[i], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
                max = Math.max(dp[i][j], max);
            }
        }

        System.out.println(max);
    }

}

