package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1번부터 증가하면서 선행작업중 가장 긴걸로 시간 세팅
public class BOJ2056 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1]; // 1~N

        for(int i=1; i<=N; i++) {
            String[] line_N = br.readLine().split(" ");
            arr[i] = Integer.parseInt(line_N[0]); // i번 작업 수행시 걸리는 시간
            int cnt = Integer.parseInt(line_N[1]);
            int maxpre = 0;
            for(int j=0; j<cnt; j++) {
                maxpre = Math.max(maxpre, arr[Integer.parseInt(line_N[2+j])]); // 가장 먼저 선행되는 작업 찾기
            }
            arr[i]+=maxpre;
        }

        int rst = Integer.MIN_VALUE;
        for(int i=1; i<=N; i++) {
            rst = Math.max(rst, arr[i]);
        }
        System.out.println(rst);
    }
}