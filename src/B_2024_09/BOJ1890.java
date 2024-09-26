package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 방향성 존재 - bfs 가능할거같은데
// MLE1) 방향성이 오른쪽, 아래로만 존재하므로 굳이 BFS가 아니라 for문으로 해결
public class BOJ1890 {
    static int N;
    static int[][] arr;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new long[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j]==0) continue;
                if(j+arr[i][j]<N) {
                    dp[i][j+arr[i][j]] += dp[i][j];
                }
                if(i+arr[i][j]<N) {
                    dp[i+arr[i][j]][j] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N-1][N-1]);

    }
}
