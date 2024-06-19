package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 딱봐도 DP
public class BOJ15486 {
    static int N; // 0~N-1일차
    static int[][] info;
    static int[] dp; // dp[i]: 0~i일까지 지났을 때 최대로 받을 수 있는 보수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = new int[N][3];
        dp = new int[N+1];
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            info[i][0] = i; // 시작일
            info[i][1] = i+Integer.parseInt(line_N[0]); // 종료일
            info[i][2] = Integer.parseInt(line_N[1]); // 급여
        }

        // 끝나는 시간 순 정렬
        Arrays.sort(info, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });

        int idx = 0;
        dp[0] = 0;
        for(int i=1; i<=N; i++) {
            dp[i] = dp[i-1];
            // i일에 끝나는 일들 검사
            while(idx<N && info[idx][1]==i) {
//                System.out.println("info[idx][2] = " + info[idx][2]);
                dp[i] = Math.max(dp[i], dp[info[idx][0]]+info[idx][2]);
                idx++;
            }
        }

//        for(int i=0; i<N; i++) {
//            System.out.println("dp["+i+"] = " + dp[i]);
//        }

        System.out.println(dp[N]);
    }
}
