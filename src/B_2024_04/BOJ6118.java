package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 1번에서부터 가장 먼 헛간 탐색 - bfs
public class BOJ6118 {
    static int N, M;
    static LinkedList<Integer>[] list;
    static boolean[] visited;
    static LinkedList<Integer>[] dist; // 1번으로부터 거리가 i인 헛간 리스트
    static Queue<Node> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 헛간
        M = Integer.parseInt(line_1[1]); // 엣지
        list = new LinkedList[N+1];
        visited = new boolean[N+1];
        dist = new LinkedList[20001];
        for(int i=1; i<=N; i++) {
            list[i] = new LinkedList<>();
        }
        for(int i=0; i<=20000; i++) {
            dist[i] = new LinkedList<>();
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int start = Integer.parseInt(line_M[0]);
            int end = Integer.parseInt(line_M[1]);
            list[start].add(end);
            list[end].add(start);
        }

        queue.add(new Node(1, 0));
        dist[0].add(1);
        visited[1] = true;
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            for(int i=0; i<list[cur.n].size(); i++) {
                int next = list[cur.n].get(i);
                if(!visited[next]) {
                    queue.add(new Node(next, cur.depth+1));
                    visited[next] = true;
                    dist[cur.depth+1].add(next);
                }
            }
        }

        for(int i=20000; i>=0; i--) {
            if(dist[i].size()!=0) {
                int number = Integer.MAX_VALUE;
                for(int j=0; j<dist[i].size(); j++) {
                    number = Math.min(number, dist[i].get(j));
                }
                int distance = i;
                int size = dist[i].size();
                System.out.println(number+" "+distance+" "+size);
                return;
            }
        }
    }

    static class Node {
        int n;
        int depth;

        public Node(int n, int depth) {
            this.n = n;
            this.depth = depth;
        }
    }
}