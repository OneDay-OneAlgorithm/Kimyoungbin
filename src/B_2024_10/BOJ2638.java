package B_2024_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// (0,0)은 공백이므로 bfs 반복
// WA1) 처음부터 모두 0인경우 답이 1이 나옴
public class BOJ2638 {
    static int N, M;
    static int[][] arr;
    static Queue<Info> queue;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int delCnt = delete();
        if(delCnt==0) { // 처음부터 치즈가 없다면
            System.out.println(0);
            return;
        }

        while(true) {
            // 빈공간 bfs, 닿는 공기에 따라 치즈좌표값 증가
            bfs();
            // 치즈좌표값이 2이상 증가했을 경우 빈공간으로 변화
            delCnt = delete();
            time++;
            if(delCnt==0) break;
        }

        System.out.println(time);
    }

    private static void bfs() {
        queue = new LinkedList<>();
        visited = new boolean[N][M];
        queue.add(new Info(0,0));
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Info cur = queue.poll();

            if(arr[cur.y][cur.x]==0) {
                for(int i=0; i<4; i++) {
                    int nx = cur.x+dx[i];
                    int ny = cur.y+dy[i];
                    if(0<=nx && nx<M && 0<=ny && ny<N) {
                        if(arr[ny][nx]==0 && !visited[ny][nx]) { // 빈공간에 대해서는 bfs
                            visited[ny][nx] = true;
                            queue.add(new Info(nx, ny));
                        } else if(arr[ny][nx]>=1) { // 치즈가 주변에 존재한다면 값 증가
                            arr[ny][nx]++;
                        }
                    }
                }
            }
        }
    }

    // 남은 치즈 수 반환
    private static int delete() {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(arr[i][j]>=3) { // 치즈의 두 변 이상이 외부공기와 닿았다면 삭제
                    arr[i][j] = 0;
                } else if(1<=arr[i][j] && arr[i][j]<=2) {
                    arr[i][j] = 1;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void printArr() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Info {
        int x;
        int y;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
