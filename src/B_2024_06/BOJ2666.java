package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// simulation?
// 재방문을 하지 않을 확률
// WA) greedy하다는 보장이 없다. search) 완전탐색(brute force)
public class BOJ2666 {
    static int N, M;
    static int[] seq;
    static int rst = Integer.MAX_VALUE; // 이동 횟수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] line_2 = br.readLine().split(" ");
        int s1 = Integer.parseInt(line_2[0]); // 열린 벽장 작은번호
        int s2 = Integer.parseInt(line_2[1]); // 열린 벽장 큰번호
        M = Integer.parseInt(br.readLine());
        seq = new int[M];

        for(int i=0; i<M; i++) {
            seq[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, s1, s2, 0);

        System.out.println(rst);
    }

    private static void dfs(int depth, int o1, int o2, int move) {
        if(depth==M) {
            rst = Math.min(move, rst);
            return;
        }

        int cur = seq[depth];
        if(cur<o1) {
            dfs(depth+1, cur, o2, move+Math.abs(o1-cur));
        } else if(cur>o2) {
            dfs(depth+1, o1, cur, move+Math.abs(cur-o2));
        } else { // o1, o2 둘다 탐색
            dfs(depth+1, cur, o2, move+Math.abs(o1-cur));
            dfs(depth+1, o1, cur, move+Math.abs(cur-o2));
        }
    }
}
