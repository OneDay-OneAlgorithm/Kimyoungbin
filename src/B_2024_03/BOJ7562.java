package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7562
{
    static int T, l;
    static boolean[][] visited;
    static int sx, sy, tx, ty;
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
    static Queue<Node> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            l = Integer.parseInt(br.readLine());
            visited = new boolean[l][l];
            queue = new LinkedList<>();
            String[] line_1 = br.readLine().split(" ");
            String[] line_2 = br.readLine().split(" ");
            sx = Integer.parseInt(line_1[1]);
            sy = Integer.parseInt(line_1[0]);
            tx = Integer.parseInt(line_2[1]);
            ty = Integer.parseInt(line_2[0]);

            System.out.println(bfs());
        }
    }

    private static int bfs() {

        visited[sy][sx] = true;
        queue.add(new Node(sx, sy, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(node.x==tx && node.y==ty) {
                return node.depth;
            }

            for(int i=0; i<8; i++) {
                int nx = node.x+dx[i];
                int ny = node.y+dy[i];
                if(0<=nx && nx<l && 0<=ny && ny<l && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.add(new Node(nx, ny, node.depth+1));
                }
            }
        }

        return -1;
    }

    static class Node {
        int x;
        int y;
        int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}

