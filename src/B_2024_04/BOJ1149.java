package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1149 {
    static int N;
    static int[][] arr;
    static int[][] dp;
    static final int R = 0, G = 1, B = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        dp = new int[N][3]; // R/G/B
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int red = Integer.parseInt(line_N[0]);
            int green = Integer.parseInt(line_N[1]);
            int blue = Integer.parseInt(line_N[2]);
            arr[i][R] = red;
            arr[i][G] = green;
            arr[i][B] = blue;
        }
        for(int i=0; i<3; i++) {
            dp[0][i] = arr[0][i];
        }

        //dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B])+arr[i][R];
        for(int i=1; i<N; i++) {
            dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B])+arr[i][R];
            dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B])+arr[i][G];
            dp[i][B] = Math.min(dp[i-1][R], dp[i-1][G])+arr[i][B];
        }

        System.out.println(Math.min(dp[N-1][R], Math.min(dp[N-1][G], dp[N-1][B])));
    }
}