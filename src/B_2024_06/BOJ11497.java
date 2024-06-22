package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 확실한 규칙은 못찾겠다
// search) rst의 왼쪽, 오른쪽 끝에 번갈아가면서 넣어준다.
public class BOJ11497 {
    static int N;
    static int[] arr, rst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            rst = new int[N];
            Arrays.sort(arr);

            int l=0, r=N-1;
            for(int i=0; i<N; i++) {
                if(i%2==0) rst[l++] = arr[i];
                else rst[r--] = arr[i];
            }

            int max = 0;
            for(int i=1; i<N; i++) {
                max = Math.max(max, Math.abs(rst[i]-rst[i-1]));
            }
            System.out.println(max);
        }
    }

}