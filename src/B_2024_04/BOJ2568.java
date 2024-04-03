package B_2024_04;

import java.io.*;
import java.util.HashSet;

// LIS+이분탐색+역추적
// WR1) 초기조건 설정 오류
public class BOJ2568 {
    static int N; // 전기줄의 개수
    static int[] arr, dp, index;
    static int start = 1, end;
    static HashSet<Integer> rstSet = new HashSet<>(); // 교차되지 않게 한 전깃줄 인덱스 모음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[500001];
        dp = new int[500001];
        index = new int[500001];

        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            arr[Integer.parseInt(line_N[0])] = Integer.parseInt(line_N[1]);
        }

        // 초기조건 설정 (arr[1]에 무조건 값이 들어가지 않으므로, dp[1] = arr[1]이 아니다)
        for(int i=1; i<=500000; i++) {
            if(arr[i]!=0) {
                start = i;
                break;
            }
        }
        end = 1;
        dp[1] = arr[start];

        // dp 갱신, index 갱신
        for(int i=1; i<=500000; i++) {
            if(arr[i]==0) continue; // 전깃줄 연결 X
            if(arr[i]>dp[end]) {
                end++;
                dp[end] = arr[i];
                index[i] = end;
            } else {
                int idx = binarySearch(i);
                dp[idx] = arr[i];
                index[i] = idx;
            }
        }

        // 연결된 전깃줄 구하기
        int next = end;
        for(int i=500000; i>=1; i--) {
            if(index[i]!=0 && index[i]==next) {
                rstSet.add(i);
                next--;
            }
        }

        // 답 출력
        sb.append(N - end).append("\n");
        for(int i=1; i<=500000; i++) {
            if(arr[i]!=0 && !rstSet.contains(i)) {
                sb.append(i).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int binarySearch(int target) {
        int l = 1, r = end;
        int rst = Integer.MAX_VALUE;

        while(l<=r) {
            int mid = (l+r)/2;
            if(dp[mid]>=arr[target]) {
                r = mid-1;
                rst = Math.min(rst, mid);
            } else {
                l = mid+1;
            }
        }

        return rst;
    }
}