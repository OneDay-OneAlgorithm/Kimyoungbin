package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 가중치+방향성 연결 그래프
// n=100이므로 floyd(n^3) 사용
// dist[i][j] = min(dist[i][j], map[i][k]+map[k][j])
// WA) i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다. -> INF출력됨
public class BOJ11404 {
    static int n, m;
    static int[][] dist;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i==j) {
                    dist[i][j] = 0;
                }
                else {
                    dist[i][j] = INF;
                }
            }
        }
        for(int i=0; i<m; i++) {
            String[] line_m = br.readLine().split(" ");
            int start = Integer.parseInt(line_m[0]);
            int end = Integer.parseInt(line_m[1]);
            int cost = Integer.parseInt(line_m[2]);
            dist[start][end] = Math.min(dist[start][end], cost);
        }

        floyd();

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(dist[i][j]==INF)
                    System.out.print(0+" ");
                else
                    System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void floyd() {
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }
    }
}