package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dp[i] = dp[i-arr[i]]+1;
// search)knapsack
public class BOJ9084 {
    static int N, M;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());

            dp = new int[M+1]; //dp[i]: i원을 만드는 경우의 수
            dp[0] = 1;
            for(int j=0; j<N; j++) { //동전
                for(int i=0; i<=M; i++) { //금액
                    if(i-arr[j]>=0) {
                        dp[i] += dp[i-arr[j]];
                    }
                }
            }

            System.out.println(dp[M]);
        }
    }
}