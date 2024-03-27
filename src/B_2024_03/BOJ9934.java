package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 일차원 배열 사용해서 트리 풀어보기
public class BOJ9934 {
    static int K, N;
    static int[] tree;
    static int seq = 0;
    static int rstIdx = 1;
    static String[] line_2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        K = Integer.parseInt(line_1[0]);
        N = (int) Math.pow(2, K)-1;
        tree = new int[N+1];
        line_2 = br.readLine().split(" ");

        // tree 초기화
        dfs(1);

        for(int i=0; i<K; i++) {
            for(int j=0; j<(int)Math.pow(2, i); j++) {
                System.out.print(tree[rstIdx++]+" ");
            }
            System.out.println();
        }
    }

    private static void dfs(int index) {
        if(index>N) {
            return;
        }

        dfs(index*2);
        tree[index] = Integer.parseInt(line_2[seq++]);
        dfs(index*2+1);
    }
}