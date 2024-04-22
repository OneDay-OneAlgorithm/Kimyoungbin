package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// MST
public class BOJ1922 {
    static int N, M;
    static Link[] link;
    static int[] parent;
    static int cost = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 컴퓨터 수
        M = Integer.parseInt(br.readLine()); // 연결 정보
        link = new Link[M];
        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int a = Integer.parseInt(line_M[0]);
            int b = Integer.parseInt(line_M[1]);
            int c = Integer.parseInt(line_M[2]);
            link[i] = new Link(a, b, c);
        }
        Arrays.sort(link, new Comparator<Link>() { // cost 오름차순 정렬
            @Override
            public int compare(Link l1, Link l2) {
                return l1.cost - l2.cost;
            }
        });

        for(int i=0; i<M; i++) {
            Link cur = link[i];
            if(find(cur.n1)!=find(cur.n2)) { // 현재 간선의 양 끝점이 연결되지 않았다면
                union(cur.n1, cur.n2); // 두 점 연결
                cost+=cur.cost;
            }
        }

        System.out.println(cost);
    }

    private static void union(int a, int b) {
        if(a<b) {
            parent[find(b)] = find(a);
        } else {
            parent[find(a)] = find(b);
        }
    }

    private static int find(int a) {
        if(parent[a]==a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    static class Link {
        int n1;
        int n2;
        int cost;

        public Link(int n1, int n2, int cost) {
            this.n1 = n1;
            this.n2 = n2;
            this.cost = cost;
        }
    }
}