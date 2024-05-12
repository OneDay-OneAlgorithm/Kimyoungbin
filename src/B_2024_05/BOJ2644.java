package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ2644 {
    static int n, m, x, y, start, end;
    static LinkedList<Integer>[] list;
    static boolean[] visited;
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] line_2 = br.readLine().split(" ");
        start = Integer.parseInt(line_2[0]);
        end = Integer.parseInt(line_2[1]);
        m = Integer.parseInt(br.readLine());
        list = new LinkedList[n+1];
        visited = new boolean[n+1];
        for(int i=1; i<=n; i++) {
            list[i] = new LinkedList<>();
        }

        // 부모-자식 관계
        for(int i=0; i<m; i++) {
            String[] line_M = br.readLine().split(" ");
            x = Integer.parseInt(line_M[0]);
            y = Integer.parseInt(line_M[1]);
            list[x].add(y);
            list[y].add(x);
        }

        dfs(start, 0);
        System.out.println(rst==Integer.MAX_VALUE?-1:rst);
    }

    private static void dfs(int n, int depth) {
        if(n==end) {
            rst = Math.min(depth, rst);
        }

        for(int i=0; i<list[n].size(); i++) {
            int next = list[n].get(i);
            if(!visited[next]) {
                visited[next] = true;
                dfs(next, depth+1);
                visited[next] = false;
            }
        }
    }
}