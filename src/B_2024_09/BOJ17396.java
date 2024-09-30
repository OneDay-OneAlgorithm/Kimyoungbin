package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시야에 보이는 곳은 아예 간선을 연결하지 않음
// WR1) 연결은 양방향
// WR2) int -> long (INF: 100,000*100,000+1)
public class BOJ17396 {
    static int N, M;
    static boolean[] available;
    static ArrayList<Info>[] list;
    static PriorityQueue<Info> pq;
    static long[] dist;
    static boolean[] visited;
    static final long INF = 100000L*100000+1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        available = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            available[i] = st.nextToken().equals("0");
        }
        available[N-1] = true;
        list = new ArrayList[N];
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if(available[a] && available[b]) {
                list[a].add(new Info(b, t));
                list[b].add(new Info(a, t));
            }
        }

        dijkstra(0);

        System.out.println(dist[N-1]!=INF?dist[N-1]:-1);
    }

    private static void dijkstra(int start) {
        pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if(o1.weight==o2.weight) return 0;
                return o1.weight>o2.weight?1:-1;
            }
        });
        pq.add(new Info(start, 0));
        dist = new long[N];
        Arrays.fill(dist, INF);
        visited = new boolean[N];
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            if(visited[cur.dest]) {
                continue;
            }
            visited[cur.dest] = true;

            // cur을 거쳐서 다른 노드로 가는 방법이 더 짧은지 검사
            for(int i=0; i<list[cur.dest].size(); i++) {
                Info next = list[cur.dest].get(i);
                if(dist[next.dest] > dist[cur.dest]+next.weight) {
                    dist[next.dest] = dist[cur.dest]+next.weight;
                    if(!visited[next.dest]) {
                        pq.add(new Info(next.dest, dist[next.dest]));
                    }
                }
            }
        }
    }

    static class Info {
        int dest;
        long weight;
        public Info(int dest, long weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}
