package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 측정할 수 없는 무게중 최소값 -> 이분탐색: X (기준점이 존재하지 않음), DP: X (범위가 너무 큼), search) greedy
// search) 오름차순 정렬 후 0~k번 추까지 사용하여 1~i까지의 무게를 잴 수 있다면, i+1 무게를 재기 위해서는 무게가 i+1인 추가 존재해야 한다.
// i=n일때 sum=k라는 이야기는 0~n번까지의 추를 사용하여 1~k까지 모든 무게를 측정할 수 있다는 말이다. 따라서 sum=k일때 다음으로 큰 추가 k+1보다 크면 k+1을 측정할 수 없기 때문에 그 수가 측정할 수 없는 최소 양의 정수무게가 된다.
// 그리고 sum=k일때 다음으로 큰 추가 k+1보다 작거나 같다면, sum+다음으로 큰 추까지는 측정할 수 있다.
public class BOJ2437 {
    static int N, sum = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        Arrays.sort(arr);

        for(int i=0; i<N; i++) {
            if(sum+1<arr[i]) {
                break;
            }
            sum+=arr[i];
        }
        System.out.println(sum+1);
    }
}