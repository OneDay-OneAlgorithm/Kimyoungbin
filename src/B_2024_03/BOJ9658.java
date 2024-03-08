package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9658
{
    static int N;
    static boolean[] dp; // 1개, 3개, 4개
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new boolean[N+1];

//        기준: i개 남았을때 그때 차례의 사람이 이길 수 있는지
//        dp[1]: false
//        dp[2]: dp[1]+1 -> true
//        dp[3]: dp[2]+1 or false -> false
//        dp[4]: dp[3]+1 or dp[1]+1 -> true
//        dp[5]: dp[4]+1 or dp[2]+1 or dp[1]+1 -> true
//        dp[6]: dp[5]+1 or dp[3]+1 or dp[2]+1 -> true
//        dp[7]: dp[6]+1 or dp[4]+1 or dp[3]+1 -> true
//        dp[8]: dp[7]+1 or dp[5]+1 or dp[4]+1 -> false

        System.out.println((N%7==3 || N%7==1)?"CY":"SK");
    }
}

