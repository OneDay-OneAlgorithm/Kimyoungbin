package B_2024_03;

import java.io.*;
import java.util.Stack;

// lis(dp)+binarysearch+역추적(search)
// search) index와 스택을 사용한 역추적
public class BOJ14003_2 {
    static int N;
    static int[] arr;
    static int[] dp; // arr값 정보
    static int[] index;
    static int[] rst;
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        index = new int[N];
        rst = new int[N];
        int end = 0; // 이번에 들어갈 위치
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line_2[i]);
        }

        for(int i=0; i<N; i++) {
            if(end==0) {
                dp[end] = arr[i];
                index[i] = 0;
                end++;
                continue;
            }
            if(arr[i]>dp[end-1]) { // dp 최대길이 갱신
                dp[end] = arr[i];
                index[i] = end;
                end++;
            } else {
                int curIdx = binarySearch(0, end, arr[i]); // dp에 들어갈 인덱스
                dp[curIdx] = arr[i];
                index[i] = curIdx;
            }
        }

        // 스택에 index배열 싹 넣어주고
        for(int i=0; i<N; i++) {
            stack.add(index[i]);
        }
        // 스택에서 빼면서 처음나오는 값 역순 정렬

        System.out.println(end);
        int cur = end-1;
        for(int i=N-1; i>=0; i--) {
            int pop = stack.pop();
            if(pop==cur) {
                rst[cur] = arr[i];
                cur--;
            }
        }

        for(int i=0; i<end; i++) {
            bw.write(rst[i]+" ");
        }
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