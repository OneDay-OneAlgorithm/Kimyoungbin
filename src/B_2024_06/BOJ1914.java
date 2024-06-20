package B_2024_06;

import java.io.*;
import java.math.BigInteger;

// dp[n] = dp[n-1]*2+1
// WA1) BufferedWriter와 println 혼용
public class BOJ1914 {
    static int N;
    static BigInteger[] dp;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new BigInteger[N+1];
        dp[1] = new BigInteger("1");
        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-1].multiply(new BigInteger("2")).add(new BigInteger("1"));
        }

        bw.write(dp[N]+"\n");
        bw.flush();
        if(N<=20) {
            hanoi(N, 1, 2, 3);
        }

        bw.flush();
        bw.close();
    }

    // base에서 target으로 이동시킴
    private static void hanoi(int n, int base, int via, int target) throws IOException {
        if(n==1) {
            bw.write(base+" "+target+"\n");
            return;
        }

        hanoi(n-1, base, target, via);
        bw.write(base+" "+target+"\n");
        hanoi(n-1, via, base, target);

    }

}