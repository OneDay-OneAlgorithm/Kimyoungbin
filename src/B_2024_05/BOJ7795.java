package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ7795 {
    static int N, M;
    static int[] A, B;
    static int rst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            String[] line_1 = br.readLine().split(" ");
            N = Integer.parseInt(line_1[0]);
            M = Integer.parseInt(line_1[1]);
            A = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            B = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            Arrays.sort(A);
            Arrays.sort(B);

            rst = 0;
            int j = 0;
            for(int i=0; i<N; i++) {
                while(j<M && A[i]>B[j]) {
                    j++;
                }
                rst+=j;
            }

            System.out.println(rst);
        }
    }
}