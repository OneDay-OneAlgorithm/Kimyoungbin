package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 마을 분리 후 MST -> 분리를 어떻게 해야 하나?
// 가중치 긴것부터 빼가면서 find함수를 통해 분리 검사? -> O(N*M)까지 나올수 있어서 TLE
// search) 그냥 전체에 대해서 MST를 하는데, N-2개의 엣지만 union해주면 2개로 분리된다.
// 1. TLE) find()함수 수정
public class BOJ1647 {
    static int N, M;
    static int[] parent;
    static Edge[] list;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        list = new Edge[M];
        parent = new int[N+1]; // 1~N
        // 초기화
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }
        //입력
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int start = Integer.parseInt(line_M[0]);
            int end = Integer.parseInt(line_M[1]);
            int cost = Integer.parseInt(line_M[2]);
            list[i] = new Edge(start, end, cost);
        }
        // 가중치 오름차순 정렬
        Arrays.sort(list, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.cost-e2.cost;
            }
        });

        // 사이클이 존재하지 않으면 그래프 추가
        int count = 0;
        for(int i=0; i<M; i++) {
            if(count>=N-2) { // N-2개의 간선 추가 (2개로 분리)
                break;
            }
            Edge edge = list[i];
            if(find(edge.start)!=find(edge.end)) { // 사이클이 존재하지 않으면
                union(edge.start, edge.end); // 그래프에 추가
                rst+=edge.cost;
                count++;
            }
        }

        System.out.println(rst);
    }

    private static void union(int a, int b) {
        if(a<b) {
            parent[find(b)] = find(a);
        } else {
            parent[find(a)] = find(b);
        }
    }

    private static int find(int n) {
        if(n==parent[n]) return n;
        return parent[n] = find(parent[n]); // 재귀를 통해서 가치는 모든 노드의 대표값을 n으로 바꾸어줌
    }

    static class Edge {
        int start;
        int end;
        int cost;
        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}