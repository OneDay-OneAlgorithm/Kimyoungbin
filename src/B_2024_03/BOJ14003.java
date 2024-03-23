package B_2024_03;

import java.io.*;

// lis(dp)+binarysearch+역추적(search)
public class BOJ14003 {
    static int N;
    static int[] arr;
    static int[] dp; // arr값 정보
    static int[] track; // 역추적
    static int[] cur; // dp의 실제 arr 인덱스 정보
    static int[] rst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        track = new int[N];
        cur = new int[N];
        rst = new int[N];
        int end = 0; // 이번에 들어갈 위치
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line_2[i]);
        }

        for(int i=0; i<N; i++) {
            if(end==0) {
                dp[end] = arr[i];
                track[i] = -1;
                cur[end++] = i;
                continue;
            }
            if(arr[i]>dp[end-1]) { // dp 최대길이 갱신
                dp[end] = arr[i];
                cur[end] = i;
                track[i] = cur[end-1];
                end++;
            } else {
                int curIdx = binarySearch(0, end, arr[i]); // dp에 들어갈 인덱스
                dp[curIdx] = arr[i];
                cur[curIdx] = i;
                track[i] = curIdx==0?-1:cur[curIdx-1];
            }
        }

//        for (int i : dp) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//
//        for (int i : track) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//
//        for (int i : cur) {
//            System.out.print(i+" ");
//        }
//        System.out.println();

        // 틀린 출력방법
//        for(int i=0; i<end; i++) {
//            System.out.print(arr[cur[i]]+" ");
//        }

        bw.write(end+"\n");
        int curIdx = end-1;
        int tmpend = end-1;
        tmpend = cur[tmpend];
        rst[curIdx--] = arr[tmpend];
        // curIdx>=0 조건 빼면 왜 오류나지? tmpend>=0이면서 curIdx=-1인경우 가 있다는거잖아. 다시말해서 end번 넘게 돌았는데 tmpend가 -1이 안나오는경우가 있다는거네?
        while(curIdx>=0 && tmpend>0) {
//            System.out.println("tmpend = " + tmpend);
//            System.out.println("curIdx = " + curIdx);
            tmpend = track[tmpend];
            rst[curIdx--] = arr[tmpend];
//            System.out.println(arr[tmpend]);
        }
        for(int i=0; i<end; i++) {
            bw.write(rst[i]+" ");
        }
        bw.write('\n');
        bw.flush();
        bw.close();
    }

    // 들어갈 인덱스 반환
    // target: 구하려는 인덱스값
    // l<=i<=r 범위에서 dp[i]>=target이 되는 최소 i 찾기
    private static int binarySearch(int l, int r, int target) {
        int rst = Integer.MAX_VALUE;
        while(l<=r) {
            int mid = (l+r)/2;
            if(dp[mid]>=target) {
                r = mid-1;
                rst = Math.min(rst, mid);
            } else {
                l = mid+1;
            }
        }
        return rst;
    }
}