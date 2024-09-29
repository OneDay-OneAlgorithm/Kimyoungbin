package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 가중치 bfs -> dijkstra
// 시작 컴퓨터 ~ 나머지 컴퓨터까지 걸리는 시간의 최대값
// WR1)
public class BOJ10282 {
    static int n, d, c;
    static ArrayList<Info>[] list;
    static PriorityQueue<Info> pq;
    static boolean[] visited;
    static int[] dist;
    static final int INF = 0x3f3f3f3f;
    static int infectedComputer, infectTime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            list = new ArrayList[n+1];
            for(int i=1; i<=n; i++) {
                list[i] = new ArrayList<>();
            }
            visited = new boolean[n+1];
            infectedComputer = 0;
            infectTime = 0;

            for(int i=0; i<d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // b->a 경로
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list[b].add(new Info(a, s));
            }

            dijkstra(c);
            for(int i=1; i<=n; i++) {
                if(dist[i]!=INF) {
                    infectedComputer++;
                    infectTime = Math.max(infectTime, dist[i]);
                }
            }
            System.out.println(infectedComputer+" "+infectTime);
        }
    }

    private static void dijkstra(int start) {
        dist = new int[n+1];
        visited = new boolean[n+1];
        pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.time-o2.time;
            }
        });
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Info(start, 0));

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            if (visited[cur.dest]) continue; // 이미 방문한 경우 건너뛰기
            visited[cur.dest] = true;        // 현재 노드 방문 처리

            for(int i=0; i<list[cur.dest].size(); i++){
                Info next = list[cur.dest].get(i);
                if(dist[next.dest]>dist[cur.dest]+next.time) {
                    dist[next.dest] = dist[cur.dest]+next.time;
                    if(!visited[next.dest]) {
                        pq.add(new Info(next.dest, dist[next.dest]));
                    }
                }
            }
        }

    }

    static class Info {
        int dest;
        int time;
        public Info(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }
    }
}
