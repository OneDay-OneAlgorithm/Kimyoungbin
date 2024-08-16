package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15810 {
    static int N, M;
    static int[] A;
    static long minTime = 1_000_000L*1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();

        long l=0, r=1_000_000L*1_000_000; // 시간
        while(l<=r) {
            long mid = (l+r)/2;
            long balloon = getBalloon(mid);
            if(balloon>=M) {
                minTime = Math.min(minTime, mid);
                r = mid-1;
            } else {
                l = mid+1;
            }
        }

        System.out.println(minTime);
    }

    private static long getBalloon(long time) {
        long count = 0;
        for(int i=0; i<N; i++) {
            count += time/A[i];
        }
        return count;
    }
}