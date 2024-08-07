package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 많은 선물: 선물의 종류를 이야기하는듯
// WA1) int범위 초과
// WA2) https://readble-ko.tistory.com/133 -> 넣을 수 없는 아이템일때 1로 출력되는 문제
public class BOJ25947 {
    static int n, a, b;
    static long[] arr, sum, half;
    static long max = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 선물의 개수
        b = Integer.parseInt(st.nextToken()); // 예산
        a = Integer.parseInt(st.nextToken()); // 반값할인 받을 수 있는 선물의 수

        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(s->Long.parseLong(s)).toArray();
        Arrays.sort(arr);
        sum = new long[n];
        half = new long[n];
        sum[0] = arr[0];
        half[0] = arr[0]/2;

        // 누적합
        for(int i=1; i<n; i++) {
            sum[i] = sum[i-1]+arr[i];
        }
        // 반 누적합
        for(int i=1; i<n; i++) {
            half[i] = half[i-1]+(arr[i]/2);
        }


        for(int i=0; i<n; i++) {
            if(i<a) {
                if(half[i]<=b) {
                    max = Math.max(max, i);
                }
            } else {
                if(sum[i]-(half[i]-half[i-a])<=b) {
                    max = Math.max(max, i);
                }
            }
        }

        System.out.println(max+1);
    }
}