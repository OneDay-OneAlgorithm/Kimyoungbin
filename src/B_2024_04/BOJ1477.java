package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// search) 거리로 매개변수 탐색?
// 1. TLE) 매개변수 범위 설정 오류 (l=0으로 잡음)
// 2. WA) N이 0일경우 arr이 존재하지 않으므로, rst가 0(초기값)이 되어버려 답이 무조건 0이 나오게 됨
// 3. TLE) N이 0일경우 line_2 입력받는 부분을 제거해야 함
public class BOJ1477 {
    static int N, M, L;
    static int[] arr;
    static int rst; // 최대 구간 길이의 최소값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 현재 휴게소 개수
        M = Integer.parseInt(line_1[1]); // 지으려는 휴게소 개수
        L = Integer.parseInt(line_1[2]); // 고속도로 길이
        arr = new int[N];
        if(N>0) { // 3. TLE
            String[] line_2 = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(line_2[i]);
            }
        }
        Arrays.sort(arr);
        rst = L; // 2. WA
        for(int i=0; i<N-1; i++) {
            rst = Math.max(rst, arr[i+1]-arr[i]);
        }

        // 매개변수 탐색
        int l = 1, r = L; // 1. TLE
        while(l<=r) {
            int mid = (l+r)/2;
//            System.out.println("mid = "+mid+", isValid = "+isValid(mid));
            if(isValid(mid)) {
                r = mid-1;
                rst = Math.min(rst, mid);
            } else {
                l = mid+1;
            }
        }

        System.out.println(rst);
    }

    // 휴게소간 최대간격이 dist인것이 가능한지 -> 양 끝(0, L)을 생각하여 사이에 휴게소를 최소 몇개 지어야 하는지를 측정
    private static boolean isValid(int dist) {
        int cnt = 0;
        int[] tmp = new int[N+2];
        for(int i=1; i<=N; i++) {
            tmp[i] = arr[i-1];
        }
        tmp[0] = 0;
        tmp[N+1] = L;
        for(int i=0; i<=N; ) { // 휴게소
            if(tmp[i+1]-tmp[i]>dist) { // 중간에 휴게소가 필요한 경우
                tmp[i]+=dist;
                cnt++;
            } else {
                i++;
            }
        }

        return cnt<=M;
    }
}