import java.io.*;
import java.util.*;

public class BOJ14940
{
    static int n, m;
    static int[][] map;
    static int[][] rst;
    static Queue<Node> queue = new LinkedList<>();
    static boolean[][] visited;
    static Node start;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        m = Integer.parseInt(line_1[1]);
        map = new int[n][m];
        visited = new boolean[n][m];
        rst = new int[n][m];

        for(int i=0; i<n; i++) {
            String[] line_n = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                int here = Integer.parseInt(line_n[j]);
                map[i][j] = here;
                if(here==2) {
                    start = new Node(i, j, 0);
                }
            }
        }

        queue.add(start);
        visited[start.y][start.x] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = node.x+dx[i];
                int ny = node.y+dy[i];
                if(0<=nx && nx<m && 0<=ny && ny<n && map[ny][nx]!=0 && !visited[ny][nx]) {
                    queue.add(new Node(ny, nx, node.dist+1));
                    visited[ny][nx] = true;
                    rst[ny][nx] = node.dist+1;
                }
            }
        }

        //원래 갈 수 있는 땅인데 목표지점까지 갈 수 없는 경우 -1을 출력한다.
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j]==1 && rst[i][j]==0) {
                    rst[i][j]=-1;
                }
            }
        }

        //정답 출력
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                bw.write(rst[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    static class Node {
        int x;
        int y;
        int dist;
        public Node(int y, int x, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
