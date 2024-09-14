package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014 {
    static int F,S,G,U,D;
    static Queue<Integer> queue;
    static int[] dp;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken()); // 1~F층
        S = Integer.parseInt(st.nextToken()); // 시작 위치
        G = Integer.parseInt(st.nextToken()); // 목표 층
        U = Integer.parseInt(st.nextToken()); // 위로 U층
        D = Integer.parseInt(st.nextToken()); // 아래로 D층

        dp = new int[F+1];
        visited = new boolean[F+1];

        int rst = bfs(S);
        System.out.println(rst!=-1?rst:"use the stairs");
    }

    private static int bfs(int start) {
        queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        dp[start] = 0;

        while(!queue.isEmpty()) {
            int cur = queue.remove();

            if(cur+U<=F && !visited[cur+U]) {
                dp[cur+U] = dp[cur]+1;
                visited[cur+U] = true;
                queue.add(cur+U);
            }
            if(cur-D>=1 && !visited[cur-D]) {
                dp[cur-D] = dp[cur]+1;
                visited[cur-D] = true;
                queue.add(cur-D);
            }
        }

        return visited[G]?dp[G]:-1;
    }
}