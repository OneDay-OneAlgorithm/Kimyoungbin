package SOFTEER_2024_05;

import java.io.*;
import java.util.Arrays;

public class 자동차_테스트 {
    static int n, q, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line_1 = br.readLine().split(" ");
        n = Integer.parseInt(line_1[0]);
        q = Integer.parseInt(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        Arrays.sort(arr);

        for(int i=0; i<q; i++) {
            m = Integer.parseInt(br.readLine());
            int idx = binarySearch(m);
            // 중앙값이 배열에 없을 경우
            if(idx==-1) {
                bw.write(0+"\n");
                continue;
            }
            bw.write(idx*(n-idx-1)+"\n");
        }
        bw.flush();
        bw.close();
    }

    // target의 인덱스 찾기
    private static int binarySearch(int target) {
        int l=0, r=n-1;
        while(l<=r) {
            int mid = (l+r)/2;
            if(arr[mid]==target) {
                return mid;
            } else if(arr[mid]<target) {
                l = mid+1;
            } else if(arr[mid]>target) {
                r = mid-1;
            }
        }
        return -1;
    }
}
