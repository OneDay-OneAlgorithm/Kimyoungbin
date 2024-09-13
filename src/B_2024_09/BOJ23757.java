package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ23757 {
    static int N, M;
    static int[] c, w;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        c = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray(); // 선물상자
        w = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray(); // 원하는 선물개수

        for(int present: c) {
            pq.add(present);
        }

        for(int i=0; i<M; i++) {
            if(pq.isEmpty() || pq.peek()-w[i]<0) {
                System.out.println(0);
                return;
            }
            pq.add(pq.poll()-w[i]);
        }
        System.out.println(1);
    }
}