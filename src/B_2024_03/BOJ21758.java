package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 벌통의 위치에 따라 케이스 갈림
// 맨 왼쪽 or 오른쪽 - 반드시 벌 한마리는 끝에 위치, 나머지 한마리는 최대값 탐색
// 중간 - 반드시 두 벌은 양쪽 끝에 위치, 벌통을 옮겨가며 최대값 탐색
// 누적합? LIS
public class BOJ21758
{
    static int N;
    static int[] arr;
    static int[] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        sum = new int[N+1];
        String[] line_1 = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(line_1[i-1]);
        }
        sum[0] = 0;
        sum[1] = arr[1];
        for(int i=1; i<=N; i++) {
            sum[i] = sum[i-1]+arr[i];
        }

        // 꿀통이 맨 오른쪽에 위치할 경우
        int rst1 = Integer.MIN_VALUE;
        for(int i=2; i<N; i++) { // 오른쪽 벌 위치
            rst1 = Math.max(rst1, honey(2, N)-arr[i]+honey(i+1, N));
        }
        // 꿀통이 맨 왼쪽에 위치할 경우
        int rst2 = Integer.MIN_VALUE;
        for(int i=N-1; i>=1; i--) { // 왼쪽 벌 위치
            rst2 = Math.max(rst2, honey(1, N-1)-arr[i]+honey(1, i-1));
        }
        // 꿀통이 중심에 위치할 경우
        int rst3 = Integer.MIN_VALUE;
        for(int i=2; i<N; i++) {
            rst3 = Math.max(rst3, honey(2, i)+honey(i,N-1));
        }

        int max = Math.max(rst1, Math.max(rst2, rst3));
        System.out.println(max);
    }

    // i1 ~ i2까지 딸수있는 꿀 양
    private static int honey(int i1, int i2) {
        return sum[i2] - sum[i1-1];
    }
}