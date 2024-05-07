package S_2024_05;

import java.io.*;
import java.util.*;

public class 성적_평균 {
    static int N, K; // 학생수, 구간수
    static int[] arr;
    static int[] sum; // 누적합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        arr = new int[N+1];
        sum = new int[N+1];
        String[] line_2 = br.readLine().split(" ");
        sum[0] = 0;
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(line_2[i-1]);
            sum[i] = sum[i-1]+arr[i];
        }

        for(int i=0; i<K; i++) {
            String[] line_K = br.readLine().split(" ");
            int start = Integer.parseInt(line_K[0]);
            int end = Integer.parseInt(line_K[1]);

            //start~end: sum[end]-sum[start-1]
            System.out.println((sum[end]-sum[start-1])/(double)(end-start+1));
        }
    }
}
