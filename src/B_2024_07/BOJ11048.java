package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// WA1)
public class BOJ11048 {
    static int N, M;
    static int[][] arr, max;
    static boolean[][] visited;
    static Queue<Node> queue;
    static int[] dx = {1,0,1}, dy = {0,1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        arr = new int[N][M];
        max = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(line_N[j]);
            }
        }

        queue = new LinkedList<>();
        queue.add(new Node(0, 0));
        max[0][0] = arr[0][0];
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            for(int i=0; i<3; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(0<=ny && ny<N && 0<=nx && nx<M) {
                    if(!visited[ny][nx] || max[ny][nx]<max[cur.y][cur.x]+arr[ny][nx]) { // WA1) 방문하지 않았는데 같은경우에 아예 탐색을 안한다.
                        max[ny][nx] = max[cur.y][cur.x]+arr[ny][nx];
                        visited[ny][nx] = true;
                        queue.add(new Node(nx, ny));
                    }
                }
            }
        }

//        printMAx();

        System.out.println(max[N-1][M-1]);

    }

    private static void printMAx() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(max[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
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