package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// floyd -> O(N^3)==400^3
public class BOJ1956 {
    static int V, E;
    static int[][] dist;
    static final int INF = 0x3f3f3f3f;
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V+1][V+1];
        for(int i=1; i<=V; i++) {
            for(int j=1; j<=V; j++) {
                dist[i][j] = INF;
            }
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = c;
        }

        for(int i=1; i<=V; i++) { // 경유
            for(int j=1; j<=V; j++) {
                for(int k=1; k<=V; k++) {
                    dist[j][k] = Math.min(dist[j][k], dist[j][i]+dist[i][k]);
                }
            }
        }

        // 시작점에서 찍고 돌아오기
        for(int i=1; i<=V; i++) { // 시작점
            for(int j=1; j<=V; j++) {
                rst = Math.min(rst, dist[i][j]+dist[j][i]);
            }
        }

        System.out.println(rst>=INF?-1:rst);
    }
}