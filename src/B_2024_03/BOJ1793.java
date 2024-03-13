package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// 점화식을 찾아도 BigInteger가 아니면 풀기 어려움 (범위로 장난쳐놓은 문제)
// dp[0]=1, dp[1]=1, dp[2]=(dp[1])+(dp[0]*2) -> dp[n] = (dp[n-1])+(dp[n-2]*2) = dp[n-1]+2*dp[n-2]
public class BOJ1793
{
    static BigInteger[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        dp = new BigInteger[251];

        dp[0] =new BigInteger("1");
        dp[1] =new BigInteger("1");
        dp[2] =new BigInteger("3");

        for(int i=3; i<251; i++) {
            dp[i] = dp[i-1].add(dp[i-2].multiply(new BigInteger("2")));
        }

        String line = null;
        while((line=br.readLine())!=null) {
            int n = Integer.parseInt(line);
            System.out.println(dp[n]);
        }
        br.close();
    }
}

