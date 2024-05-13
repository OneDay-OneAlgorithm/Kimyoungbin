package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ14002 {
    static int N;
    static int[] arr, dp, idx, rst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        dp = new int[N];
        idx = new int[N];
        rst = new int[N];

        int end = 0;
        for(int i=0; i<N; i++) {
            int target = arr[i];
            if(end==0) { // 처음에 초기화
                idx[i] = end;
                dp[end++] = target;
            } else if(target>dp[end-1]) { // end 갱신
                idx[i] = end;
                dp[end++] = target;
            } else {
                for(int j=0; j<=end; j++) { // 들어갈 수 있는 위치 탐색
                    if(dp[j]>=target) {
                        idx[i] = j;
                        dp[j] = target;
                        break;
                    }
                }
            }
        }

        System.out.println(end);
        int maxIdx = end-1;
        for(int i=N-1; i>=0; i--) {
            if(idx[i]==maxIdx) {
                rst[maxIdx] = arr[i];
                maxIdx--;
            }
        }

        for (int i=0; i<end; i++) {
            System.out.print(rst[i]+" ");
        }
    }
}