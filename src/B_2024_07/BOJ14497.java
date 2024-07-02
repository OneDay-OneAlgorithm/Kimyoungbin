package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14497 {
    static int N, M, x1, y1, x2, y2, rst = 0;
    static int[][] map; // 0: 빈공간, 1: 사람, 2: 무너질 사람
    static Queue<Node> queue;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        map = new int[N][M];
        visited = new boolean[N][M];
        String[] line_2 =  br.readLine().split( " ");
        y1 = Integer.parseInt(line_2[0])-1; // 주난
        x1 = Integer.parseInt(line_2[1])-1;
        y2 = Integer.parseInt(line_2[2])-1; // 범인
        x2 = Integer.parseInt(line_2[3])-1;

        // initialize
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split("");
            for(int j=0; j<M; j++) {
                map[i][j] = line_N[j].equals("1")?1:0;
            }
        }

        // 뛰면서 상하좌우 사람 제거하고, 범인이 제거되었는지 확인
        while(!bfs()) {
            clearMap();
            rst++;
        }

        System.out.println(rst+1);
    }

    private static void printMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // 넘어진 시체 처리 (2->0)
    private static void clearMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==2) {
                    map[i][j] = 0;
                }
            }
        }
    }

    // 1. bfs 통해 현재 단계에서 방문 가능한 사람벽을 마킹 (1->2)
    // 2. bfs 과정 중 범인이 색출되었는지 리턴
    private static boolean bfs() {
        boolean flag = false;
        queue = new LinkedList<>();
        visited = new boolean[N][M];
        queue.add(new Node(x1, y1));
        visited[y1][x1] = true;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            if(cur.x==x2 && cur.y==y2) {
                flag = true;
            }

            for(int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if(0<=nx && nx<M && 0<=ny && ny<N) {
                    if(!visited[ny][nx] && map[ny][nx]==0) {
                        visited[ny][nx] = true;
                        queue.add(new Node(nx, ny));
                    } else if(map[ny][nx]==1) {
                        map[ny][nx]=2;
                    }
                }
            }
        }

        return flag;
    }

    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}