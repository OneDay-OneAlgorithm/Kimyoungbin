package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// TLE1) 새로 추가할때 초과 발생시 그게 나올때까지 줄여주면 된다.
public class BOJ20922 {
    static int N, K; // 수열 길이, 같은 정수 최대개수
    static int[] arr;
    static int rst = 1;
    static int[] map = new int[100001]; // key가 value개
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();

        int l=0, r=1;
        map[arr[0]]++;

        while(r<N && l<=r) {
            if(map[arr[r]]+1<=K) {
//                System.out.println("l="+l+", r="+r);
                map[arr[r]]++;
                rst = Math.max(rst, r-l+1);
                r++;
            } else {
                map[arr[l]]--;
                l++;
            }
        }

        System.out.println(rst);
    }

}
