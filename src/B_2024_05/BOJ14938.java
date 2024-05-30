package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// n=100이므로 모든 지역에 대해서 bfs
// WA1) 간선 가중치가 다른 그래프의 경우 다익스트라 활용 (비효율적인 경로가 먼저 방문해버릴 수 있다)
// WA2) 거리 갱신시에 visited를 체크하면 안된다.
public class BOJ14938 {
    static int n, m, r;
    static int[] item, dist;
    static ArrayList<Info>[] list;
    static PriorityQueue<Info> queue;
    static boolean[] visited;
    static int rst = 0;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]); // 지역
        m = Integer.parseInt(line_1[1]); // 수색범위
        r = Integer.parseInt(line_1[2]); // 길

        item = new int[n+1];
        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        String[] line_2 = br.readLine().split(" ");
        for(int i=1; i<=n; i++) {
            item[i] = Integer.parseInt(line_2[i-1]);
        }

        // 거리 정보 받기
        for(int i=0; i<r; i++) {
            String[] line_r = br.readLine().split(" ");
            int s = Integer.parseInt(line_r[0]);
            int e = Integer.parseInt(line_r[1]);
            int w = Integer.parseInt(line_r[2]);
            list[s].add(new Info(e, w));
            list[e].add(new Info(s, w));
        }

        // n개의 시작점에 대해서 다익스트라를 통해서 거리를 구하고, 범위 내의 아이템 더한것의 최대값 구하기
        for(int i=1; i<=n; i++) {
            dijkstra(i);

            // 최대값 찾기
            int sum = 0;
            for(int j=1; j<=n; j++) {
                if(dist[j]<=m)
                    sum += item[j];
            }
            rst = Math.max(sum, rst);
        }

        System.out.println(rst);
    }

    private static void dijkstra(int start) {
        // 우선순위 큐 조건 설정
        queue = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.weight-o2.weight;
            }
        }); // 가까운 것 우선순위
        // visited는 방문시가 아니라, 첫 방문시에만 체크
        visited = new boolean[n+1];
        // dist 초기화 - 시작점만 0으로
        dist = new int[n+1];
        for(int j=1; j<=n; j++) {
            dist[j] = INF;
        }
        dist[start] = 0;

        queue.add(new Info(start, 0));

        while(!queue.isEmpty()) {
            Info poll = queue.poll();
            visited[poll.dest] = true;

            // poll 한 정점의 주변 정점들만 비교해 나감
            for(int j=0; j<list[poll.dest].size(); j++) {
                Info next = list[poll.dest].get(j);
                if(dist[next.dest]>dist[poll.dest]+next.weight) {
                    dist[next.dest] = dist[poll.dest]+next.weight;
                    if(!visited[next.dest]) { // 갱신시에만 visited 체크
                        queue.add(new Info(next.dest, dist[next.dest]));
                    }
                }
            }
        }
    }

    static class Info {
        int dest;
        int weight; // dest까지 거리

        public Info(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}
