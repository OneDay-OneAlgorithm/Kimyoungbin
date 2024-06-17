package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 거리: 맨해튼 거리(x좌표 차이+y좌표 차이)
// 1000(50*20)거리 이하에서 편의점이 존재해야 한다.
// bfs로 탐색해나가면서 1.방문가능한 편의점이 있는지, 2. 축제에 갈 수 있는지만 탐색해주면 될듯하다.
public class BOJ9205 {
    static int n; // 편의점의 개수
    static Node home, festival;
    static Node[] cons;
    static boolean[] visited;
    static Queue<Node> queue;
    static boolean rst; // 축제에 갈 수 있는지
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            rst = false;
            n = Integer.parseInt(br.readLine());
            cons = new Node[n];
            visited = new boolean[n]; // 해당 번호 편의점에 방문했는지 여부
            String[] line_1 = br.readLine().split(" ");
            home = new Node(Integer.parseInt(line_1[0]), Integer.parseInt(line_1[1]));
            for(int i=0; i<n; i++) {
                String[] line_n = br.readLine().split(" ");
                cons[i] = new Node(Integer.parseInt(line_n[0]), Integer.parseInt(line_n[1]));
            }
            String[] line_last = br.readLine().split(" ");
            festival = new Node(Integer.parseInt(line_last[0]), Integer.parseInt(line_last[1]));

            bfs();

            System.out.println(rst?"happy":"sad");
        }
    }

    private static void bfs() {
        queue = new LinkedList<>();
        queue.add(home);

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            if(isReachable(cur, festival)) {
                rst = true;
                return;
            }

            for(int i=0; i<n; i++) {
                if(!visited[i] && isReachable(cur, cons[i])) {
                    queue.add(cons[i]);
                    visited[i] = true;
                }
            }
        }
    }

    // 맨해튼 거리 1000 이하인 경우 이동 가능
    private static boolean isReachable(Node start, Node end) {
        return Math.abs(start.x-end.x)+Math.abs(start.y-end.y)<=1000;
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
