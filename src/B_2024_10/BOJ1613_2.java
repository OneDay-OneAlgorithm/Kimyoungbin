package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// floyd-warshall로 풀어보기
// bfs와의 차이는 floyd로 방문가능한지 체크하냐, bfs로 체크하냐의 차이일 뿐
public class BOJ1613_2 {
    static int n, k, s;
    static boolean[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new boolean[n+1][n+1];
        for(int i=1; i<=n; i++) {
            dist[i][i] = true;
        }


        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            dist[s][e] = true;
        }

        // 미리 floyd로 거리정보 갱신
        floyd();

        s = Integer.parseInt(br.readLine());
        for(int i=0; i<s; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(dist[start][end]) {
                System.out.println(-1);
            } else if(dist[end][start]) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

    }

    // start에서 시작해서 end로 갈 수 있느냐
    private static void floyd() {
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(dist[i][k] && dist[k][j]) {
                        dist[i][j] = true;
                    }
                }
            }
        }
    }
}
