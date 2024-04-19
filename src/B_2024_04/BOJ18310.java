package B_2024_04;

import java.io.*;
import java.util.*;

// 중간좌표에서 가장 가까운 집 - 평균값으로부터 좌우탐색
// WA 1) 4, 1 1 2 10 케이스에서 답이 1, 2 모두 되지만 1이어야 함 (평균값으로 풀기 위해서는 모든 케이스에 대해서 거리를 구해주어야 하므로 TLE가 날 수밖에 없다)
// WA 2) 왜 평균값은 불가능한가? -> 반례: 5, 1 7 8 9 10 (평균값에 가까운것이 답이 아니라는 반례가 있다)
public class BOJ18310 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] line_2 = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line_2[i]);
        }
        Arrays.sort(arr);
        System.out.println(arr[(N-1)/2]);
    }
}