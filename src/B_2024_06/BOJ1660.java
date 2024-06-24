package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N이 사면체보다 크거나 같으면 빼주기 -> WR (https://www.acmicpc.net/board/view/58850)
// search) DP
public class BOJ1660 {
    static int N;
    static int[] arr, sum, dp;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[800];
        sum = new int[800];
        dp = new int[300_001];
        arr[0] = 1;
        sum[0] = 1;
        int add = 2;
        for(int i=1; i<arr.length; i++) {
            arr[i] += arr[i-1]+(add++);
            sum[i] += sum[i-1]+arr[i];
        }
        for(int i=1; i<=300000; i++) {
            dp[i] = INF;
        }
        dp[0] = 0;
        dp[1] = 1;

        for(int i=1; i<=300000; i++) {
            for(int j=maxIdxInSum(i); j>=0; j--) {
                dp[i] = Math.min(dp[i-sum[j]]+1, dp[i]);
            }
        }

        System.out.println(dp[N]);
    }

    // n은 자연수
    private static int maxIdxInSum(int n) {
        for(int i=799; i>=0; i--) {
            if(sum[i]<=n) return i;
        }
        return -1;
    }
}