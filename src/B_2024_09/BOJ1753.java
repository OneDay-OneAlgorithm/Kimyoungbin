package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753 {
    static int V, E, K;
    static ArrayList<Info>[] list;
    static int[] dist;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        list = new ArrayList[V+1];
        for(int i=0; i<=V; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[u].add(new Info(v, w));
        }

        dijkstra(K);

        for(int i=1; i<=V; i++) {
            System.out.println(dist[i]==INF?"INF":dist[i]);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.weight-o2.weight;
            }
        });

        boolean[] visited = new boolean[V+1];
        dist = new int[V+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Info(start, 0));

        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            visited[cur.dest] = true;

            for(int i=0; i<list[cur.dest].size(); i++) {
                Info next = list[cur.dest].get(i);
                if(dist[next.dest]>dist[cur.dest]+next.weight) {
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
        int weight;
        public Info(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}