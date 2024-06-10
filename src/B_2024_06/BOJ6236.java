package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// parametric search
// WA1) 인출횟수 잘못 설정
// WA2) high 범위 잘못설정 (10000으로 설정했는데, 100000(날짜)*금액(10000)에 한번의 인출 기회만 주어질 수도 있다.
public class BOJ6236 {
    static int N, M;
    static int[] arr;
    static long rst = 100000*10000+1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        M = Integer.parseInt(line_1[1]);
        arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long l=1, r=100000*10000+1;
        while(l<=r) {
            long mid = (l+r)/2;
            if(isValid(mid)) {
                rst = Math.min(rst, mid);
                r = mid-1;
            } else {
                l = mid+1;
            }
        }

        System.out.println(rst);
    }

    // 금액제한이 K일떄 현우가 M번 이하로 인출할 수 있는지
    // (잔액이 남아있어도 인출 가능하므로, 정확히 M번이 아니어도 된다)
    private static boolean isValid(long K) {
        int 인출횟수 = 1; // WA1)
        long 잔액 = K;
        for(int i=0; i<N; i++) {
            if(arr[i]>K) return false;
            if(arr[i]>잔액) {
                인출횟수++;
                잔액 = K;
            }
            잔액 -= arr[i];
        }

        return 인출횟수<=M;
    }
}
