package B_2024_08;

import java.io.*;
import java.util.*;

public class BOJ15729 {
    static int N, cnt=0;
    static int[] ans, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        ans = new int[N];
        for(int i=0; i<N; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            if(ans[i]!=arr[i]) {
                convert(i);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static void convert(int s) {
        for(int i=s; i<s+3; i++) {
            if(i<N) {
                arr[i] = arr[i]==1?0:1;
            }
        }
    }
}