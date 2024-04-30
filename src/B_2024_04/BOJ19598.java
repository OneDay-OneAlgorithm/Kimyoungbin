package B_2024_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 끝나는 순서가 빠른 순서대로 정렬하는 PQ
public class BOJ19598 {
    static int N, rst = 1;
    static Meeting[] list;
    static PriorityQueue<Meeting> pq = new PriorityQueue<>(new Comparator<Meeting>() { // endTIme 오름차순
        @Override
        public int compare(Meeting m1, Meeting m2) {
            return m1.endTime-m2.endTime;
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new Meeting[N];
        for(int i=0; i<N; i++) {
            String[] line_N = br.readLine().split(" ");
            int startTime = Integer.parseInt(line_N[0]);
            int endTime = Integer.parseInt(line_N[1]);
            list[i] = new Meeting(startTime, endTime);
        }
        Arrays.sort(list, new Comparator<Meeting>() { // startTime 오름차순 정렬
            @Override
            public int compare(Meeting m1, Meeting m2) {
                return m1.startTime-m2.startTime;
            }
        });
        for(int i=0; i<N; i++) {
            int startTime = list[i].startTime;
            int endTime = list[i].endTime;

            if(!pq.isEmpty()) {
                Meeting peek = pq.peek();
                if(peek.endTime<=startTime) { // 겹치지 않는 경우 기존 회의정보 제거
                    pq.poll();
                } else { // 겹치는 경우 회의실 추가
                    rst++;
                }
            }
            pq.add(new Meeting(startTime, endTime));
        }

        System.out.println(rst);
    }

    static class Meeting {
        int startTime;
        int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}