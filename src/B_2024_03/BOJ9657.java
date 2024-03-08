package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9657
{
    static int N;
    // dp[i]: 돌 i개 남아있을때 가져가는 사람이 이기는지 지는지.
    // -> X 완벽하게 게임을 했으므로' '이길수 있는지'를 판단해야 한다.
    static boolean[] dp; // 가져가는 횟수 dp[n] = dp[n-1] || dp[n-3] || dp[n-4] 1개, 3개, 4개
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new boolean[N+1];
        // dp[1]은 승리, dp[2]부터는 승/패 모두 가능하다.
//        dp[i] = true: 이길수있음, false: 어떻게해도 못이김

//        dp[1] = true // true
//        dp[2] = dp[1]+1 // false
//        dp[3] = dp[2]+1 or 1 // true or true -> true
//        dp[4] = dp[3]+1 or dp[1]+1 or 1 // false or false or true -> true
//        dp[5] = dp[4]+1 or dp[2]+1 or dp[1]+1 // false or true or false -> true
//        dp[6] = dp[5]+1 or dp[3]+1 or dp[2]+1 // false or false or true -> true
//        dp[7] = dp[6]+1 or dp[4]+1 or dp[3]+1 // false or false or false -> false.
//        dp[8] = dp[7]+1 or dp[5]+1 or dp[4]+1 // true or false or false -> true
//        dp[9] = dp[8]+1 or dp[6]+1 or dp[5]+1 // false or false or false -> false
//        dp[10] = dp[9]+1 or dp[7]+1 or dp[6]+1 // true or true or false -> true

        System.out.println((N%7==2 || N%7==0)?"CY":"SK");
    }
}

