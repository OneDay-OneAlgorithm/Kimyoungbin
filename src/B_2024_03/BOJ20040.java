package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20040
{
    // search: union-find
    static int[] parent;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        m = Integer.parseInt(line_1[1]);
        parent = new int[n];

        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        for(int i=0; i<m; i++) {
            String[] line_m = br.readLine().split(" ");
            int s = Integer.parseInt(line_m[0]);
            int e = Integer.parseInt(line_m[1]);
            if(find(s)==find(e)) {
                System.out.println(i+1);
                return;
            } else {
                union(s, e);
            }
        }
        System.out.println(0);
    }

    // 그룹 합치기 - 작은 값을 대표노드로 설정
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a<b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    // 속한 그룹의 대표노드 찾기
    private static int find(int a) {
//        while(parent[a]!=a) {
//            parent[a] = parent[parent[a]];
//        }
//        return parent[a];
        if(a==parent[a])
            return a;
        return parent[a]=find(parent[a]);
    }
}

