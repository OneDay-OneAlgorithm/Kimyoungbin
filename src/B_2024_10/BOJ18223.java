package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1->V번 정점까지 이동
// 1->V와, 1->P->V가 동일한지 검사
// WA1) P=1일경우 start==P가 되어 PV에 값이 제대로 저장되지 않음
public class BOJ18223 {
    static int V, E, P;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Info>[] list;
    static PriorityQueue<Info> pq;
    static final int INF = 0x3f3f3f3f;
    static int SV, SP, PV;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        // 간선정보 초기화
        list = new ArrayList[V+1];
        for(int i=1; i<=V; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Info(e, w));
            list[e].add(new Info(s, w));
        }

        // 탐색 1
        dijkstra(1, 1);

        // 탐색 2
        dijkstra(P, 2);

        System.out.println(SV==SP+PV?"SAVE HIM":"GOOD BYE");
    }

    private static void dijkstra(int start, int type) {
        visited = new boolean[V+1];
        dist = new int[V+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.weight-o2.weight;
            }
        });
        pq.add(new Info(start, 0));

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            if(visited[cur.dest]) continue;
            else visited[cur.dest] = true;

            for(int i=0; i<list[cur.dest].size(); i++) {
                Info next = list[cur.dest].get(i);
                if(dist[next.dest]>dist[cur.dest]+next.weight) {
                    dist[next.dest] = dist[cur.dest]+next.weight;
                    pq.add(new Info(next.dest, dist[next.dest]));
                }
            }
        }

        if(type==1) {
            SV = dist[V];
            SP = dist[P];
        } else {
            PV = dist[V];
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
