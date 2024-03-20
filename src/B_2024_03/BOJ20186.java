package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ20186
{
    static int N, K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        String[] line_2 = br.readLine().split(" ");
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line_2[i]);
        }
        Arrays.sort(arr);

        int minus = K*(K-1)/2;
        int sum = 0;
        int idx = N-1;
        for(int i=0; i<K; i++) {
            sum+=arr[idx--];
        }
        System.out.println(sum-minus);
    }
}