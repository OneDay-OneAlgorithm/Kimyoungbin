package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9372 {
    static int N, M; // 국가수, 비행기수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            String[] line_0 = br.readLine().split(" ");
            N = Integer.parseInt(line_0[0]);
            M = Integer.parseInt(line_0[1]);
            for(int i=0; i<M; i++) br.readLine();
            System.out.println(N-1);
        }
    }
}