package B_2024_03;

import java.io.*;
import java.util.Arrays;

public class BOJ15666
{
    static int N, M;
    static int[] arr;
    static int[] rst;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        rst = new int[M];

        Arrays.sort(arr);

        dfs(0, 0);

        bw.flush();
        bw.close();
    }

    private static void dfs(int depth, int idx) throws IOException {
        if(depth==M) {
            for(int i : rst) {
                bw.write(i+" ");
            }
            bw.write('\n');
            return;
        }

        int past = 0; // 같은 레벨에서 두번 연속으로 dfs하지 않도록 방지
        for(int i=idx; i<N; i++) {
            if(past!=arr[i]) {
                rst[depth] = arr[i];
                past = arr[i];
                dfs(depth+1, i);
                rst[depth] = 0;
            }
        }
    }
}