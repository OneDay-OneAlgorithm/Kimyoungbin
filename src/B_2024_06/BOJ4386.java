package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// mst
// 모든 간선정보를 넣어놓아야 할 것 같음. 이걸로 TLE나면 boolean flag를 통해서 MST가 완성되면 종료되도록 해주어야 할듯
public class BOJ4386 {
    static int N, M;
    static int[] parent;
    static ArrayList<Node> gods, list;
    static double rst;
    static PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            if(o1.dist-o2.dist>0) return 1;
            else if(o1.dist-o2.dist<0) return -1;
            else return 0;
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        parent = new int[N];
        for(int i=0; i<N; i++) {
            parent[i] = i;
        }
        gods = new ArrayList<>();
        list = new ArrayList<>();

        // input
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            gods.add(new Node(Integer.parseInt(line_N[0]), Integer.parseInt(line_N[1])));
        }
        for(int i=0; i<M; i++) {
            String[] line_M = br.readLine().split(" ");
            int a = Integer.parseInt(line_M[0])-1;
            int b = Integer.parseInt(line_M[1])-1;
            union(a,b);
        }

        // 모든 간선정보 pq에 저장
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i!=j) {
                    pq.add(new Info(i, j));
                }
            }
        }

        // pq에서 제거하면서 MST 만들기
        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            if(find(parent[cur.node1])!=find(parent[cur.node2])) { // 현재 간선의 양 끝점이 같은 그룹에 속하지 않으면 (사이클)
                union(cur.node1, cur.node2);
                Node n1 = gods.get(cur.node1);
                Node n2 = gods.get(cur.node2);
                rst += getDist(n1.x, n1.y, n2.x, n2.y);
            }
        }

        System.out.printf("%.2f\n", rst);
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

    private static double getDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
    }

    static class Info {
        int node1;
        int node2;
        double dist;

        public Info(int n1, int n2) {
            this.node1 = n1;
            this.node2 = n2;
            Node node1 = gods.get(n1);
            Node node2 = gods.get(n2);
            this.dist = getDist(node1.x, node1.y, node2.x, node2.y);
        }
    }

    static class Node{
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}