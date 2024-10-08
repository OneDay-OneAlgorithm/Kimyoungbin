package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// search) 투포인터 풀이 -> 눈사람 1개를 정해두고, 각 케이스에 대해서 투포인터 O(N^2*N==N^3)
// WR1) 어휴
public class BOJ20366_2 {
    static int N;
    static int[] arr;
    static int rst = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        Arrays.sort(arr);

        // 기준 눈사람 정하기 O(N^2)
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                int s1 = arr[i]+arr[j];

                // 투포인터 O(N)
                int s=0, e=N-1;
                while(s<e) {
                    if(s==i || s==j) { // WR1)
                        s++;
                        continue;
                    }
                    if(e==i || e==j) {
                        e--;
                        continue;
                    }

                    int s2 = arr[s]+arr[e];
                    rst = Math.min(rst, Math.abs(s1-s2));

                    if(s1>s2) s++;
                    else if(s1<s2) e--;
                    else { // 높이가 동일
                        System.out.println(0);
                        return;
                    }
                }
            }
        }


        System.out.println(rst);
    }
}
