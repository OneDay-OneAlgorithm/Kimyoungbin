package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 배낭 냅색처럼 메모리를 기준으로 dp배열 선언시 100*1천만으로 MLE가 난다.
// search) 비용을 기준으로 생각
// WA1) 비용이 0일경우 고려
public class BOJ7579 {
    static int N, M;
    static int[] memory; // 앱이 차지하는 메모리
    static int[] cost; // 앱 비활성화후 실행할 경우 비용
    static int[][] dp; // dp[i][j]: 0~i까지 앱을 실행해서 j이하 비용을 소비했을때 확보할 수 있는 메모리
    static int minCost = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        memory = new int[N+1];
        cost = new int[N+1];
        String[] line_2 = br.readLine().split(" ");
        String[] line_3 = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            memory[i] = Integer.parseInt(line_2[i-1]);
            cost[i] = Integer.parseInt(line_3[i-1]);
        }

        dp = new int[N+1][10001];

        for(int i=1; i<=N; i++) {
            for(int j=0; j<=10000; j++) { // WA1 j=1 -> j=0
                if(j-cost[i]<0) {
                    dp[i][j] = dp[i-1][j]; // OutOfIndex 예방
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+memory[i]);
                }
                if(dp[i][j]>=M) {
                    minCost = Math.min(minCost, j);
                }
            }
        }

        System.out.println(minCost);
    }
}