package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시작점에 따라서 dp를 분리
public class BOJ17404 {
    static int N;
    static int[][] arr;
    static int[][][] dp;
    static final int R = 0, G = 1, B = 2;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        dp = new int[3][N][3]; // R/G/B
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int red = Integer.parseInt(line_N[0]);
            int green = Integer.parseInt(line_N[1]);
            int blue = Integer.parseInt(line_N[2]);
            arr[i][R] = red;
            arr[i][G] = green;
            arr[i][B] = blue;
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<3; j++) {
                dp[j][i][R] = INF;
                dp[j][i][G] = INF;
                dp[j][i][B] = INF;
            }
        }
        dp[R][0][R] = arr[0][R];
        dp[G][0][G] = arr[0][G];
        dp[B][0][B] = arr[0][B];

        //dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B])+arr[i][R];
        for(int i=1; i<N; i++) {
            for(int j=0; j<3; j++) { // 시작점에 따라 분리
                dp[j][i][R] = Math.min(dp[j][i-1][G], dp[j][i-1][B]) + arr[i][R];
                dp[j][i][G] = Math.min(dp[j][i-1][R], dp[j][i-1][B]) + arr[i][G];
                dp[j][i][B] = Math.min(dp[j][i-1][R], dp[j][i-1][G]) + arr[i][B];
            }
        }

        // 첫번째 집이 R인경우
        int firstR = Math.min(dp[R][N-1][G], dp[R][N-1][B]);
        int firstG = Math.min(dp[G][N-1][R], dp[G][N-1][B]);
        int firstB = Math.min(dp[B][N-1][R], dp[B][N-1][G]);

        System.out.println(Math.min(firstR, Math.min(firstG, firstB)));
    }
}