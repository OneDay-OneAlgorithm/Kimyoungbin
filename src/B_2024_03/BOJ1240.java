package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 1. 가중치가 있는 트리
// 2. 두 점의 거리 구하기
// 3. N, M<=1000이므로 O(N^2), Floyd 불가능
// 4. N개의 노드, N-1개의 엣지의 트리이므로 모든 점이 연결되어 있음
public class BOJ1240 {
    static int N, M;
    static ArrayList<Node>[] list;
    static int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++) {
            String[] line_N = br.readLine().split(" ");
            int start = Integer.parseInt(line_N[0]);
            int end = Integer.parseInt(line_N[1]);
            int cost = Integer.parseInt(line_N[2]);
            list[start].add(new Node(end, cost));
            list[end].add(new Node(start, cost));
        }

        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int start = Integer.parseInt(line_M[0]);
            int end = Integer.parseInt(line_M[1]);
            System.out.println(bfs(start, end));
        }
    }

    static int bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[N+1]; // start점으로부터 거리정보
        Arrays.fill(dist, INF);
        dist[start] = 0;
        queue.add(start);

        while(!queue.isEmpty()) {
            int cur = queue.remove();
            for(int i=0; i<list[cur].size(); i++) {
                Node next = list[cur].get(i);
                if(dist[cur]+next.cost < dist[next.end]) {
                    dist[next.end] = dist[cur] + next.cost;
                    queue.add(next.end);
                }
            }
        }

        return dist[end];
    }

    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}