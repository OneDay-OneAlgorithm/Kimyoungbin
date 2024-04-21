package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// union-find후 2개의 대표노드를 연결
public class BOJ17352 {
    static int N;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }
        for(int i=0; i<N-2; i++) {
            String[] bridge = br.readLine().split(" ");
            int n1 = Integer.parseInt(bridge[0]);
            int n2 = Integer.parseInt(bridge[1]);
            union(n1, n2);
        }

//        for(int i=1; i<=N; i++) {
//            System.out.println("parent["+i+"] = " + find(parent[i]));
//        }

        // 대표노드가 다른 두 노드 출력
        for(int i=2; i<=N; i++) {
            if(find(i)!=find(1)) {
                System.out.println((parent[1])+" "+(parent[i]));
                return;
            }
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
}