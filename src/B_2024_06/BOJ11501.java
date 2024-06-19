package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// WA) 출력 64bit -> long 사용
public class BOJ11501 {
    static int N;
    static int[] arr, std;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            std = new int[N];
            int max = 0;
            for(int i=N-1; i>=0; i--) {
                max = Math.max(max, arr[i]);
                std[i] = max;
            }
            long sum = 0;
            for(int i=0; i<N; i++) {
                sum+=std[i]-arr[i];
            }
            System.out.println(sum);
        }
    }

}
