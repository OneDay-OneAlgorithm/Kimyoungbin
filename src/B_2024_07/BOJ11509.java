package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// WA1) arr 초기값을 0으로 두면 안됨 (인덱스 0이 존재)
// WA2) arr크기를 1000001로 잡으면 if(arr[H[i]+1]!=-1) {에서 배열범위 초과
// WA3) 반례 - H+1의 개수가 여러개일 수 있음 (5 4 5 4 1 3 3 5) -> arr이 굳이 인덱스를 가질 필요 없고, 개수를 표시해주면 될듯
public class BOJ11509 {
    static int N;
    static int[] H, arr;
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        H = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        arr = new int[1000002];

        for(int i=0; i<N; i++) {
            // 이전에 H[i]보다 1큰 풍선이 있었다면 이어서 터짐
            if(arr[H[i]+1]!=0) {
                arr[H[i]+1]--;
                arr[H[i]]++;
            }
            // 없었다면 새로 화살을 쏴야 함
            else {
                arr[H[i]]++;
                rst++;
            }
        }

        System.out.println(rst);
    }
}