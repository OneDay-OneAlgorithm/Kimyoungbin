package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// 개수만 구하는거니까 정렬후에 H*2번 비교하면 되지 않을까?
public class BOJ3020 {
    static int N, H;
    static Integer[] 종유석; // 아래부터
    static Integer[] 석순;
    static int jPointer = 0;
    static int sPointer = 0;
    static int rst;
    static int ans = Integer.MAX_VALUE;
    static int[] cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        H = Integer.parseInt(line_1[1]);
        rst = N/2;// H=1부터 측정하므로 처음에는 모든 종유석을 통과
        cache = new int[N+1];
        종유석 = new Integer[N/2];
        석순 = new Integer[N/2];
        for(int i=0; i<N; i++) {
            if(i%2==0) 종유석[i/2] = Integer.parseInt(br.readLine());
            else 석순[i/2] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(종유석);
        Arrays.sort(석순, Collections.reverseOrder());
        // 종유석
        for(int i=1; i<=H; i++) {
            while(jPointer<N/2 && i>종유석[jPointer]) {
                rst--;
                jPointer++;
            }
//            System.out.print(rst+", ");
            while(sPointer<N/2 && H-i+1<=석순[sPointer]) {
                rst++;
                sPointer++;
            }
//            System.out.println(rst);
            ans = Math.min(ans, rst);
            cache[rst]++;
        }

        System.out.println(ans+" "+cache[ans]);
    }
}