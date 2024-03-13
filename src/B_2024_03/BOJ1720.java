package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1. 일단 dp로 가능한 경우수를 모두 구한다.
// n이 짝수일경우 대칭이라면 중간에 1x2짜리 2개가 올경우, 2x1짜리 2개가 올경우, 2x2짜리 1개가 올경우 3가지 경우의 수가 있고,
// n이 홀수일경우 대칭이라면 무조건 중간에 1x2짜리 1개가 와야한다.
// 2. n이 짝수일 경우 dp[n] - dp[(n-2)/2]*3
// 3. n이 홀수일 경우 dp[n] - dp[(n-1)/2]
// dp[0]=1, dp[1]=1, dp[2]=(dp[1])+(dp[0]*2) -> dp[n] = (dp[n-1])+(dp[n-2]*2) = dp[n-1]+2*dp[n-2]

// search) 전체 가능한 경우의 수(dp)에서, 완전 대칭인 경우를 빼면 대칭이 아닌 경우(dp2)가 나온다.
// dp[i] = dp[i-1] + dp[i-2]
// 완전 대칭인 경우는 dp[i]일때 i가 짝수일 경우 가운데 2x2 1개나 2x1이 2개 들어가는 경우 (dp[(i-2)/2])*2, 아니면 가운데 갈라지는경우 (dp[i/2]) 존재
// i가 홀수일 경우 가운데 1x2이 1개 들어가는 경우(dp[(i-1)/2]) 존재
// 짝수일 경우, dp2[i] = dp[i] - dp[i/2] - dp[i/2-1]*2
// 홀수일 경우, dp2[i] = dp[i] - dp[(i-1)/2]

// https://beginthread.tistory.com/145
// search 2) 좌우반전동일이랑 대칭(pallendrome)을 구분해야함. 문제 예시를 보면 알듯이 팰린드롬이 조건이 아님
// dp[i]=k라고 했을때, k개의 경우의수 중에는 좌우대칭인것 x개, 대칭아닌것 y개가 존재한데, 이때 좌우대칭이 아닌것 y개는 반드시 좌우반전이 가능하다.
// 따라서 k-(y/2)가 좌우반전 중복을 제외한 개수. 우리가 구하려는 답이다.
// 이때 우리는 팰린드롬의 개수(x)는 구할 수 있다. 따라서 k-x = y이므로 k-((k-x)/2) = (k+x)/2가 우리가 구하려는 답이다.
public class BOJ1720
{
    static int N;
    static int[] dp; // 좌우반전 + 좌우대칭
    static int[] dp2; // 좌우대칭 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[31];
        dp2 = new int[31];
        dp[0] = 1; dp[1] = 1; dp[2] = 3;
        dp2[0] = 1; dp2[1] = 1; dp2[2] = 3;
        for(int i=3; i<31; i++) {
            dp[i] = dp[i-1] + 2*dp[i-2];
        }
        for(int i=3; i<31; i++) {
            dp2[i] = i%2==0 ? dp[i/2] + dp[i/2-1]*2 : dp[(i-1)/2];
        }
        System.out.println((dp[N] + dp2[N]) / 2);
    }
}

