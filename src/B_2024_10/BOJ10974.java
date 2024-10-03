package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10974 {
    static int N;
    static int[] rst;
    static boolean[] used;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        rst = new int[N+1];
        used = new boolean[N+1];

        dfs(0);

        System.out.println(sb);
    }

    private static void dfs(int depth) {
        if(depth==N) {
            for(int i=0; i<N; i++) {
                sb.append(rst[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=N; i++) {
            if(!used[i]) {
                rst[depth] = i;
                used[i] = true;
                dfs(depth+1);
                used[i] = false;
            }
        }
    }
}
