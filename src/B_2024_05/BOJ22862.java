package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 짝수 수열 길이의 최대값
public class BOJ22862 {
    static int N, K;
    static boolean[] arr; // 짝수:true, 홀수:false
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        arr = new boolean[N];
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line_2[i])%2==0;
        }

        int l=0, r;
        while(l<N && !arr[l]) l++; // 초반 홀수 skip
        r = l;

        int cnt = 0;
        int len = 0;
        int max = 0;
        while(r<N) {
            if(arr[r]) { // r이 짝수
                len++;
                r++;
            } else { // 다음수가 홀수
                if(cnt<K) { // cnt 소모 가능
                    cnt++;
                    r++;
                } else { // cnt 소모불가 -> l 이동
                    if(arr[l]) {
                        len--;
                    } else {
                        cnt--;
                    }
                    l++;
                }
            }
            max = Math.max(max, len);
        }
        System.out.println(max);
    }
}