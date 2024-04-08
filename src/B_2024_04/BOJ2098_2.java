package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// dp[i][visited] = Math.min(dp[i][visited | (1<<j)] + W[i][j], dp[i][visited])
public class BOJ2098_2 {
    static int N;
    static int[][] W, dp;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][(1<<N)-1];
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                W[i][j] = Integer.parseInt(line_N[j]);
            }
        }
        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));
    }

    private static int dfs(int cur, int visited) {
        if(visited==(1<<N)-1) {
            if(W[cur][0]==0) return INF;
            return W[cur][0];
        }

        if(dp[cur][visited]!=-1) {
            return dp[cur][visited];
        }
        dp[cur][visited] = INF;

        for(int i=0; i<N; i++) {
            if(W[cur][i]!=0 && (visited & (1<<i))==0) {
                dp[cur][visited] = Math.min(dfs(i, visited | (1<<i))+W[cur][i], dp[cur][visited]);
            }
        }
        return dp[cur][visited];
    }
}