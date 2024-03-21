package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ15664
{
    static int N, M;
    static int[] arr;
    static int[] rst;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        rst = new int[M];
        visited = new boolean[N];

        Arrays.sort(arr);

        dfs(0, 0);
    }

    private static void dfs(int depth, int idx) {
        if(depth==M) {
            for(int i : rst) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        int past = 0; // 같은 레벨에서 두번 연속으로 dfs하지 않도록 방지
        for(int i=idx; i<N; i++) {
            if(past!=arr[i] && !visited[i]) {
                visited[i] = true;
                rst[depth] = arr[i];
                past = arr[i];
                dfs(depth+1, i);
                rst[depth] = 0;
                visited[i] = false;
            }
        }
    }
}