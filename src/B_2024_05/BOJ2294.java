package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// n<=100, k<=10000이므로 dp[?]를 구하기 위해서는 dp[i]에 arr[j]을 더하는 모든 경우를 계산해본다.
// dp[i] = Math.min(dp[i], dp[i-arr[j]]+1)
// 추가로 k가 최대 10000이므로 10001 이상의 동전은 고려하지 않아도 됨.
// WA1) 불가능할 경우 -1 출력
public class BOJ2294 {
    static int n, k;
    static int[] arr, dp;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]); // n가지 종류 동전
        k = Integer.parseInt(line_1[1]); // k원 목표
        arr = new int[n];
        dp = new int[k+1];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.fill(dp, INF);
        Arrays.sort(arr);
        // dp 초기화
        for(int i=0; i<n; i++) {
            if(arr[i]<=k)
                dp[arr[i]] = 1;
        }

        // O(k*n)
        for(int i=1; i<=k; i++) {
            for(int j=0; j<n; j++) {
                if(i-arr[j]>0) {
                    dp[i] = Math.min(dp[i], dp[i-arr[j]]+1);
                }
            }
        }

//        printdp();
        System.out.println(dp[k]==INF?-1:dp[k]); // WA1
    }

    private static void printdp() {
        for (int i=1; i<=k; i++) {
            System.out.print(dp[i]+" ");
        }
        System.out.println();
    }
}