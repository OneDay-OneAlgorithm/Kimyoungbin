package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {
    static int N, M;
    static int[][] arr; // 0:빈칸, 1:벽, 2:비활성 바이러스, 3: 활성 바이러스
    static boolean[][] visited;
    static int rst = Integer.MAX_VALUE;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int emptyNum = 0; // 채워야 하는 빈칸의 수
    static Queue<Node> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) emptyNum++; // 빈 칸 세기
            }
        }

        activateVirus(0, 0);

        System.out.println(rst == Integer.MAX_VALUE ? -1 : rst);
    }

    // dfs를 통해 바이러스 중에서 활성 바이러스 M개 고르기
    private static void activateVirus(int cur, int start) {
        // M개의 활성 바이러스가 설정되면 BFS 실행
        if (cur == M) {
            int time = getTime();
            rst = Math.min(rst, time);
//            System.out.println("time = " + time);
            return;
        }

        for(int i=start; i<N*N; i++) {
            if(arr[i/N][i%N]==2) {
                arr[i/N][i%N] = 3;
                activateVirus(cur+1, i+1);
                arr[i/N][i%N] = 2;
            }
        }
    }

    // 해당 배열에서 바이러스가 퍼질 수 있는 최소 시간
    private static int getTime() {
        queue = new LinkedList<>();
        visited = new boolean[N][N];
        int visitNum = 0; // 빈칸이었던 곳을 감염시킨 횟수

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 3) { // 활성 바이러스
                    queue.add(new Node(j, i, 0)); // 좌표 및 시간 초기화
                    visited[i][j] = true;
                }
            }
        }

        int time = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if(arr[cur.y][cur.x]==0) {
                time = Math.max(time, cur.time);
            }
//            System.out.println("cur.x = " + cur.x + ", cur.y = "+cur.y + ", time = " + cur.time);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[ny][nx] && arr[ny][nx] != 1) {
                    visited[ny][nx] = true;

                    if(arr[ny][nx]==0) {
                        visitNum++;
                    }

                    queue.add(new Node(nx, ny, cur.time+1));

                }
            }
        }

        return visitNum==emptyNum ? time : Integer.MAX_VALUE; // 모든 빈 칸이 감염되었는지 확인
    }

    static class Node {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
