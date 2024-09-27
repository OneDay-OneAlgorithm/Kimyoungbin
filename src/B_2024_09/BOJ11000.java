package B_2024_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000 {
    static int N, cnt = 0, rst = Integer.MIN_VALUE;
    static PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            if(o1.time==o2.time) {
                return o2.type-o1.type; // 종료가 먼저 되도록
            }
            return o1.time-o2.time;
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Info(s, 0));
            pq.add(new Info(e, 1));
        }

        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            if(cur.type==0) { // start
                cnt++;
            } else { // end
                cnt--;
            }
            rst = Math.max(cnt, rst);
        }

        System.out.println(rst);
    }


    static class Info {
        int time;
        int type; // 0: start, 1: end
        public Info(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }
}
