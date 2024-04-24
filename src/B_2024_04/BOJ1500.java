package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1500 {
    static long S, K;
    static long rst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        S = Long.parseLong(line_1[0]);
        K = Long.parseLong(line_1[1]);

        long std = S/K;
        long nmg = S%K;

        System.out.println((long) (Math.pow(std, K-nmg)*Math.pow(std+1, nmg)));
    }
}