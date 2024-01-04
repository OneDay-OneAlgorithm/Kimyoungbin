package B_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ13458
{
    static int N, B, C;
    static int[] A;
    static long rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(line_2[i]);
        }
        String[] line_3 = br.readLine().split(" ");
        B = Integer.parseInt(line_3[0]);
        C = Integer.parseInt(line_3[1]);

        for(int i=0; i<N; i++) {
            A[i]-=B; rst++;
            if(A[i]<0) continue;
            if(A[i]%C==0)
                rst += (A[i]/C);
            else
                rst += (A[i]/C+1);
        }

        System.out.println(rst);
    }
}