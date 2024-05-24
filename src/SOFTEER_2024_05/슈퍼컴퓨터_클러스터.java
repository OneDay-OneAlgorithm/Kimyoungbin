package SOFTEER_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 파라미터 탐색: 최소cost
// WA) 타입오류 or r 범위문제
public class 슈퍼컴퓨터_클러스터 {
    static long N, B;
    static long[] arr;
    static long rst = 0; // 최저 성능
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Long.parseLong(line_1[0]);
        B = Long.parseLong(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(s->Long.parseLong(s)).toArray();

        long l=0, r=2_000_000_002;
        while(l<=r) {
            long mid = (l+r)/2L;
            long cost = getCost(mid);
            if(cost<=B) {
                l = mid+1;
                rst = Math.max(rst, mid);
            } else {
                r = mid-1;
            }
        }

        System.out.println(rst);
    }

    // 모든 컴퓨터를 target성능으로 맞추는 예산이 B를 초과하지는 않는지
    private static long getCost(long target) {
        long rst = 0;
        for(int i=0; i<N; i++) {
            // 성능 향상이 필요한 경우
            if(arr[i]<target) {
                rst+=(long) Math.pow(target-arr[i], 2);
            }
            if(rst>B) {
                break;
            }
        }

        return rst;
    }
}
