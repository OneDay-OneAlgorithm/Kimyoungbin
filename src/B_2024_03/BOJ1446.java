package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1446 {
    static int N, D;
    static int[][] map; // 점 사이의 최소 길이
    static int[] dist; // 출발점(0)으로부터의 최소거리
    static boolean[] visited;
    static int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        D = Integer.parseInt(line_1[1]);
        map = new int[D+1][D+1];
        dist = new int[D+1];
        visited = new boolean[D+1];
        for(int i=0; i<=D; i++) {
            for(int j=0; j<=D; j++) {
                map[i][j] = INF;
                if(j-1==i) { // 인접한 점 사이에 길 존재
                    map[i][j] = 1;
                } else if(i==j) {
                    map[i][j] = 0;
                }
            }
        }
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int start = Integer.parseInt(line_N[0]);
            int end = Integer.parseInt(line_N[1]);
            int cost = Integer.parseInt(line_N[2]);
            if(end<=D) {
                map[start][end] = Math.min(cost, map[start][end]);
            }
        }
        for(int i=1; i<=D; i++) {
            dist[i] = map[0][i];
        }

        for(int i=0; i<D; i++) { // D번 반복
            int find = findMin(); // 1~D 중에 출발점으로부터 거리가 제일 짧은 경유점부터 검사
            for(int j=0; j<=D; j++) {
                dist[j] = Math.min(dist[find]+map[find][j], dist[j]); // 0~find최단거리+find~j거리와 기존 0~j최단거리 비교
            }
        }

        System.out.println(dist[D]);
    }

    private static int findMin() {
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for(int i=1; i<=D; i++) { // 방문하지 않은 가장 작은 dist[i]를 찾는 과정
            if(!visited[i] && min>dist[i]) {
                min = dist[i];
                minIdx = i;
            }
        }

        visited[minIdx] = true;
        return minIdx;
    }
}