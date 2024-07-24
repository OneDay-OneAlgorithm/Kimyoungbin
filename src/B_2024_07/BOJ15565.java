package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// WA1) 그런 집합이 없을경우 -1 출력
public class BOJ15565 {
    static int N, K;
    static int[] arr;
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        K = Integer.parseInt(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();

        int l=0, r=0, cnt=0;
        if(arr[0]==1) cnt++;
        while(r<N) {
            if(cnt<K) {
                r++;
                if(r<N && arr[r]==1) {
                    cnt++;
                }
            } else if(cnt==K){
                if(arr[l]==1) {
                    cnt--;
                }
                rst = Math.min(rst, r-l+1);
                l++;
            }
        }

        System.out.println(rst!=Integer.MAX_VALUE?rst:-1);
    }
}