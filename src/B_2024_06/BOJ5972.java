package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// dijkstra (1에서 N까지 최단거리, 가중치 존재)
public class BOJ5972 {
    static int N, M;
    static int[] dest;
    static boolean[] visited;
    static ArrayList<Info>[] list;
    static PriorityQueue<Integer> queue;
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        dest = new int[N+1];
        list = new ArrayList[N+1]; // 1~N

        for(int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
            dest[i] = INF;
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int s = Integer.parseInt(line_M[0]);
            int e = Integer.parseInt(line_M[1]);
            int w = Integer.parseInt(line_M[2]);
            // 하나 이상의 길로 연결되어 있을수도 있긴한데 PQ에서 내부 정렬되므로 결과에는 영향 없어보임
            list[s].add(new Info(e, w));
            list[e].add(new Info(s, w));
        }

        dijkstra();

        System.out.println(dest[N]);
    }

    private static void dijkstra() {
        dest[1] = 0;
        visited = new boolean[N+1];
        queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dest[o1]-dest[o2];
            }
        });
        queue.add(1);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int i=0; i<list[cur].size(); i++) {
                Info next = list[cur].get(i);

                if(dest[next.dest]>dest[cur]+next.weight) {
                    dest[next.dest] = dest[cur]+next.weight;
                    if(!visited[next.dest]) {
                        queue.add(next.dest);
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
