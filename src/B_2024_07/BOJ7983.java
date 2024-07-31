package B_2024_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ7983 {
    static int n;
    static int day; // 마지막으로 놀 수 있는 날
    static PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o2.t-o1.t; // 마감일 기준 내림차순 정렬
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            String[] line_n = br.readLine().split(" ");
            pq.add(new Info(Integer.parseInt(line_n[0]), Integer.parseInt(line_n[1])));
        }

        Info first = pq.poll();
        day = first.t-first.d;
        while(!pq.isEmpty()) {
            Info info = pq.poll();
            day = Math.min(day, info.t)-info.d;
        }

        System.out.println(day);
    }

    static class Info {
        int d; // 걸리는 시간
        int t; // 마감일
        public Info(int d, int t) {
            this.d = d;
            this.t = t;
        }
    }
}