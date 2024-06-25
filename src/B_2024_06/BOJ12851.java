package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// dp? -> search) BFS
// WA1) while문 한번이 같은 레벨의 전부는 아니다. - while문 조건 수정
// WA2) search) 두번째 조건문 cur.n-1>=0으로 수정
public class BOJ12851 {
    static int N, K; // start, end
    static int[] dp, count; // 빠른 시간, 경우의 수
    static boolean[] visited;
    static Queue<Info> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);

        dp = new int[100001];
        count = new int[100001];
        visited = new boolean[100001];

        dp[N] = 0;
        count[N] = 1;
        visited[N] = true;
        queue.add(new Info(N, 0));

        while(!queue.isEmpty()) {
            Info cur = queue.poll();

            if(cur.n+1<=100000) {
                if(!visited[cur.n+1] || (visited[cur.n+1] && dp[cur.n+1]==cur.depth+1)) {
                    visited[cur.n+1] = true;
                    dp[cur.n+1] = cur.depth+1;
                    count[cur.n+1]++;
                    queue.add(new Info(cur.n+1, cur.depth+1));
                }
            }
            if(cur.n-1>=0) {
                if(!visited[cur.n-1] || (visited[cur.n-1] && dp[cur.n-1]==cur.depth+1)) {
                    visited[cur.n-1] = true;
                    dp[cur.n-1] = cur.depth+1;
                    count[cur.n-1]++;
                    queue.add(new Info(cur.n-1, cur.depth+1));
                }
            }
            if(cur.n*2<=100000){
                if(!visited[cur.n*2] || (visited[cur.n*2] && dp[cur.n*2]==cur.depth+1)) {
                    visited[cur.n*2] = true;
                    dp[cur.n*2] = cur.depth+1;
                    count[cur.n*2]++;
                    queue.add(new Info(cur.n*2, cur.depth+1));
                }
            }
        }

        System.out.println(dp[K]);
        System.out.println(count[K]);
    }

    static class Info {
        int n;
        int depth;
        public Info(int n, int depth) {
            this.n = n;
            this.depth = depth;
        }
    }
}