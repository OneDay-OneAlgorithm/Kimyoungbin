package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// 단순 반복문을 돌리면 중간에 끼우는 케이스를 생각할 수 없다.
// 배열 크기가 최대 50이므로 arr[0]부터 arr[N-1]까지 각각을 시작으로하는 연속된 5개수가 배열에 포함되어있나 확인
public class BOJ1337 {
    static int N;
    static int[] arr;
    static HashSet<Integer> set = new HashSet<>();
    static int rst = 5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            set.add(arr[i]);
        }

        for(int i=0; i<N; i++) {
            int cnt = 0;
            for(int j=arr[i]; j<arr[i]+5; j++) {
                if(!set.contains(j)) {
                    cnt++;
                }
            }
            rst = Math.min(cnt, rst);
        }

        System.out.println(rst);
    }
}