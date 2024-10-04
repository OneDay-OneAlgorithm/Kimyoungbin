package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// parametric search
// RE1) l=0->1로 수정 (0나누기 방지)
public class BOJ16401 {
    static int M, N;
    static int[] arr;
    static int maxLen = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();

        int l=1, r=1_000_000_000;
        while(l<=r) {
            int mid = (l+r)/2;
            int cur = getParts(mid);
            if(cur>=M) {
                maxLen = mid;
                l = mid+1;
            } else {
                r = mid-1;
            }
        }

        System.out.println(maxLen);
    }

    // 길이 std인 과자를 몇개나 만들 수 있는지 구하기
    private static int getParts(int std) {
        int rst = 0;
        for(int i=0; i<N; i++) {
            rst+=arr[i]/std;
        }
        return rst;
    }
}
