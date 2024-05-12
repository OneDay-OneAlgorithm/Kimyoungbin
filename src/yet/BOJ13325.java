package yet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// k<=20이므로 완전탐색해도 될것같은데, (2^21-1)이 2백만정도니까
public class BOJ13325 {
    static long max = Integer.MIN_VALUE;
    static int k, N; // 높이, 노드개수
    static int[] edge; // arr[i]: 노드 i의 부모노드(i/2)로부터 i로 오는 엣지의 가중치
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        N = (int) Math.pow(2, k+1)-1;
        edge = new int[N+1];
        String[] line_2 = br.readLine().split(" ");
        for(int i=2; i<=N; i++) {
            edge[i] = Integer.parseInt(line_2[i-2]);
        }

        dfs(1, 0);

        long leaf = (long) Math.pow(2, k);
        System.out.println(leaf*max);
    }


    private static void dfs(int idx, int w) {
        if(idx*2>N) {
            max = Math.max(max, w);
            return;
        }
        dfs(idx*2, w+edge[idx*2]);
        dfs(idx*2+1, w+edge[idx*2+1]);
    }
}