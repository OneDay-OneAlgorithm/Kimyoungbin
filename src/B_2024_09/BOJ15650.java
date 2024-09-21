package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15650 {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        dfs(0, 1);

        System.out.println(sb);
    }

    // cur: 인덱스, depth: 숫자
    private static void dfs(int cur, int depth) {
        if(cur==M) {
            for(int i=0; i<M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        if(depth>N) {
            return;
        }
        arr[cur]=depth;

        dfs(cur+1, depth+1); // depth 고를 경우
        dfs(cur, depth+1); // depth 안고를 경우
    }
}
