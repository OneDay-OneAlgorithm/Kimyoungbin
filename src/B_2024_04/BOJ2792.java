package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// M가지 보석, N명의 학생
// 보석 최대개수의 최소값을 구해야 함 - 최대개수를 기준으로 매개변수 탐색
public class BOJ2792 {
    static int N, M;
    static int[] jewel;
    static int max = Integer.MIN_VALUE;
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 학생
        M = Integer.parseInt(line_1[1]); // 보석
        jewel = new int[M];
        for(int i=0; i<M; i++) {
            jewel[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, jewel[i]);
        }

        int l=1, r=max;
        while(l<=r) {
            int mid = (l+r)/2;
            if(isAvailable(mid)) {
                rst = Math.min(rst, mid);
                r = mid-1;
            } else {
                l = mid+1;
            }
        }

        System.out.println(rst);
    }

    // 최대값이 n이하가 되도록 보석을 나눌 수 있는지
    private static boolean isAvailable(int n) {
        int needStudent = 0;
        for(int i=0; i<M; i++) {
            needStudent += jewel[i]%n==0?(jewel[i]/n):(jewel[i]/n)+1; // jewel[i]개 보석을 최대값 n이하가 되도록 나눠주려면 jewel[i]/n(올림)명이 필요하다.
        }
        return needStudent<=N;
    }
}