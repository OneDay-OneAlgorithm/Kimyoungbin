package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// brute force의 경우 (거인탐색)*(망치사용수) = O(10^10)이라 TLE
// PQ를 이용하여 거인탐색 시간을 줄인다.
// WA1) 망치를 한번도 사용하지 않아도 모든 거인의 키가 센티보다 작을경우
public class BOJ19638 {
    static int N, H, T;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 인구수
        H = Integer.parseInt(line_1[1]); // 기준 키
        T = Integer.parseInt(line_1[2]); // 망치 사용수
        for(int i=0; i<N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        // WA1) 망치를 한번도 사용하지 않아도 모든 거인의 키가 센티보다 작을경우
        if(pq.peek()<H) {
            System.out.println("YES");
            System.out.println(0);
            return;
        }

        for(int i=0; i<T; i++) {
            int poll = pq.poll();
            pq.add(hit(poll));
            if(pq.peek()<H) {
                System.out.println("YES");
                System.out.println(i+1);
                return;
            }
        }

        System.out.println("NO");
        System.out.println(pq.poll());

    }

    private static int hit(int a) {
        if(a==1) return a;
        else return a/2;
    }
}
