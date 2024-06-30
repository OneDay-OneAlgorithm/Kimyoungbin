package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// disjoint set(union find)
public class BOJ10216 {
    static int N, rst;
    static Node[] node;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            rst = 0;
            node = new Node[N];
            parent = new int[N];
            for(int i=0; i<N; i++) {
                parent[i] = i;
            }

            for(int i=0; i<N; i++) {
                String[] line_N = br.readLine().split(" ");
                int x = Integer.parseInt(line_N[0]);
                int y = Integer.parseInt(line_N[1]);
                int r = Integer.parseInt(line_N[2]);

                node[i] = new Node(x, y, r);
            }

            // grouping
            for(int i=0; i<N; i++) {
                for(int j=i+1; j<N; j++) {
                    if(isConnected(i, j)) {
                        union(i, j);
                    }
                }
            }

            // find group number
            for(int i=0; i<N; i++) {
                boolean flag = true;
                for(int j=0; j<i; j++) {
                    if(find(i)==find(j)) { // node[i]가 기존 그룹에 속하면 flag=false
                        flag = false;
                    }
                }
                if(flag) rst++; // 새로운 그룹일 경우에 rst증가
            }

            System.out.println(rst);
        }
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

    // 두 진영이 상호통신 가능한지
    private static boolean isConnected(int n1, int n2) {
        Node a = node[n1];
        Node b = node[n2];

        return Math.sqrt(Math.pow(a.x-b.x, 2)+Math.pow(a.y-b.y, 2))<=a.r+b.r; // 닿거나 겹치는 부분이 있다면
    }

    static class Node {
        int x;
        int y;
        int r;
        public Node(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}