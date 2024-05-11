package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// RE1) M=0일때 Index예외
public class BOJ2230 {
    static int N, M;
    static int[] A;
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(A);

        int l=0, r=0;
        while(l<=r && r<N) { // RE1
            int diff = A[r]-A[l];
            if(diff<M) {
                r++;
            } else {
                l++;
                rst = Math.min(rst, diff);
            }
        }

        System.out.println(rst);
    }
}