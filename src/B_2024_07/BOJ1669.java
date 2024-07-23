package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// binary search
public class BOJ1669 {
    static int X, Y;
    static long rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        X = Integer.parseInt(line_1[0]);
        Y = Integer.parseInt(line_1[1]);
        int inc = Y-X;

        if(inc==0) {
            System.out.println(0);
            return;
        }

        int l=0, r=100_000;
        while(l<=r) {
            int mid = (l+r)/2; // 증가하는 최대 높이
            long max = getMax(mid); // 늘어나는 키
            if(max<=inc) {
                rst = Math.max(rst, mid);
                l = mid+1;
            } else {
                r = mid-1;
            }
        }

//        System.out.println(rst); // 증가하는 최대 높이
//        System.out.println(getTime(rst)); // 최대 높이까지 찍고 내려오는 시간
//        System.out.println(getExtraTime(inc-getMax(rst), rst)); // 남은 키
        System.out.println(getTime(rst)+getExtraTime(inc-getMax(rst), rst)); // 남은 키 채우기
    }

    // 키 n이 더 필요할 때 필요한 일수. (h = 최대 높이)
    private static long getExtraTime(long n, long h) {
        if(n%h==0) return n/h;
        else return n/h+1;
    }

    // 높이 n까지 단순 증가-감소했을 때 늘어나는 키의 총합
    private static long getMax(long n) {
        return n*(n-1)+n;
    }

    // 높이 n까지 단순 증가-감소했을 때 걸리는 일수
    private static long getTime(long n) {
        return 2*n-1;
    }
}