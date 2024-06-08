package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// 0,0부터 N-1,N-1까지 이동하는데 드는 최소비용
// N=125이므로 모든 경로를 지나가면서 테스트하려면 TLE
// 가중치가 모두 같을때는 BFS를 사용하지만, 가중치가 다를 때는 Dijkstra를 사용해준다.
public class BOJ4485 {
    static int N, T=1;
    static int[][] arr, dist;
    static PriorityQueue<Node> queue;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while((N = Integer.parseInt(br.readLine()))!=0) {
            arr = new int[N][N];
            dist = new int[N][N]; //[i][j]까지 가는데 걸리는 최소비용
            queue = new PriorityQueue<Node>(new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    return dist[n1.y][n1.x] - dist[n2.y][n2.x];
                }
            });

            // input
            for(int i=0; i<N; i++) {
                String[] line_N = br.readLine().split(" ");
                for(int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(line_N[j]);
                    dist[i][j] = INF;
                }
            }

            dijkstra();

            System.out.println("Problem "+(T++)+": "+dist[N-1][N-1]);
        }
    }

    private static void dijkstra() {
        dist[0][0] = arr[0][0];
        visited = new boolean[N][N];
        queue.add(new Node(0, 0));

        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(0<=nx && nx<N && 0<=ny && ny<N && dist[ny][nx]>dist[cur.y][cur.x]+arr[ny][nx]) {
                    dist[ny][nx] = dist[cur.y][cur.x]+arr[ny][nx];
                    if(!visited[ny][nx]) {
                        queue.add(new Node(nx, ny));
                    }
                }
            }
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
}
