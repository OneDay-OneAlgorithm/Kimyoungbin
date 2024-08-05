package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// WA1)
public class BOJ23085 {
    static int N, K, cnt=0;
    static boolean[] dp;
    static Queue<Info> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new boolean[N+1]; // dp[i]: i개의 동전을 앞면(H)으로 만들수있는 최소 횟수
        String[] S = br.readLine().split("");
        for (String s : S) {
            if(s.equals("H")) cnt++;
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        queue = new LinkedList<>();
        dp[cnt] = true;
        queue.add(new Info(cnt, 0));

        while(!queue.isEmpty()) {
            Info cur = queue.remove();

            if(cur.h==0) {
                return cur.depth;
            }

            for(int i=0; i<=K; i++) {
                int head = i;
                int tail = K-i;

                if(cur.h>=head && N-cur.h>=tail) {
                    int nextHead = cur.h-head+tail;
                    if(!dp[nextHead]) {
                        queue.add(new Info(nextHead, cur.depth+1));
                        dp[nextHead] = true;
                    }
                }
            }
        }
        return -1;
    }

    static class Info {
        int h; // 앞면 개수
        int depth;
        public Info(int h, int depth) {
            this.h = h;
            this.depth = depth;
        }
    }
}