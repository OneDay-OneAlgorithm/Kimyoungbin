package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// LIS + Binary Search
// N이 크므로 이진 탐색을 이용한 LIS
public class BOJ12738 {
    static int N;
    static int[] arr, dp;
    static int end = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();

        dp[end] = arr[0];
        for(int i=1; i<N; i++) {
            if(arr[i]>dp[end]) {
                dp[++end] = arr[i];
            } else {
                dp[binarySearch(arr[i])] = arr[i];
            }
        }

        System.out.println(end+1);
    }

    // 0~end 인덱스 중에서 n값이 들어갈 적당한 위치 찾기
    private static int binarySearch(int n) {
        int l = 0, r = end;
        int rst = Integer.MAX_VALUE;
        while(l<=r) {
            int mid = (l+r)/2;
            if(dp[mid]>=n) {
//                System.out.println("dp:"+dp[mid]);
//                System.out.println("n = " + n);
                rst = Math.min(rst, mid);
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return rst;
    }
}