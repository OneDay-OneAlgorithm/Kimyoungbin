package B_2024_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 순서: 일단 영혼을 받아서 한길이인지 확인하고 새치기를 하고 시간을 증가시킨다
public class BOJ16568 {
    static int N, a, b;
    static boolean[] visited;
    static int[] mov;
    static Queue<Info> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        visited = new boolean[N+2];
        mov = new int[]{0, a, b};

        bfs();
    }

    private static void bfs() {
        // init
        queue = new LinkedList<>();
        queue.add(new Info(N+1, 0)); // 한길이 앞에 N명이 있으므로, 1부터 시작한다고 가정시 한길이는 N+1에 있다.
        visited[N+1] = true;

        // search
        while(!queue.isEmpty()) {
            Info cur = queue.remove();

            // 영혼을 받은 사람이(맨 앞) 한길이인지 확인하고
            if(cur.loc==1) {
                System.out.println(cur.time);
                return;
            }

            // 3가지 방법으로 새치기를 하고
            for(int i=0; i<3; i++) {
                int next = cur.loc-mov[i];
                next--; // 한명이 빠지니까 한길이 위치를 1 감소시켜준다.

                if(0<next && !visited[next]) {
                    visited[next] = true;
                    // 시간을 증가시킨다.
                    queue.add(new Info(next, cur.time+1));
                }
            }
        }
    }

    static class Info {
        int loc; // 1~N 사람이 N명
        int time;
        public Info(int loc, int time) {
            this.loc = loc;
            this.time = time; // loc에 있을 때 시간
        }
    }
}