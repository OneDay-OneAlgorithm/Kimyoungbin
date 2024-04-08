package B_2024_04;

import java.io.*;
import java.util.*;

// TSP = dp+dfs(https://eckrin.tistory.com/189)
// dp[i][visited]: 현재 있는 도시가 i이고 이미 방문한 도시들의 집합이 visited 일때, 방문하지 않은 나머지 도시들을 모두 방문한 뒤 출발 도시로 돌아올 때 드는 최소 비용.
// dp[i][visited] = min(dp[next][visited | (1 << next)] + graph[i][j]), dp[i][visited])
public class BOJ2098 {
    static int N;
    static final int INF = 0x3f3f3f3f;
    static int[][] W, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        // 초기화
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                W[i][j] = Integer.parseInt(line_N[j]);
            }
        }
        dp = new int[N][(1<<N)-1]; // 2^n번 자리가 n번 도시를 방문했는지 여부를 나타낸다
        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1); // 초기화
        }

        bw.write(dfs(0, 1)+"\n"); // 어떤 도시부터 순회해도 답을 구할 수 있다
        bw.flush();
        br.close();
    }

    private static int dfs(int cur, int visited) {
        if(visited == (1<<N)-1) { // 모든 도시를 방문한 경우
            // 출발도시로 가는 경로가 존재해야 돌아갈 수 있음 (존재하지 않으면 이 방법은 불가능)
            if(W[cur][0] == 0) return INF;
            return W[cur][0];
        }

        // 초기값이 아닌 경우 - 중복탐색 방지
        if(dp[cur][visited] != -1) {
            return dp[cur][visited];
        }
        dp[cur][visited] = INF;

        for(int i=0; i<N; i++) {
            // 현재 노드에서 아직 방문하지 않는 i번 도시 가는 경로가 있는 경우
            if((visited & (1<<i))==0 && W[cur][i]!=0) {
                // 방문해야하는 도시로 가는 최소 비용
                dp[cur][visited] = Math.min(dfs(i, visited | (1 << i)) + W[cur][i], dp[cur][visited]);
            }
        }
        return dp[cur][visited];
    }
}