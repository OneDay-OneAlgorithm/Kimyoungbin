package B_2024_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// k개를 연속으로 먹을 경우 정액가 제공, 초밥 무료 추가 제공
// 연속된 k개 -> 투포인터 (del, add)
// WA) del 제거 - add 추가 과정을 완벽히 분리해야 함 (sum판단-map조작 과정을 하나로 묶어야함)
public class BOJ15961 {
    static int N, d, k, c;
    static int[] arr;
    static Map<Integer, Integer> map = new HashMap<>(); // key번 초밥이 현재 value개 있음
    static int rst = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 접시수
        d = Integer.parseInt(line_1[1]); // 초밥 종류수
        k = Integer.parseInt(line_1[2]); // 연속수
        c = Integer.parseInt(line_1[3]); // 쿠폰번호
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            map.put(arr[i], 0);
        }
        map.put(c, 0); // 쿠폰초밥이 테이블에 없을경우 nullpointerexception 방지

        // 투포인터 초기화
        int sum=0;
        for(int i=0; i<k; i++) {
            if(map.get(arr[i])==0) {
                sum++;
            }
            map.put(arr[i], map.get(arr[i])+1);
        }
        if(map.get(c)==0) {
            rst = Math.max(rst, sum+1);
        } else {
            rst = Math.max(rst, sum);
        }

        // 투포인터 계산 - del제거, add추가
        for(int i=0; i<N; i++) {
            int del = i%N;
            int add = (i+k)%N;

            // del 제거
            if(map.get(arr[del])==1) {
                sum--;
            }
            map.put(arr[del], map.get(arr[del])-1);

            // add 추가
            if(map.get(arr[add])==0) {
                sum++;
            }
            map.put(arr[add], map.get(arr[add])+1);

            if(map.get(c)==0) {
                rst = Math.max(rst, sum+1);
            } else {
                rst = Math.max(rst, sum);
            }
        }

        System.out.println(rst);
    }
}