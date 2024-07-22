package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2^50은 불가능
// WA1) search - 1차원 배열로 하면 정보가 덮어씌워질 수 있음 (예제 - 2 1 4/n 1 2)
public class BOJ1495 {
    static int N, S, M;
    static int[] V;
    static boolean[][] dp; // dp[i][j]: i회차에 볼륨 j를 연주할 수 있는지
    static int rst = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 곡의 개수
        S = Integer.parseInt(line_1[1]); // 시작 볼륨
        M = Integer.parseInt(line_1[2]); // 가능한 최대 볼륨
        V = new int[N+1];
        String[] line_2 = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            V[i] = Integer.parseInt(line_2[i-1]);
        }
        dp = new boolean[N+1][M+1];
        dp[0][S] = true;

        for(int i=1; i<=N; i++) { // 회차
            boolean flag = false;
            for(int j=0; j<=M; j++) { // dp 검사
                if(dp[i-1][j]) {
                    if(j-V[i]>=0) {
                        dp[i][j-V[i]] = true;
                        flag = true;
                    }
                    if(j+V[i]<=M) {
                        dp[i][j+V[i]] = true;
                        flag = true;
                    }
                }
            }
            if(!flag) {
                System.out.println(-1);
                return;
            }
        }

        for(int i=0; i<=M; i++) {
            if(dp[N][i]) {
                rst = Math.max(rst, i);
            }
        }
        System.out.println(rst);
    }
}