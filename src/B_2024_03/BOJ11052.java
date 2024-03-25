package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11052 {
    static int N;
    static int[] arr; // 카드 i개짜리 팩의 가격
    static int[] dp; // 카드 i개를 살때 최대한 많이 쓸 수 있는 돈
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1];
        String[] line_2 = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(line_2[i-1]);
        }

        for(int i=1; i<=N; i++) {
            dp[i] = arr[i];
            for(int j=1; i-j>=0; j++) {
                dp[i] = Math.max(dp[i], dp[i-j]+arr[j]); // 최대한 비싸게 쓸수있는돈 갱신
            }
        }

        System.out.println(dp[N]);
    }
}