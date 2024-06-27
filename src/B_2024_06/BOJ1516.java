package B_2024_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

// topological sort
public class BOJ1516 {
    static int N;
    static int[] source, time;
    static LinkedList<Info>[] list;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        source = new int[N+1]; // 진입차수 배열
        list = new LinkedList[N+1];
        time = new int[N+1]; // 건물 준공 시간

        for(int i=1; i<=N; i++) {
            list[i] = new LinkedList<>();
        }

        for(int i=1; i<=N; i++) {
            String[] line_N = br.readLine().split(" ");
            int curTime = Integer.parseInt(line_N[0]);
            if(line_N.length==2) {
                time[i] = curTime;
                continue;
            }
            for(int j=1; j<line_N.length-1; j++) {
                list[Integer.parseInt(line_N[j])].add(new Info(i, curTime)); //j->i에 time만큼 소요
                source[i]++;
            }
        }

        for(int i=1; i<=N; i++) {
            if(source[i]==0) {
                pq.add(i);
            }
        }

        // 위상 정렬
        while(!pq.isEmpty()) {
            int cur = pq.poll();

            for(int i=0; i<list[cur].size(); i++) {
                int next = list[cur].get(i).end;
                int nextTime = list[cur].get(i).time;
                source[next]--;
                time[next] = Math.max(time[next], time[cur]+nextTime);
                if(source[next]==0) {
                    pq.add(next);
                }
            }
        }

        // 답 출력
        for(int i=1; i<=N; i++) {
            System.out.println(time[i]);
        }
    }

    static class Info {
        int end;
        int time;
        public Info(int end, int time) {
            this.end = end;
            this.time = time;
        }
    }
}