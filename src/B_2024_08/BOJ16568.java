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
        queue.add(new Info(N+1, 0));
        visited[N+1] = true;

        // search
        while(!queue.isEmpty()) {
            Info cur = queue.remove();

            // 영혼을 받고
            if(cur.loc==1) {
                System.out.println(cur.time);
                return;
            }

            // 새치기를 하고
            for(int i=0; i<3; i++) {
                int next = cur.loc-mov[i];
                next--;

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