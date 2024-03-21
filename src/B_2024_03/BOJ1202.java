package B_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

// search) priority queue 사용 (https://velog.io/@ich0906/%EB%B0%B1%EC%A4%80-1202-%EB%B3%B4%EC%84%9D-%EB%8F%84%EB%91%91
// 보석을 무게 오름차순으로 정렬하고, pq에서는 가치가 높은것순으로 정렬시킴.
public class BOJ1202
{
    static int N, K;
    static ArrayList<Jewel> jewels;
    static ArrayList<Integer> C;
    static PriorityQueue<Jewel> pq = new PriorityQueue<>(new Comparator<Jewel>() {
        @Override
        public int compare(Jewel o1, Jewel o2) {
            return o2.v-o1.v; // 가치 높은것부터
        }
    });
    static long rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line_1 = br.readLine().split(" ");
        N = Integer.parseInt(line_1[0]); // 보석 개수
        K = Integer.parseInt(line_1[1]); // 가방 개수
        jewels = new ArrayList<>(); // 보석 가격
        C = new ArrayList<>(); // 가방 최대무게
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int w = Integer.parseInt(line_N[0]);
            int v = Integer.parseInt(line_N[1]);
            jewels.add(new Jewel(w, v));
        }
        Collections.sort(jewels, new Comparator<Jewel>() { // 무게 오름차순 정렬
            @Override
            public int compare(Jewel j1, Jewel j2) {
                return j1.w-j2.w;
            }
        });
        for(int i=0; i<K; i++) {
            C.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(C); // 가방은 무게 오름차순 정렬

        // 가방과 보석이 무게 오름차순 정렬된 상태에서,
        // 무게가 작은 가방부터 하나씩 해당 가방보다 무게가 같거나 작은 보석중에 가장 가치가 높은것을 뽑는다.
        int j=0;
        for(int i=0; i<C.size(); i++) {
            int bagWeight = C.get(i); // 현재 가방

            while(j<N && bagWeight>=jewels.get(j).w) { // 현재 가방에 들어갈 수 있는 보석을 큐에 넣기
                pq.add(jewels.get(j++));
            }
            if(!pq.isEmpty()) { // 큐에서 가장 가치가 높은 보석을 빼서 가방에 넣기
                rst += pq.poll().v;
            }
        }

        System.out.println(rst);
    }


    static class Jewel {
        int w;
        int v;
        public Jewel(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}