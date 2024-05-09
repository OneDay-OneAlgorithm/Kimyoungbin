package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 사이클 여부 판단: dfs or union-find
// search) 지워질때마다 사이클이 제거되었는지 판단(dfs)하기에는 TLE 예상되므로 - O(200_000*200,000)
// 정점이 지워지는 순서는 결정 -> 간선이 지워지는 순서도 결정되어 있으므로 역순으로 그래프를 만들면서 사이클이 생기는 시점을 판단 (union-find)
// 고민 point 1) queue에서는 순차 연결을 위해서 양방향 간선정보가 필요한데, stack에서는 의도치 않은 사이클이 생길 수 있으므로 단일간선의 정보만 필요함
// -> visited체크를 미리하는게 아니라 큐에서 꺼낸다음에 하고, 큐에는 단 하나의 정점만 들어가야 하므로 set으로 관리 (visited랑 큐에 들어가는 노드 개념을 분리)
// 56% WA) 첫 삭제 노드 저장시 set에는 저장해주지 않음
public class BOJ30870 {
    static int N, M, K;
    static LinkedList<Integer>[] list;
    static Queue<Node> queue = new LinkedList<>(); // bfs를 위한 큐
    static Stack<Edge> stack = new Stack<>(); // 역순 노드 추가를 위한 스택
    static boolean[] visited;
    static int[] parent;
    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        K = Integer.parseInt(line_1[2]);
        visited = new boolean[N+1];

        // 연결 간선 정보 초기화
        list = new LinkedList[N+1];
        for(int i=1; i<=N; i++) {
            list[i] = new LinkedList<>();
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int start = Integer.parseInt(line_M[0]);
            int end = Integer.parseInt(line_M[1]);
            // 넣을때 양방향으로 넣으면, 이걸 하나만 체크해서 stack에 넣어야함 (의도치 않은 사이클이 생길 수 있음)
            // 근데 또 중복간선이 주어질 수 있으니까 이 경우 중복간선 자체로 사이클을 만들 수 있으니까 체크를 해줘야함
            list[start].add(end);
            list[end].add(start);
        }

        // 첫 삭제 노드 저장
        String[] line_K = br.readLine().split(" ");
        for(int i=0; i<K; i++) {
            int first = Integer.parseInt(line_K[i]);
            queue.add(new Node(first, 1));
            set.add(first); // WA1
        }

        // 노드 삭제, 순서 저장
        int deleteTime = 0;
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            visited[cur.n] = true;
            deleteTime = Math.max(deleteTime, cur.time);

            for(int i=0; i<list[cur.n].size(); i++) {
                int next = list[cur.n].get(i);
                if(!visited[next]) { // 노드가 아직 삭제되지 않은 경우
                    if(!set.contains(next)) { // visited체크가 되지 않았어도 큐에 중복노드가 들어가지는 않게 함
                        set.add(next);
                        queue.add(new Node(next, cur.time+1));
                    }
//                    System.out.print("cur.n = " + cur.n);
//                    System.out.println(", next = " + next);
                    stack.add(new Edge(cur.n, next, cur.time)); // 스택에 삭제되는 간선정보 저장 -> time순 저장되므로 순차적으로 꺼내면서 그래프 다시 만들 예정
                }
            }
        }

        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }

        // 스택에서 꺼내면서 시간순서대로 union-find로 사이클 판별
        int time = deleteTime;
        boolean cycle = false;
        while(!stack.isEmpty()) {
            Edge cur = stack.pop();
//            System.out.print("cur.start = " + cur.start);
//            System.out.print(", cur.end = " + cur.end);
//            System.out.println(", cur.time = " + cur.time);
            // 시간이 지났으면 사이클 생겼는지 체크
            if(cur.time==time-1) {
                if(cycle) {
                    break;
                }
                time--;
            }
            // 뽑은 간선을 연결했을떄 사이클이 생기는지 체크
//            System.out.print("cur.start = " + cur.start);
//            System.out.println(", cur.end = " + cur.end);
            if(find(cur.start)==find(cur.end)) {
//                System.out.println("사이클 발생");
                cycle = true;
            }
            union(cur.start, cur.end);
        }

        // deleteTime: 정점을 전부 지우는데 걸리는 시간
        // time: 사이클이 존재하지 않는 마지막 시간
//        System.out.println("deleteTime = " + deleteTime);
//        System.out.println("time = " + time);
        System.out.println(time);
    }

    private static void union(int a, int b) {
        if(a<b) {
            parent[find(b)] = find(a);
        } else {
            parent[find(a)] = find(b);
        }
    }

    private static int find(int a) {
        if(parent[a]==a) return a;
        else return parent[a] = find(parent[a]);
    }

    static class Node {
        int n;
        int time;

        public Node(int n, int time) {
            this.n = n;
            this.time = time;
        }
    }

    static class Edge {
        int start;
        int end;
        int time;

        public Edge(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}