package B_2024_10;

import java.io.*;
import java.util.*;

// search)모름
public class BOJ14179 {
    static int H, W;
    static int[] arr;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();

        for(int i=1; i<W-1; i++) {
            int left = 0;
            int right = 0;

            for(int j = 0; j < i; j++) {
                left = Math.max(arr[j], left);
            }

            for(int j=i+1; j<W; j++) {
                right = Math.max(arr[j], right);
            }

            if(arr[i] < left && arr[i] < right)
                rst += Math.min(left, right) - arr[i];
        }
        System.out.println(rst);
    }
}
