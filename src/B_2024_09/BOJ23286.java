package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가중치의 총 합이 아니라, 가중치의 최소값
// floyd 기반 같은데
// WA1) INIT을 -1 -> INF로 변경, 분기문 제거
public class BOJ23286 {
    static int N, M, T;
//    static LinkedList<Info>[] list;
    static int[][] dist;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

//        list = new LinkedList[N+1];
//        for(int i=1; i<=N; i++) {
//            list[i] = new LinkedList<>();
//        }
        dist = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i!=j) dist[i][j] = INF;
                else dist[i][j] = 0;
            }
        }

        // 간선정보 입력 받기
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            dist[s][e] = Math.min(dist[s][e], w);
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    dist[i][j] = Math.min(dist[i][j], Math.max(dist[i][k], dist[k][j]));
                }
            }
        }


        // input
        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dist[s][e]== INF ?-1:dist[s][e]).append("\n");
        }

        System.out.println(sb);

    }

    static class Info {
        int end;
        int w;
        public Info(int end, int w) {
            this.end = end;
            this.w = w;
        }
    }
}
