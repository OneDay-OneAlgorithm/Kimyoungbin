package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// bfs, BOJ2206 유사문제
public class BOJ14923 {
    static int N, M;
    static Node start, end;
    static int[][] arr; // 초기 미로의 상태
    static int[][][] dp; // 해당 좌표까지 가는데 걸리는 최단시간 - [0]: 벽 부수지 않고, [1]: 벽 부수고 이동
    static Queue<Info> queue;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        arr = new int[N][M];
        dp = new int[N][M][2];

        String[] line_2 = br.readLine().split(" ");
        start = new Node(Integer.parseInt(line_2[1])-1, Integer.parseInt(line_2[0])-1);
        String[] line_3 = br.readLine().split(" ");
        end = new Node(Integer.parseInt(line_3[1])-1, Integer.parseInt(line_3[0])-1);
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(line_N[j]);
                for(int k=0; k<2; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        bfs();
        if(dp[end.y][end.x][0]==INF && dp[end.y][end.x][1]==INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(dp[end.y][end.x][0], dp[end.y][end.x][1]));
        }
//        printDP();
    }

    private static void bfs() {
        queue = new LinkedList<>();
        queue.add(new Info(start, false));
        dp[start.y][start.x][0] = 0;
        dp[start.y][start.x][1] = 0;

        while(!queue.isEmpty()) {
            Info cur = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.node.x+dx[i];
                int ny = cur.node.y+dy[i];

                if(0<=nx && nx<M && 0<=ny && ny<N) {
                    if(arr[ny][nx]==0) { // 벽을 부술 필요가 없는 경우
                        if(!cur.crash && dp[ny][nx][0]==INF) {
                            dp[ny][nx][0] = dp[cur.node.y][cur.node.x][0]+1;
                            queue.add(new Info(new Node(nx, ny), false));
                        } else if(cur.crash && dp[ny][nx][1]==INF) {
                            dp[ny][nx][1] = dp[cur.node.y][cur.node.x][1]+1;
                            queue.add(new Info(new Node(nx, ny), true));
                        }
                    } else { // 벽을 부수어야 하는 경우 -> 아직 기회가 남아있어야 함
                        if(!cur.crash && dp[ny][nx][1]==INF) {
                            dp[ny][nx][1] = dp[cur.node.y][cur.node.x][0]+1;
                            queue.add(new Info(new Node(nx, ny), true));
                        }
                    }
                }
            }
        }
    }

    private static void printDP() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(dp[i][j][0]+" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(dp[i][j][1]+" ");
            }
            System.out.println();
        }
    }

    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Info {
        Node node;
        boolean crash;
        public Info(Node node, boolean crash) {
            this.node = node;
            this.crash = crash;
        }
    }
}
