package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 전체 가짓수: N! (N이 최대 8)
// 방문처리 완전탐색
// 자릿수 구하기 -> 54321에서 2번째 자리(1000)을 구하려면 54321/1000%10을 하면 된다. (수/자릿수%10)
// WA1) N이 3이 아니라 2부터였음
public class BOJ1327 {
    static int N, K, init;
    static boolean[] visited = new boolean[87654322];
    static Queue<Info> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        init = Integer.parseInt(br.readLine().replace(" ",""));

        System.out.println(bfs());

    }

    private static int bfs() {
        queue = new LinkedList<>();
        queue.add(new Info(init, 0));
        visited[init] = true;
        if(is오름차순(init)) {
            return 0;
        }

        while(!queue.isEmpty()) {
            Info cur = queue.poll();
            for(int i=1; i<=N-K+1; i++) {
                int next = invert(i, cur.n);
                if(is오름차순(next)) {
                    return cur.depth+1;
                }
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(new Info(next, cur.depth+1));
                }
            }
        }

        return -1;
    }

    // idx번째 자리수부터 K개를 교체
    private static int invert(int idx, int n) {
        for(int i=0; i<K/2; i++) {
            n = swap(idx+i, idx+K-1-i, n);
        }
        return n;
    }

    // n의 a번째 자리와 b번째 자리를 교체
    private static int swap(int a, int b, int n) {
        int aLoc = N-a; // 자릿수
        int bLoc = N-b;
        int a10Loc = (int) Math.pow(10, aLoc); // 기준 10배수
        int b10Loc = (int) Math.pow(10, bLoc);
        int aVal = (n/a10Loc)%10; // 해당 자리 숫자
        int bVal = (n/b10Loc)%10;

        n-=(aVal*a10Loc);
        n-=(bVal*b10Loc);
        n+=(aVal*b10Loc);
        n+=(bVal*a10Loc);
        return n;
    }

    private static boolean is오름차순(int n) {
        return n==12||n==123||n==1234||n==12345||n==123456||n==1234567||n==12345678;
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