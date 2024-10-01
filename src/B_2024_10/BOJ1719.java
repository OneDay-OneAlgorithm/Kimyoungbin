package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// dijkstra - O(VlogE*V)
// search) rst 갱신하는 부분
public class BOJ1719 {
    static int n, m;
    static ArrayList<Info>[] list;
    static boolean[] visited;
    static PriorityQueue<Info> pq;
    static int[] dist;
    static final int INF = 0x3f3f3f3f;
    static int[][] rst; // rst[i][j]: i에서 j로 가기 위해서 가장 먼저 가야 하는 집하장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        rst = new int[n+1][n+1];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Info(e, w));
            list[e].add(new Info(s, w));
        }

        for(int start=1; start<=n; start++) {
            dijkstra(start);
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(rst[i][j]==0) {
                    System.out.print("- ");
                }
                else System.out.print(rst[i][j]+" ");
            }
            System.out.println();
        }

    }

    private static void dijkstra(int start) {
        visited = new boolean[n+1];
        dist = new int[n+1];
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
                if(dist[next.dest] > dist[cur.dest]+next.weight) {
                    dist[next.dest] = dist[cur.dest]+next.weight;
                    if(!visited[next.dest]) {
                        pq.add(new Info(next.dest, dist[next.dest]));
                        // 이동해야 하는 정보 갱신
                        if(start==cur.dest) {
                            rst[start][next.dest] = next.dest; // 시작점이라면 다음노드가 방문해야 할 노드
                        } else {
                            rst[start][next.dest] = rst[start][cur.dest]; // 시작점이 아니라면 start->next.dest로 가는 첫번째 노드는 start->cur.dest로 이동하는 노드와 동일
                        }
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
