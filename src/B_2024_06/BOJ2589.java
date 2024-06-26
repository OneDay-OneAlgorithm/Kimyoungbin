package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// bfs로 영역별로 최장거리를 구하고, 그 중에 최고값 구하기
// WA1) 육지로만 이동 가능
// WA2) 영역의 시작점을 어디로 잡느냐에 따라서 bfs의 결과가 달라짐 -> 모든 육지에 대해서 bfs시작점으로 잡아주어야 할듯. (N,M이 50이하이므로)
// WA3) nx ny 바꿔씀
public class BOJ2589 {
    static int N, M;
    static int[][] map; //0: 바다, 1: 육지
    static boolean[][] visited;
    static Queue<Info> queue;
    static int rst = 0;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split("");
            for(int j=0; j<M; j++) {
                map[i][j] = line_N[j].equals("W")?0:1;
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==1) { // 육지에서만 bfs
                    rst = Math.max(rst, bfs(j, i));
                }
            }
        }

        System.out.println(rst);
    }

    // 영역 내에서 최대거리 구하기
    private static int bfs(int x, int y) {
        visited = new boolean[N][M];
        queue = new LinkedList<>();
        queue.add(new Info(x, y, 0));
        visited[y][x] = true;
        int maxTime = 0;

        while(!queue.isEmpty()) {
            Info cur = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if(0<=nx && nx<M && 0<=ny && ny<N && !visited[ny][nx] && map[ny][nx]==1) { // WA1)
                    visited[ny][nx] = true;
                    maxTime = Math.max(maxTime, cur.time+1);
                    queue.add(new Info(nx, ny, cur.time+1)); // WA3) queue.add(new Info(ny, nx, cur.time+1));
                }
            }
        }

        return maxTime;
    }

    static class Info {
        int x;
        int y;
        int time;
        public Info(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}