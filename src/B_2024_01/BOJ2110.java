package B_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 분류: 매개변수탐색, 이분탐색
// 기준: 인접 공유기사이 가장 가까운거리,
public class BOJ2110
{
    static int N, C;
//    static boolean[] home = new boolean[1_000_000_001]; // 리스트 or 배열?
    static List<Integer> list = new ArrayList<>();
    static int low, high, mid, amount;
    static int rst = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]);
        C = Integer.parseInt(line_1[1]);
        for(int n=0; n<N; n++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list); // **순서대로 나온다는 보장이 없으므로 정렬**

        // 공유기 개수가 C일때 거리의 최대값 구하기
        low = 0; high = 1_000_000_000; // **인접 최대거리 (구하려는 값)**
        while(low<=high) {
            mid = (low+high)/2;
            amount = getGongUNum(mid);
            if(amount>=C) { // **정답 포함범위** (들어갈 수 있는 공유기 개수가 C보다는 크거나 같아야함)
                low = mid+1;
                rst = Math.max(rst, mid); // **최근값이 정답이 아님**
            } else {
                high = mid-1;
            }
        }

        System.out.println(rst);
    }

    // 인접 최단거리 dist일 경우 들어갈 수 있는 공유기 개수
    private static int getGongUNum(int dist) {
        int prevGongU = list.get(0);
        int rst = 1;
        for(int i=1; i<list.size(); i++) {
            if(list.get(i)-prevGongU>=dist) {
                prevGongU = list.get(i);
                rst++;
            }
        }
        return rst;
    }
}