package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9252 {
    static int[][] dp;
    static Stack<Character> stack = new Stack<>(); // LCS를 이루는 문자열 출력
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        dp = new int[str1.length()+1][str2.length()+1];

        // LCS
        for(int i=1; i<=str1.length(); i++) {
            for(int j=1; j<=str2.length(); j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // 문자열 구하기
        int i=str1.length(), j=str2.length();
        while(i>=1 && j>=1) {
            if(dp[i][j]==dp[i-1][j]) {
                i-=1;
            } else if(dp[i][j]==dp[i][j-1]) {
                j-=1;
            } else {
                stack.add(str1.charAt(i-1));
                i-=1;
                j-=1;
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}