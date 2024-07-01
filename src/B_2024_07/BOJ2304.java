package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2304 {
    static int N, max = 0;
    static int[] pillar = new int[1001];
    static long rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int L = Integer.parseInt(line_N[0]);
            int H = Integer.parseInt(line_N[1]);
            pillar[L] = H;
            max = Math.max(max, H);
        }

        int lmax = 0, rmax = 0, l, r;
        for(l=0; pillar[l]<max; l++) {
            lmax = Math.max(lmax, pillar[l]);
            rst+=lmax;
        }
        for(r=1000; pillar[r]<max; r--) {
            rmax = Math.max(rmax, pillar[r]);
            rst+=rmax;
        }
        for(int i=l; i<=r; i++) {
            rst+=max;
        }
        System.out.println(rst);
    }
}