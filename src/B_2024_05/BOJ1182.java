package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// N=20, 투포인터+누적합
// WA) 부분수열(subsequence)이란, 원소가 연속하지 않게 뽑는 경우도 포함하는 용어입니다. (https://www.acmicpc.net/board/view/34824)
// WA2) sum==S라고 return해버리면 뒤에 합이 0이 되는케이스를 구하지 못함
// WA3) 공집합 (S가 0일경우, dfs하자마자 return되는 케이스 제외)
public class BOJ1182 {
    static int N, S, rst = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        S = Integer.parseInt(line_1[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();

        dfs(0, 0);

        System.out.println(S!=0?rst:rst-1); //WA3
    }

    private static void dfs(int sum, int start) {
        if(sum==S) {
            rst++;
//            return; // WA2
        }
        if(start==N) {
            return;
        }

        for(int i=start; i<N; i++) {
            dfs(sum+arr[i], i+1);
        }
    }
}