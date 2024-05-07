package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 거리중요X, 부수어야 하는 벽의 개수 구하기
// BFS?
public class BOJ1261 {
    static int N, M;
    static int[][] maze, crack; // NxM
    static final int INF = 0x3f3f3f3f;
    static Queue<Node> queue = new LinkedList<>();
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        M = Integer.parseInt(line_1[0]);
        N = Integer.parseInt(line_1[1]);
        maze = new int[N+1][M+1]; // 미로 상태
        crack = new int[N+1][M+1]; // (1,1)부터 (i,j)까지 오기위해서 부수어야 하는 벽의 최소개수

        // 미로, 벽정보 초기화
        for(int i=1; i<=N; i++) {
            String[] line_N = br.readLine().split("");
            for(int j=1; j<=M; j++) {
                maze[i][j] = Integer.parseInt(line_N[j-1]);
                crack[i][j] = INF;
            }
        }

        // bfs
        crack[1][1] = 0;
        queue.add(new Node(1, 1, 0));
        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if(1<=nx && nx<=M && 1<=ny && ny<=N) {
                    int nCrack = maze[ny][nx]==1?cur.crack+1:cur.crack;
                    if(crack[ny][nx]>nCrack) {
                        crack[ny][nx] = nCrack;
                        queue.add(new Node(nx, ny, nCrack));
                    }
                }
            }
        }

        System.out.println(crack[N][M]);
    }

    static class Node {
        int x;
        int y;
        int crack;

        public Node(int x, int y, int crack) {
            this.x = x;
            this.y = y;
            this.crack = crack;
        }
    }
}