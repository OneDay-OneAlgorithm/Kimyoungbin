package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// MST Standard
// Edge List, Collection sort, Union-find
public class BOJ1197 {
    static int V, E;
    static int[] parent;
    static Node[] list;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        V = Integer.parseInt(line_1[0]);
        E = Integer.parseInt(line_1[1]);
        // 연결 리스트 생성, 유니온파인드 초기화
        parent = new int[V+1];
        list = new Node[E];
        for(int i=0; i<E; i++) {
            String[] line_V = br.readLine().split(" ");
            int start = Integer.parseInt(line_V[0]);
            int end = Integer.parseInt(line_V[1]);
            int cost = Integer.parseInt(line_V[2]);
            list[i] = new Node(start, end, cost);
        }
        for(int i=1; i<=V; i++) {
            parent[i] = i;
        }

        // 엣지 가중치 오름차순 정렬
        Arrays.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.cost - n2.cost;
            }
        });

        // 엣지 오름차순 검사하면서 사이클이 존재하지 않을때(부모노드가 일치하지 않을때) 연결하고(union), 가중치 개수 추가
        for(int i=0; i<E; i++) {
            Node node = list[i];
            if(find(node.start)!=find(node.end)) { // 대표노드가 달라야 연결해도 사이클이 생기지 않는다.
                union(node.start, node.end);
                rst+=node.cost;
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
        if(n==parent[n]) {
            return n;
        }
        return find(parent[n]);
    }

    static class Node {
        int start;
        int end;
        int cost;
        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}