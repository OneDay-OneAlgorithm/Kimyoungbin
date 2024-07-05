package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// WA1) 자료형 범위 초과
public class BOJ13702 {
    static int N, M, K;
    static long l, r; // l,r 은 용량
    static int[] arr;
    static long maxml;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 주전자 개수
        K = Integer.parseInt(line_1[1]); // 친구 명수
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            r = Math.max(arr[i], r);
        }

        l = 1;
        while(l<=r) {
            long mid = (l+r)/2;
            if(getDrinkCount(mid)>=K) { // K명보다 많은 사람에게 나눠줄 수 있다면
                l = mid+1;
                maxml = Math.max(maxml, mid);
            } else {
                r = mid-1;
            }
        }

        System.out.println(maxml);
    }

    private static int getDrinkCount(long amount) {
        int rst = 0;
        for(int i=0; i<N; i++) {
            rst += arr[i]/amount;
        }
        return rst;
    }
}