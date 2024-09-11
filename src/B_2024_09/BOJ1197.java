package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1197 {
    static int V, E;
    static int[] parent;
    static int rst = 0;
    static PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.weight-o2.weight;
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        for(int i=1; i<=V; i++) {
            parent[i] = i;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.add(new Info(A, B, C));
        }

        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            if(find(cur.start)!=find(cur.dest)) {
                union(cur.start, cur.dest);
                rst += cur.weight;
            }
        }

        System.out.println(rst);
    }

    private static void union(int a, int b) {
        if(a<b) {
            parent[find(a)] = find(b);
        } else {
            parent[find(b)] = find(a);
        }
    }

    private static int find(int a) {
        if(a==parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    static class Info {
        int start;
        int dest;
        int weight;

        public Info(int start, int dest, int weight) {
            this.start = start;
            this.dest = dest;
            this.weight = weight;
        }
    }
}