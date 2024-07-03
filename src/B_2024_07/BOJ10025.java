package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//RE1) ArrayIndexOfBounds -> K가 최대 200만 -> K가 50만 이상일 경우 모든 얼음을 포함 가능함.
public class BOJ10025 {
    static int N, K;
    static int[] arr = new int[1_000_001];
    static int rst = 0;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int g = Integer.parseInt(line_N[0]);
            int x = Integer.parseInt(line_N[1]);

            arr[x] = g;
        }

        // RE1
        if(K>=500000) {
            for(int i=0; i<=100_0000; i++) {
                rst+=arr[i];
            }
            System.out.println(rst);
            return;
        }

        // 초기 합 구하기
        for(int i=0; i<K*2+1; i++) {
            rst+=arr[i];
        }
        max = Math.max(max, rst);

        int l = 0;
        int r = K*2+1;
        for(int i=0; i<=1_000_000-(K*2+1); i++) {
            rst-=arr[l++];
            rst+=arr[r++];
            max = Math.max(max, rst);
        }

        System.out.println(max);
    }
}