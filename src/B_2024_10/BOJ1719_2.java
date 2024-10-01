package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// floyd로도 풀어보기 (rst search)
public class BOJ1719_2 {
    static int n, m;
    static boolean[] visited;
    static int[][] dist;
    static final int INF = 0x3f3f3f3f;
    static int[][] rst; // rst[i][j]: i에서 j로 가기 위해서 가장 먼저 가야 하는 집하장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        rst = new int[n+1][n+1];
        dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                rst[i][j] = j;
            }
            rst[i][i] = 0;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            dist[s][e] = w;
            dist[e][s] = w;
        }

        // floyd
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(dist[i][j] > dist[i][k]+dist[k][j]) {
                        dist[i][j] = dist[i][k]+dist[k][j];
                        rst[i][j] = rst[i][k];
                    }
                }
            }
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(rst[i][j]==0) {
                    System.out.print("- ");
                }
                else System.out.print(rst[i][j]+" ");
            }
            System.out.println();
        }

    }
}
