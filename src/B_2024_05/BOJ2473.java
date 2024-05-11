package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// search) 간단하게 N이 최대 5000이므로 N 하나를 고정시켜둔채 두 용액과 똑같이 풀어도 된다
// 수의 최대값이 30억이므로 long 사용
// WA1) 오버플로
public class BOJ2473 {
    static int N;
    static long[] arr;
    static int[] rstArr = new int[3];
    static long rst = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(s -> Long.parseLong(s)).toArray();
        Arrays.sort(arr);

        // solution함수에서 idx와 l, r이 겹치지 않도록 해야 함
        for(int i=1; i<N-1; i++) {
            Info info = solution(i);
            if(rst>info.sum) {
                rst = info.sum;
                rstArr[0] = info.l;
                rstArr[1] = info.r;
                rstArr[2] = i;
            }
        }

        Arrays.sort(rstArr);
        System.out.println(arr[rstArr[0]]+" "+arr[rstArr[1]]+" "+arr[rstArr[2]]);
    }

    // 한 용액의 인덱스가 idx일때 0에 가장 가까운 정보(l, r, absval) 반환
    // idx와 l, r이 겹치지 않도록 해야 함
    private static Info solution(int idx) {
        Info info = new Info(-1, -1, -1);
        int l=0, r=N-1;
        long rst = Long.MAX_VALUE;
        while(l<r) {
            long val = arr[l]+arr[r]+arr[idx]; // WA1) val은 long이지만 우항은 int라 overflow 가능
            if(rst>Math.abs(val)) {
                info = new Info(l, r, Math.abs(val));
                rst = Math.abs(val);
            }
            if(val>0) {
                r--;
            } else if(val<0) {
                l++;
            } else if(val==0) {
                return new Info(l, r, 0);
            }

            // l, r이 겹칠경우
            if(l==idx) l++;
            if(r==idx) r--;
        }

        return info;
    }

    static class Info {
        int l;
        int r;
        long sum; // 절대값

        public Info(int l, int r, long sum) {
            this.l = l;
            this.r = r;
            this.sum = sum;
        }
    }
}