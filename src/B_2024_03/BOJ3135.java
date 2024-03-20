package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N에서 가는방법, N을 안누르고 A에서 B로 증/감만 통해서 가는방법
public class BOJ3135
{
    static int A, B, N;
    static int[] arr;
    static int[] go;
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        A = Integer.parseInt(line_1[0]);
        B = Integer.parseInt(line_1[1]);
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        go = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            go[i] = Math.abs(B-arr[i]);
            rst = Math.min(rst, go[i]+1);
        }
        rst = Math.min(rst, Math.abs(B-A));
        System.out.println(rst);
    }
}