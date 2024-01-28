package B_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1026
{
    static int N;
    static int[] A, B;
    static int S = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(n -> Integer.parseInt(n)).toArray();
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(n -> Integer.parseInt(n)).toArray();

        Arrays.sort(A);
        Arrays.sort(B);

        for(int i=0; i<N; i++) {
            S += (A[i]*B[N-1-i]);
        }

        System.out.println(S);
    }
}